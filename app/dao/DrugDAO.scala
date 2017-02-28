package dao

import com.google.inject.{Inject, Singleton}
import model.Drug
import model.Drug.Id
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import tables.{DistributorComponet, DrugComponent}

import scala.concurrent.Future

trait DrugDAO {

  def all(): Future[Seq[Drug]]
  def byId(id: Id): Future[Option[Drug]]
  def insert(drug: Drug): Future[Drug.Id]
  def update(drug: Drug): Future[Int]
  def delete(id: Drug.Id): Future[Int]

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

  def all(): Future[Seq[Drug]] = db.run(drugs.result)

  def byId(id: Id): Future[Option[Drug]] = db.run(drugs.filter(_.id === id).result.headOption)

  def insert(drug: Drug): Future[Drug.Id] = db.run(
    (drugs returning drugs.map(_.id)) += drug
  )

  def update(drug: Drug): Future[Int] = db.run(drugs.filter(_.id === drug.id).update(drug))

  def delete(id: Drug.Id): Future[Int] = db.run(drugs.filter(_.id === id).delete)

}
