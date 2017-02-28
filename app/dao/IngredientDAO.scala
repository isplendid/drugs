package dao

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import model.Ingredient
import tables.{DistributorComponet, DrugComponent, IngredientComponet}

import scala.concurrent.Future

trait IngredientDAO {

  def all(): Future[Seq[Ingredient]]
  def insert(ingredient: Ingredient): Future[Ingredient.Id]

}

@Singleton
class IngredientSlickDAO @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
) extends IngredientDAO
  with HasDatabaseConfigProvider[JdbcProfile]
  with IngredientComponet
  with DrugComponent
  with DistributorComponet
{
  import driver.api._

  def all(): Future[Seq[Ingredient]] = db.run(ingredients.result)

  def insert(ingredient: Ingredient): Future[Ingredient.Id] = db.run(
    (ingredients returning ingredients.map(_.id)) += ingredient
  )

}
