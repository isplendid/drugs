package model

import Drug._
import play.api.libs.json.{Json, OFormat}

case class Drug(
  name: String,
  distributorId: Distributor.Id,
  id: Option[Id] = None
)

object Drug {
  type Id = Long

  implicit val format: OFormat[Drug] = Json.format[Drug]
}