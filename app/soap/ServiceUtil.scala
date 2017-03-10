package soap

import javax.annotation.Resource
import javax.xml.ws.WebServiceContext

trait ServiceUtil {
  @Resource
  var wsContext: WebServiceContext = null

  def remoteAddr: String = {
    val mc = wsContext.getMessageContext
    mc.get("Remote-Address").asInstanceOf[String]
  }

}
object ServiceUtil extends ServiceUtil
