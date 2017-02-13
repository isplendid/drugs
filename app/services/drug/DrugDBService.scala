package services.drug

import com.google.inject.{Inject, Singleton}
import dao._
import model.Ingredient
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import util.Util.Implicits.Futurable

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DrugDBService @Inject()(
  drugDAO: DrugDAO,
  ingredientDAO: IngredientDAO,
  protected val dbConfigProvider: DatabaseConfigProvider
) extends DrugService with HasDatabaseConfigProvider[JdbcProfile] {

  def getIngredientsByDrugName(name: String)(implicit ec: ExecutionContext): Future[Seq[Ingredient]] = for {
    drugs <- db.run(drugDAO.byName(name))
    drug <- if (drugs.length == 1) drugs.head.future else Future.failed(
      new RuntimeException("Drug is not found or the name is not unique."))
    ingrs <- drug.id.fold(Future.failed[Seq[Ingredient]](
      new RuntimeException("Drug has no id.")))(id => db.run(ingredientDAO.byDrugId(id)))
  } yield ingrs
}
