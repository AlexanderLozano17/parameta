
package parameta.demo.ws.client.pojo;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EmployeePojo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EmployeePojo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="role" type="{http://localhost:8081/soap/employee}RolePojo"/&gt;
 *         &lt;element name="salary" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dateVinculation" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="timeVinculation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeePojo", propOrder = {
    "id",
    "role",
    "salary",
    "dateVinculation",
    "timeVinculation"
})
public class EmployeePojo {

    protected long id;
    @XmlElement(required = true)
    protected RolePojo role;
    protected double salary;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateVinculation;
    @XmlElement(required = true)
    protected String timeVinculation;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad role.
     * 
     * @return
     *     possible object is
     *     {@link RolePojo }
     *     
     */
    public RolePojo getRole() {
        return role;
    }

    /**
     * Define el valor de la propiedad role.
     * 
     * @param value
     *     allowed object is
     *     {@link RolePojo }
     *     
     */
    public void setRole(RolePojo value) {
        this.role = value;
    }

    /**
     * Obtiene el valor de la propiedad salary.
     * 
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Define el valor de la propiedad salary.
     * 
     */
    public void setSalary(double value) {
        this.salary = value;
    }

    /**
     * Obtiene el valor de la propiedad dateVinculation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateVinculation() {
        return dateVinculation;
    }

    /**
     * Define el valor de la propiedad dateVinculation.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateVinculation(XMLGregorianCalendar value) {
        this.dateVinculation = value;
    }

    /**
     * Obtiene el valor de la propiedad timeVinculation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeVinculation() {
        return timeVinculation;
    }

    /**
     * Define el valor de la propiedad timeVinculation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeVinculation(String value) {
        this.timeVinculation = value;
    }

}
