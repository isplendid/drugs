package tables

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

trait IngredientComponet {
  self: HasDatabaseConfigProvider[JdbcProfile] with DrugComponent =>
  import driver.api._

  class Ingredients(tag: Tag) extends Table[model.Ingredient](tag, "INGREDIENT") {
    def id     = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def drugId = column[model.Drug.Id]("DRUG_ID")
    def name   = column[String]("NAME")
    def amount = column[Int]("AMOUNT")

    def drug   = foreignKey("DRUG_PK", drugId, drugs)(_.id)

    def * = (name, amount, drugId, id.?) <> ((model.Ingredient.apply _).tupled, model.Ingredient.unapply)
  }

  protected val ingredients = TableQuery[Ingredients]
}
