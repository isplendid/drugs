package controllers.dto

import model.{Drug, Ingredient}
import model.Drug.Id
import play.api.data._
import play.api.data.Forms._

case class IngredientDTO(name: String, amount: Int) {

  def toModel(drugId: Drug.Id): Ingredient = Ingredient(name, amount, drugId)

}

object IngredientDTO {

  val form: Mapping[IngredientDTO] = mapping(
    "name"   -> nonEmptyText,
    "amount" -> number
  )(IngredientDTO.apply)(IngredientDTO.unapply)
}