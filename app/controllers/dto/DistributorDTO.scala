package controllers.dto

import model.Distributor
import play.api.data._
import play.api.data.Forms._

case class DistributorDTO(name: String) {

  def toModel: Distributor = Distributor(name)

}

object DistributorDTO {

  val form: Mapping[DistributorDTO] = mapping(
    "name" -> nonEmptyText
  )(DistributorDTO.apply)(DistributorDTO.unapply)
}