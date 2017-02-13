package tables

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

trait DistributorComponet { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class Distributors(tag: Tag) extends Table[model.Distributor](tag, "DISTRIBUTOR") {
    def id     = column[model.Distributor.Id]("ID", O.PrimaryKey, O.AutoInc)
    def name   = column[String]("NAME")

    def * = (name, id.?) <> ((model.Distributor.apply _).tupled, model.Distributor.unapply)
  }

  protected val distributors = TableQuery[Distributors]
}