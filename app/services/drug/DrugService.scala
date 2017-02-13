package services.drug

import scala.concurrent.{ExecutionContext, Future}
import model.Ingredient

trait DrugService {

  def getIngredientsByDrugName(name: String)(implicit ec: ExecutionContext): Future[Seq[Ingredient]]
}
