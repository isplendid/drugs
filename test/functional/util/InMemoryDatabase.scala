package functional.util

import org.scalatest.{BeforeAndAfterEachTestData, Suite, TestData}
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Await

trait InMemoryDatabase extends BeforeAndAfterEachTestData {
  this: Suite
    with TestDBData
    with HasDatabaseConfigProvider[JdbcProfile] =>

  import dbConfig.driver.api._

  override protected def beforeEach(testData: TestData): Unit = {
    super.beforeEach(testData)
    Await.result(db.run(populate), timeout)
  }

  override protected def afterEach(testData: TestData): Unit =
    try {
      super.afterEach(testData)
    } finally {
      Await.result(db.run(ddl.drop), timeout)
    }
}
