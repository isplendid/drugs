import com.google.inject.AbstractModule
import dao._
import net.codingwell.scalaguice.ScalaModule
import services.drug.{DrugDBService, DrugService}

class Module extends AbstractModule with ScalaModule {

  override def configure() = {

    bind[DistributorDAO].to[DistributorSlickDAO]
    bind[DrugDAO].to[DrugSlickDAO]
    bind[IngredientDAO].to[IngredientSlickDAO]

    bind[DrugService].to[DrugDBService]
  }

}
