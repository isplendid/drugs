package soap

import javax.jws.WebService

import com.google.inject.Inject
import play.api.libs.concurrent.Execution.Implicits._

import services.drug.DrugService
import soap.dto.{DrugListType, DrugSoapAPI}

import util.Util.AwaitResult

@WebService(name = "DrugSoapApi", serviceName = "DrugSoapApi", targetNamespace = "http://drugservice.hu", endpointInterface = "soap.dto.DrugSoapAPI")
class SoapController extends DrugSoapAPI with ServiceUtil {

  @Inject
  implicit val service: DrugService = null

  override def all(): DrugListType = AwaitResult {
    service.getDrugs.map { drugs =>
      val ret = new DrugListType
      val list = ret.getDrugs
      drugs.foreach(d => list.add(d.name))
      ret
    }
  }

}