package functional.dao

import play.api.db.slick.DatabaseConfigProvider
import org.scalatest.AsyncFlatSpec
import org.scalatestplus.play.OneAppPerSuite

import dao.DrugSlickDAO
import model.Drug

import functional.util.{InMemoryDatabase, SimpleTestData, WithTestInjector}

class DrugSlickDAOTest extends AsyncFlatSpec
  with OneAppPerSuite
  with InMemoryDatabase
  with SimpleTestData
  with WithTestInjector
{
import driver.api._

  protected lazy val dbConfigProvider: DatabaseConfigProvider = injector.instanceOf[DatabaseConfigProvider]

  "DrugSlickDAO" should "return all element" in {
    val drugDAO = injector.instanceOf[DrugSlickDAO]

    for {
      all <- drugDAO.all()
    } yield assert(all == testData.drugs)
  }

  it should "retrun element by id" in {
    val drugDAO = injector.instanceOf[DrugSlickDAO]

    for {
      lecitin   <- drugDAO.byId(1)
      lizin     <- drugDAO.byId(2)
      magnesium <- drugDAO.byId(3)
    } yield assert(
        lecitin === Option(testData.drugs(0)) &&
        lizin === Option(testData.drugs(1)) &&
        magnesium === Option(testData.drugs(2))
    )
  }

  it should "insert an element" in {
    val drugDAO = injector.instanceOf[DrugSlickDAO]
    val drug = Drug("Aspirin", 1)

    for {
      id <- drugDAO.insert(drug)
      inserted <- db.run(drugs.filter(_.id === id).result.headOption)
    } yield assert(inserted === Option(drug.copy(id = Option(id))))
  }

  it should "update an element" in {
    val drugDAO = injector.instanceOf[DrugSlickDAO]
    val drug = testData.drugs.head.copy(name = "Changed")

    for {
      rows <- drugDAO.update(drug)
      updated <- db.run(drugs.filter(_.id === drug.id).result.headOption)
    } yield assert(updated === Option(drug) && rows === 1)
  }

  it should "delete an element" in {
    val drugDAO = injector.instanceOf[DrugSlickDAO]
    val drug = testData.drugs.last

    for {
      rows <- drugDAO.delete(drug.id.get)
      none <- db.run(drugs.filter(_.id === drug.id).result.headOption)
    } yield assert(rows === 1 && none === None)
  }

}
