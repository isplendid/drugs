package functional.util

import model.{Distributor, Drug, Ingredient}
import play.api.db.slick.DatabaseConfigProvider
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

import scala.concurrent.duration._

trait TestDBData {
  this: WithTestInjector =>

  protected lazy val dbConfig: DatabaseConfig[JdbcProfile] = injector
    .instanceOf[DatabaseConfigProvider]
    .get[JdbcProfile]

  import dao._
  import dbConfig.driver.api._

  private lazy val drugDAO = injector.instanceOf[DrugSlickDAO]
  private lazy val distributorDAO = injector.instanceOf[DistributorSlickDAO]
  private lazy val ingredientDAO = injector.instanceOf[IngredientSlickDAO]

  import distributorDAO.distributors
  import drugDAO.drugs
  import ingredientDAO.ingredients

  protected val timeout: FiniteDuration = 5.seconds

  protected def ddl = drugs.schema ++ distributors.schema ++ ingredients.schema
  protected def populate: DBIO[Unit] = DBIO.seq(ddl.create, distr, drgs, ingrs)

  private def distr = distributors ++= Seq(
    Distributor("JutaVit", Option(1)),
    Distributor("Dr. Aliment", Option(2))
  )

  private def drgs = drugs ++= Seq(
    Drug("Lecitin PRO", 1, Option(1)),
    Drug("Lizin-C", 2, Option(2)),
    Drug("Magnézium + B6", 1, Option(3))
  )

  private def ingrs = ingredients ++= Seq(
    Ingredient("Lecitin", 1200, 1, Option(1)),
    Ingredient("L-izin", 714, 2, Option(2)),
    Ingredient("C vitamin", 30, 2, Option(3)),
    Ingredient("Magnézium", 250, 3, Option(4)),
    Ingredient("B6 vitamin", 2, 3, Option(5)),
    Ingredient("D3 vitamin", 10, 3, Option(6))
  )
}
