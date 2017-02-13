package model

import Distributor._

case class Distributor(
  name: String,
  id: Option[Id] = None
)

object Distributor {
  type Id = Long
}