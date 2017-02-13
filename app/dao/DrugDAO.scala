package dao

import com.google.inject.{Inject, Singleton}
import model.Drug
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import tables.{DistributorComponet, DrugComponent}

trait DrugDAO {
  import slick.dbio.DBIO

  def all(): DBIO[Seq[Drug]]
  def byName(name: String): DBIO[Seq[Drug]]

}

@Singleton
class DrugSlickDAO @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
) extends DrugDAO
  with DrugComponent
  with DistributorComponet
  with HasDatabaseConfigProvider[JdbcProfile]
{
  import driver.api._

  def all(): DBIO[Seq[Drug]] = drugs.result
  def byName(name: String): DBIO[Seq[Drug]] = drugs.filter(_.name === name).result
}
