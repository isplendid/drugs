package services.drug

import model.Drug.Id

import scala.concurrent.{ExecutionContext, Future}
import model.{Distributor, Drug, Ingredient}

trait DrugService {

  def deleteDrug(id: Drug.Id)(implicit ec: ExecutionContext): Future[Int]

  def insert(distributor: Distributor)(implicit ec: ExecutionContext): Future[Distributor.Id]

  def insert(drug: Drug)(implicit ec: ExecutionContext): Future[Drug.Id]

  def insert(ingredient: Ingredient)(implicit ec: ExecutionContext): Future[Ingredient.Id]

  def getDrugs(implicit ec: ExecutionContext): Future[Seq[Drug]]

  def renameDrug(id: Drug.Id, name: String)(implicit ec: ExecutionContext): Future[Int]

}
