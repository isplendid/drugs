package dao

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import model.Ingredient
import tables.{DistributorComponet, DrugComponent, IngredientComponet}

trait IngredientDAO {
  import slick.dbio.DBIO

  def all(): DBIO[Seq[Ingredient]]
  def byDrugId(id: model.Drug.Id): DBIO[Seq[Ingredient]]

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

  def all(): DBIO[Seq[Ingredient]] = ingredients.result
  def byDrugId(id: model.Drug.Id): DBIO[Seq[Ingredient]] = ingredients.filter(_.drugId === id).result
}
