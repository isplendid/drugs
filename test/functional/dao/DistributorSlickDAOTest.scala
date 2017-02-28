package functional.dao

import play.api.db.slick.DatabaseConfigProvider
import org.scalatest.AsyncFlatSpec
import org.scalatestplus.play.OneAppPerSuite

import dao.DistributorSlickDAO
import model.Distributor

import functional.util.{InMemoryDatabase, SimpleTestData, WithTestInjector}

class DistributorSlickDAOTest extends AsyncFlatSpec
  with OneAppPerSuite
  with InMemoryDatabase
  with SimpleTestData
  with WithTestInjector
{
  import driver.api._

  protected lazy val dbConfigProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]

  "DistributorSlickDAO" should "return all element" in {
    val distributorDAO = injector.instanceOf[DistributorSlickDAO]

    for {
      all <- distributorDAO.all()
    } yield assert(all == testData.distributors)
  }

  it should "insert an element" in {
    val distributorDAO = injector.instanceOf[DistributorSlickDAO]
    val distributor = Distributor("Test distributor")

    for {
      id <- distributorDAO.insert(distributor)
      inserted <- db.run(distributors.filter(_.id === id).result.headOption)
    } yield assert(inserted === Option(distributor.copy(id = Option(id))))
  }

}
