package dao

import com.google.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import model.Drug
import tables.DrugComponent

trait DrugDAO {
  import slick.dbio.DBIO

  def all(): DBIO[Seq[Drug]]
  def byName(name: String): DBIO[Seq[Drug]]

}

@Singleton
class DrugSlickDAO @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider
) extends DrugDAO with DrugComponent with HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  val drugs = TableQuery[Drugs]

  def all(): DBIO[Seq[Drug]] = drugs.result
  def byName(name: String): DBIO[Seq[Drug]] = drugs.filter(_.name === name).result
}
