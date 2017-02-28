package functional.util

import model._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import tables._

import scala.concurrent.duration._

trait TestDataComponent {

  def testData: TestData

  trait TestData {
    def drugs: Seq[Drug]
    def ingredients: Seq[Ingredient]
    def distributors: Seq[Distributor]
  }
}

trait SimpleTestData extends TestDataComponent {

  override def testData: TestData = new SimpleTestData

  class SimpleTestData extends TestData {

    override def drugs: Seq[Drug] = Seq(
      Drug("Lecitin PRO", 1, Option(1)),
      Drug("Lizin-C", 2, Option(2)),
      Drug("Magnézium + B6", 1, Option(3))
    )

    override def ingredients: Seq[Ingredient] = Seq(
      Ingredient("Lecitin", 1200, 1, Option(1)),
      Ingredient("L-izin", 714, 2, Option(2)),
      Ingredient("C vitamin", 30, 2, Option(3))
    )

    override def distributors: Seq[Distributor] = Seq(
      Distributor("JutaVit", Option(1)),
      Distributor("Dr. Aliment", Option(2))
    )
  }
}
