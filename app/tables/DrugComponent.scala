package tables

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

trait DrugComponent {
  self: HasDatabaseConfigProvider[JdbcProfile] with DistributorComponet =>
  import driver.api._

  class Drugs(tag: Tag) extends Table[model.Drug](tag, "DRUG") {
    def id            = column[model.Drug.Id]("ID", O.PrimaryKey, O.AutoInc)
    def distributorId = column[model.Distributor.Id]("DISTRIBUTOR_ID")
    def name          = column[String]("NAME")

    def distributor = foreignKey("DISTRIBUTOR_PK", distributorId, distributors)(_.id)

    def * = (name, distributorId, id.?) <> ((model.Drug.apply _).tupled, model.Drug.unapply)
  }

  protected val drugs = TableQuery[Drugs]
}