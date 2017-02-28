package unit

import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.concurrent.duration._

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory

import services.drug.DrugDBService
import dao._
import model._
import util.Util.Implicits.Futurable

class DrugDBServiceTest extends FlatSpec with MockFactory {

  trait Spec {
    val drugDAO: DrugDAO = mock[DrugDAO]
    val ingreditenDAO: IngredientDAO = mock[IngredientDAO]
    val distributorDAO: DistributorDAO = mock[DistributorDAO]
    val service: DrugDBService = new DrugDBService(drugDAO, ingreditenDAO, distributorDAO)

    val timeout: FiniteDuration = 5.seconds
    implicit val executionContext: ExecutionContextExecutor =
      scala.concurrent.ExecutionContext.Implicits.global
  }

  "DrugDBServiceTest" should "return the id of inserted drug" in new Spec {
    val exptId = 1l
    val drug = Drug("name", 1, Option(exptId))
    (drugDAO.insert _).expects(drug).returns(exptId.future)

    val future = service.insert(drug).map { id =>
      assert(id === exptId)
    }

    Await.result(future, timeout)
  }

  it should "return the id of inserted ingredient" in new Spec {
    val exptId = 1l
    val ingr = Ingredient("name", 1, 1,  Option(exptId))
    (ingreditenDAO.insert _).expects(ingr).returns(exptId.future)

    val future = service.insert(ingr).map { id =>
      assert(id === exptId)
    }

    Await.result(future, timeout)
  }

  it should "return the id of inserted distributor" in new Spec {
    val exptId = 1l
    val dist = Distributor("name", Option(exptId))
    (distributorDAO.insert _).expects(dist).returns(exptId.future)

    val future = service.insert(dist).map { id =>
      assert(id === exptId)
    }

    Await.result(future, timeout)
  }

  it should "return the number of affacted rows on deleting drug" in new Spec {
    val drugId = 1l
    val exptAffectedRows = 1
    (drugDAO.delete _).expects(drugId).returns(exptAffectedRows.future)

    val future = service.deleteDrug(drugId).map { affectedRows =>
      assert(affectedRows === exptAffectedRows)
    }

    Await.result(future, timeout)
  }

  it should "return the list of drugs" in new Spec {
    val exptDrugs = Seq(
      Drug("name", 1, Option(1)),
      Drug("name", 1, Option(2))
    )
    (drugDAO.all _).expects().returns(exptDrugs.future)

    val future = service.getDrugs.map { drugs =>
      assert(drugs === exptDrugs)
    }

    Await.result(future, timeout)
  }

  it should "return the number of affacted rows on rename drug" in new Spec {
    val drugId = 1l
    val drug = Drug("name", 1, Option(drugId))
    val newName = "renamed"
    val renamedDrug = drug.copy(name = newName) 
    val exptAffectedRows = 1

    (drugDAO.byId _).expects(drugId).returns(Option(drug).future)
    (drugDAO.update _).expects(renamedDrug).returns(exptAffectedRows.future)

    val future = service.renameDrug(drugId, newName).map { affectedRows =>
      assert(affectedRows === exptAffectedRows)
    }

    Await.result(future, timeout)
  }

  it should "throw `RuntimeException` on rename non-extining drug" in new Spec {
    val drugId = 1l

    (drugDAO.byId _).expects(drugId).returns(None.future)

    val ex = intercept[RuntimeException] {
      val future = service.renameDrug(drugId, "renamed")
      Await.result(future, timeout)
    }

    assert(ex.getMessage === "Invalid drug Id.")
  }

}
