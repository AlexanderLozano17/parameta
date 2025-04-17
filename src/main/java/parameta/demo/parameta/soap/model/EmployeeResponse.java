package parameta.demo.parameta.soap.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class EmployeeResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private String status; 
    private String message; 
    private PersonEmployeeSoapDTO data;

}
