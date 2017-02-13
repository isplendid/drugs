package functional.util

import model._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import tables._

import scala.concurrent.duration._

trait AllComponent extends DrugComponent with DistributorComponet with IngredientComponet {
  this: HasDatabaseConfigProvider[JdbcProfile] =>
}

trait TestDBData extends AllComponent with HasDatabaseConfigProvider[JdbcProfile] {
  this: WithTestInjector =>

  override protected lazy val dbConfigProvider: DatabaseConfigProvider = injector
    .instanceOf[DatabaseConfigProvider]

  import dbConfig.driver.api._

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
