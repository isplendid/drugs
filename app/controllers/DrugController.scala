package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits._

import services.drug.DrugService
import util.Util.Implicits.Futurable

@Singleton
class DrugController @Inject() (
  drugService: DrugService
)extends Controller {

  def getIngrediens(name: String) = Action.async {
    for {
      ingrs    <- drugService.getIngredientsByDrugName(name)
      strIngrs <- ingrs.map(_.name).mkString(", ").future
    } yield Ok(strIngrs)
  }

}
