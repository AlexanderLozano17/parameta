
package parameta.demo.ws.client.pojo;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EmployeePortService", targetNamespace = "http://localhost:8081/soap/employee", wsdlLocation = "http://localhost:8081/ws/employee.wsdl")
public class EmployeePortService extends Service {

    private final static URL EMPLOYEEPORTSERVICE_WSDL_LOCATION;
    private final static WebServiceException EMPLOYEEPORTSERVICE_EXCEPTION;
    private final static QName EMPLOYEEPORTSERVICE_QNAME = new QName("http://localhost:8081/soap/employee", "EmployeePortService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/ws/employee.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EMPLOYEEPORTSERVICE_WSDL_LOCATION = url;
        EMPLOYEEPORTSERVICE_EXCEPTION = e;
    }

    public EmployeePortService() {
        super(__getWsdlLocation(), EMPLOYEEPORTSERVICE_QNAME);
    }

    public EmployeePortService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EMPLOYEEPORTSERVICE_QNAME, features);
    }

    public EmployeePortService(URL wsdlLocation) {
        super(wsdlLocation, EMPLOYEEPORTSERVICE_QNAME);
    }

    public EmployeePortService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EMPLOYEEPORTSERVICE_QNAME, features);
    }

    public EmployeePortService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmployeePortService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EmployeePort
     */
    @WebEndpoint(name = "EmployeePortSoap11")
    public EmployeePort getEmployeePortSoap11() {
        return super.getPort(new QName("http://localhost:8081/soap/employee", "EmployeePortSoap11"), EmployeePort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmployeePort
     */
    @WebEndpoint(name = "EmployeePortSoap11")
    public EmployeePort getEmployeePortSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost:8081/soap/employee", "EmployeePortSoap11"), EmployeePort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EMPLOYEEPORTSERVICE_EXCEPTION!= null) {
            throw EMPLOYEEPORTSERVICE_EXCEPTION;
        }
        return EMPLOYEEPORTSERVICE_WSDL_LOCATION;
    }

}
