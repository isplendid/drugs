package dao

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import model.Distributor
import tables.DistributorComponet

trait DistributorDAO {
  import slick.dbio.DBIO

  def all(): DBIO[Seq[Distributor]]

}

@Singleton
class DistributorSlickDAO @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
) extends DistributorDAO with DistributorComponet with HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def all(): DBIO[Seq[Distributor]] = distributors.result
}
