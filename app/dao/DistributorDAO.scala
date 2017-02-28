package dao

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import model.Distributor
import tables.DistributorComponet

import scala.concurrent.Future

trait DistributorDAO {

  def all(): Future[Seq[Distributor]]
  def insert(distributor: Distributor): Future[Distributor.Id]

}

@Singleton
class DistributorSlickDAO @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
) extends DistributorDAO with DistributorComponet with HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def all(): Future[Seq[Distributor]] = db.run(distributors.result)

  def insert(distributor: Distributor): Future[Distributor.Id] = db.run(
    (distributors returning distributors.map(_.id)) += distributor
  )
}
