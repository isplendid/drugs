package model

import Drug._

case class Drug(
  name: String,
  distributorId: Distributor.Id,
  id: Option[Id] = None
)

//TODO: Place some testable logic for demonstrating purpose.
object Drug {
  type Id = Long
}