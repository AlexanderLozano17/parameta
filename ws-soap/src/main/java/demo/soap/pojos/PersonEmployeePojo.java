//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.04.18 a las 05:28:45 PM COT 
//


package demo.soap.pojos;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PersonEmployeePojo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PersonEmployeePojo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="names" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="lastNames" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="typeDocument" type="{http://localhost:8081/soap/employee}TypeDocumentPojo"/&gt;
 *         &lt;element name="dni" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="employee" type="{http://localhost:8081/soap/employee}EmployeePojo"/&gt;
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonEmployeePojo", propOrder = {
    "id",
    "names",
    "lastNames",
    "typeDocument",
    "dni",
    "dateOfBirth",
    "employee",
    "age"
})
public class PersonEmployeePojo {

    protected long id;
    @XmlElement(required = true)
    protected String names;
    @XmlElement(required = true)
    protected String lastNames;
    @XmlElement(required = true)
    protected TypeDocumentPojo typeDocument;
    @XmlElement(required = true)
    protected String dni;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfBirth;
    @XmlElement(required = true)
    protected EmployeePojo employee;
    @XmlElement(required = true)
    protected String age;

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
     * Obtiene el valor de la propiedad names.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNames() {
        return names;
    }

    /**
     * Define el valor de la propiedad names.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNames(String value) {
        this.names = value;
    }

    /**
     * Obtiene el valor de la propiedad lastNames.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastNames() {
        return lastNames;
    }

    /**
     * Define el valor de la propiedad lastNames.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastNames(String value) {
        this.lastNames = value;
    }

    /**
     * Obtiene el valor de la propiedad typeDocument.
     * 
     * @return
     *     possible object is
     *     {@link TypeDocumentPojo }
     *     
     */
    public TypeDocumentPojo getTypeDocument() {
        return typeDocument;
    }

    /**
     * Define el valor de la propiedad typeDocument.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDocumentPojo }
     *     
     */
    public void setTypeDocument(TypeDocumentPojo value) {
        this.typeDocument = value;
    }

    /**
     * Obtiene el valor de la propiedad dni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDni() {
        return dni;
    }

    /**
     * Define el valor de la propiedad dni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDni(String value) {
        this.dni = value;
    }

    /**
     * Obtiene el valor de la propiedad dateOfBirth.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Define el valor de la propiedad dateOfBirth.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfBirth(XMLGregorianCalendar value) {
        this.dateOfBirth = value;
    }

    /**
     * Obtiene el valor de la propiedad employee.
     * 
     * @return
     *     possible object is
     *     {@link EmployeePojo }
     *     
     */
    public EmployeePojo getEmployee() {
        return employee;
    }

    /**
     * Define el valor de la propiedad employee.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeePojo }
     *     
     */
    public void setEmployee(EmployeePojo value) {
        this.employee = value;
    }

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
}
