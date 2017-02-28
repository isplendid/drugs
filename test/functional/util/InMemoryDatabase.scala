package functional.util

import org.scalatest.{BeforeAndAfterEachTestData, Suite, TestData => TD}

import scala.concurrent.Await
import scala.concurrent.duration._

trait InMemoryDatabase extends BeforeAndAfterEachTestData with AllTableComponent {
  this: Suite with TestDataComponent =>

  import dbConfig.driver.api._

  protected lazy val timeout: FiniteDuration = 5.seconds

  private lazy val ddl = drugs.schema ++ distributors.schema ++ ingredients.schema
  private lazy val populate: DBIO[Unit] = DBIO.seq(ddl.create, distr, drgs, ingrs)

  private lazy val distr = distributors ++= testData.distributors
  private lazy val drgs = drugs ++= testData.drugs
  private lazy val ingrs = ingredients ++= testData.ingredients

  override protected def beforeEach(td: TD): Unit = {
    super.beforeEach(td)
    Await.result(db.run(populate), timeout)
  }

  override protected def afterEach(td: TD): Unit =
    try {
      super.afterEach(td)
    } finally {
      Await.result(db.run(ddl.drop), timeout)
    }
}
