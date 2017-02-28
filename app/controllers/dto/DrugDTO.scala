package controllers.dto

import model.{Distributor, Drug}
import play.api.data._
import play.api.data.Forms._

case class DrugDTO(name: String, ingredients: List[IngredientDTO], distributor: DistributorDTO) {

  def toModel(distId: Distributor.Id): Drug = Drug(name, distId)

}

object DrugDTO {

  val form: Mapping[DrugDTO] = mapping(
    "name"        -> nonEmptyText,
    "ingredients" -> list(IngredientDTO.form),
    "distributor" -> DistributorDTO.form
  )(DrugDTO.apply)(DrugDTO.unapply)
}