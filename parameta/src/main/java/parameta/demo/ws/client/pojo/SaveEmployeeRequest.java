
package parameta.demo.ws.client.pojo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personEmployee" type="{http://producer-soap:8081/soap/employee}PersonEmployeePojo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personEmployee"
})
@XmlRootElement(name = "saveEmployeeRequest")
public class SaveEmployeeRequest {

    @XmlElement(required = true)
    protected PersonEmployeePojo personEmployee;

    /**
     * Obtiene el valor de la propiedad personEmployee.
     * 
     * @return
     *     possible object is
     *     {@link PersonEmployeePojo }
     *     
     */
    public PersonEmployeePojo getPersonEmployee() {
        return personEmployee;
    }

    /**
     * Define el valor de la propiedad personEmployee.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonEmployeePojo }
     *     
     */
    public void setPersonEmployee(PersonEmployeePojo value) {
        this.personEmployee = value;
    }

}
