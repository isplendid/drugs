package functional.dao

import play.api.db.slick.DatabaseConfigProvider
import org.scalatest.AsyncFlatSpec
import org.scalatestplus.play.OneAppPerSuite

import dao.IngredientSlickDAO
import model.Ingredient

import functional.util.{InMemoryDatabase, SimpleTestData, WithTestInjector}

class IngredientSlickDAOTest extends AsyncFlatSpec
  with OneAppPerSuite
  with InMemoryDatabase
  with SimpleTestData
  with WithTestInjector
{
  import driver.api._

  protected lazy val dbConfigProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]

  "IngredientSlickDAO" should "return all element" in {
    val ingredientDAO = injector.instanceOf[IngredientSlickDAO]

    for {
      all <- ingredientDAO.all()
    } yield assert(all == testData.ingredients)
  }

  it should "insert an element" in {
    val ingredientDAO = injector.instanceOf[IngredientSlickDAO]
    val ingredient = Ingredient("Test-vitamin", 1, 1)

    for {
      id <- ingredientDAO.insert(ingredient)
      inserted <- db.run(ingredients.filter(_.id === id).result.headOption)
    } yield assert(inserted === Option(ingredient.copy(id = Option(id))))
  }

}
