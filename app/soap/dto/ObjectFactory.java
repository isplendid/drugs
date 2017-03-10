
package soap.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Drugs_QNAME = new QName("http://drugservice.hu", "Drugs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DrugListType }
     * 
     */
    public DrugListType createDrugListType() {
        return new DrugListType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DrugListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://drugservice.hu", name = "Drugs")
    public JAXBElement<DrugListType> createDrugs(DrugListType value) {
        return new JAXBElement<DrugListType>(_Drugs_QNAME, DrugListType.class, null, value);
    }

}
