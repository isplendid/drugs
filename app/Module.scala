import com.google.inject.AbstractModule
import dao._
import net.codingwell.scalaguice.ScalaModule
import org.apache.cxf.transport.play.CtxConfigurator
import org.springframework.context.support.ClassPathXmlApplicationContext
import services.drug.{DrugDBService, DrugService}

class Module extends AbstractModule with ScalaModule {

  override def configure() = {
    val ctx = new ClassPathXmlApplicationContext("applicationContext.xml")

    bind(classOf[ClassPathXmlApplicationContext])
      .toInstance(ctx)

    val DrugsSoapController = ctx.getBean("DrugsServiceImplBean")
    requestInjection(DrugsSoapController)

    bind[CtxConfigurator].to[SoapConfigurator]

    bind[DistributorDAO].to[DistributorSlickDAO]
    bind[DrugDAO].to[DrugSlickDAO]
    bind[IngredientDAO].to[IngredientSlickDAO]

    bind[DrugService].to[DrugDBService]
  }

}
