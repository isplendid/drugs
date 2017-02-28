package controllers

import com.google.inject.{Inject, Singleton}

import scala.concurrent.Future
import play.api.data.Form
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.Json

import services.drug.DrugService
import controllers.dto.DrugDTO
import model.Drug

import util.Util.Implicits.Futurable

@Singleton
class DrugController @Inject() (
  drugService: DrugService
)extends Controller {

  def all = Action.async {
    import model.Drug.format

    for {
      d <- drugService.getDrugs
    } yield Ok(Json.toJson(d))
  }

  def create = Action.async(parse.json) { request =>
    Form(DrugDTO.form).bind(request.body).fold(_ => Future.successful(BadRequest), { drugDTO =>
      for {
        distId <- drugService.insert(drugDTO.distributor.toModel)
        drugId <- drugService.insert(drugDTO.toModel(distId))
        _      <- drugDTO.ingredients.foreach(ing => drugService.insert(ing.toModel(drugId))).toFuture
      } yield Ok(Json.toJson(drugId))
    })
  }

  def update(id: Drug.Id, name: String) = Action.async {
    for {
      _ <- drugService.renameDrug(id, name)
    } yield Ok
  }

  def delete(id: Drug.Id) = Action.async {
    for {
      _ <- drugService.deleteDrug(id)
    } yield Ok
  }

}
