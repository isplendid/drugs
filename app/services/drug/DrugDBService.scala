package services.drug

import com.google.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

import util.Util.Implicits.Futurable
import dao._
import model._

@Singleton
class DrugDBService @Inject()(
  drugDAO: DrugDAO,
  ingredientDAO: IngredientDAO,
  distributorDAO: DistributorDAO
) extends DrugService {


  def deleteDrug(id: Drug.Id)(implicit ec: ExecutionContext): Future[Int] = drugDAO.delete(id)

  def insert(distributor: Distributor)(implicit ec: ExecutionContext): Future[Distributor.Id] = {
    distributorDAO.insert(distributor)
  }

  def insert(drug: Drug)(implicit ec: ExecutionContext): Future[Drug.Id] = drugDAO.insert(drug)

  def insert(ingredient: Ingredient)(implicit ec: ExecutionContext): Future[Ingredient.Id] = {
    ingredientDAO.insert(ingredient)
  }

  def getDrugs(implicit ec: ExecutionContext): Future[Seq[Drug]] = drugDAO.all()

  def renameDrug(id: Drug.Id, name: String)(implicit ec: ExecutionContext): Future[Int] = for {
    drugOpt <- drugDAO.byId(id)
    drug    <- drugOpt.getOrElse[Drug](throw new RuntimeException("Invalid drug Id.")).toFuture
    rows    <- drugDAO.update(drug.copy(name = name))
  } yield rows

}
