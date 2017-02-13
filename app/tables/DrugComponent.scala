package tables

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

trait DrugComponent { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  //TODO: Use slick foreign key on foreign keys
  class Drugs(tag: Tag) extends Table[model.Drug](tag, "DRUG") {
    def id            = column[model.Drug.Id]("ID", O.PrimaryKey, O.AutoInc)
    def distributorId = column[model.Distributor.Id]("DISTRIBUTOR_ID")
    def name          = column[String]("NAME")

    def * = (name, distributorId, id.?) <> ((model.Drug.apply _).tupled, model.Drug.unapply)
  }
}