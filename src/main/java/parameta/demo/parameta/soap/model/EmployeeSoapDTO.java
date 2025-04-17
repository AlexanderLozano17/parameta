package parameta.demo.parameta.soap.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
public class EmployeeSoapDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Valid
	private RoleSoapDTO role;
	
	@NotNull(message = "El salario es obligatorio")
	private Double salary;
	
	@NotNull(message = "La fecha de vinculación es obligatoria")
	private LocalDate dateVinculation;	
	
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String timeVinculation;
}
