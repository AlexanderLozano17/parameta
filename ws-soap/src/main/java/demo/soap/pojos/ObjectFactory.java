//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.04.18 a las 05:28:45 PM COT 
//


package demo.soap.pojos;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the demo.soap.pojos package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: demo.soap.pojos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaveEmployeeRequest }
     * 
     */
    public SaveEmployeeRequest createSaveEmployeeRequest() {
        return new SaveEmployeeRequest();
    }

    /**
     * Create an instance of {@link PersonEmployeePojo }
     * 
     */
    public PersonEmployeePojo createPersonEmployeePojo() {
        return new PersonEmployeePojo();
    }

    /**
     * Create an instance of {@link SaveEmployeeResponse }
     * 
     */
    public SaveEmployeeResponse createSaveEmployeeResponse() {
        return new SaveEmployeeResponse();
    }

    /**
     * Create an instance of {@link TypeDocumentPojo }
     * 
     */
    public TypeDocumentPojo createTypeDocumentPojo() {
        return new TypeDocumentPojo();
    }

    /**
     * Create an instance of {@link EmployeePojo }
     * 
     */
    public EmployeePojo createEmployeePojo() {
        return new EmployeePojo();
    }

    /**
     * Create an instance of {@link RolePojo }
     * 
     */
    public RolePojo createRolePojo() {
        return new RolePojo();
    }

}
