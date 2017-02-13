package model

import Ingredient._

case class Ingredient(
  name: String,
  amount: Int,
  drugId: Drug.Id,
  id: Option[Id] = None
)

object Ingredient {
  type Id = Long
}