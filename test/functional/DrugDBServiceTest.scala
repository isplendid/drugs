package functional

import functional.util._
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.libs.concurrent.Execution.Implicits._
import services.drug.DrugDBService

import scala.concurrent.Await
import scala.concurrent.duration._

class DrugDBServiceTest()
  extends PlaySpec
    with OneAppPerSuite
    with WithTestInjector
    with InMemoryDatabase
    with TestDBData {

  "DrugDBServiceTest" should {

    "dummy testcase" in {

      val service = injector.instanceOf[DrugDBService]
      val res = service.getIngredientsByDrugName("Magnézium + B6")
      val ret = Await.result(res, 5.seconds)
      5 mustEqual 5
    }

    "second dummy testcase" in {
      3 mustEqual 3
    }

  }

}
