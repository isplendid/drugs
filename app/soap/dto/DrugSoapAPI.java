
package soap.dto;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DrugSoapAPI", targetNamespace = "http://drugservice.hu")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DrugSoapAPI {


    /**
     * 
     * @return
     *     returns soap.dto.DrugListType
     */
    @WebMethod
    @WebResult(name = "Drugs", targetNamespace = "http://drugservice.hu", partName = "parameters")
    public DrugListType all();

}
