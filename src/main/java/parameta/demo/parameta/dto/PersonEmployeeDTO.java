package parameta.demo.parameta.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import parameta.demo.parameta.util.DateUtils;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PersonEmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 40)
	private String names;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Size(max = 40)
	private String lastames;
	private TypeDocumentDTO typeDocumentDTO;
	
	@NotBlank(message = "El DNI es obligatorio")
	@Column(unique = true)
	private String dni;
	
	@NotNull(message = "La fecha es obligatorio")
	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	private LocalDate dateOfBirth;
	
	@Valid
	private EmployeeDTO employeeDTO;	
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String age;

	public PersonEmployeeDTO(Long id, String names, String lastames, TypeDocumentDTO typeDocumentDTO, String dni,
			LocalDate dateOfBirth, EmployeeDTO employeeDTO) {
		super();
		this.id = id;
		this.names = names;
		this.lastames = lastames;
		this.typeDocumentDTO = typeDocumentDTO;
		this.dni = dni;
		this.dateOfBirth = dateOfBirth;
		this.employeeDTO = employeeDTO;
		calcularDatosDerivados();
	}	
	
	public void calcularDatosDerivados() {
		if (dateOfBirth != null) {
			this.age = DateUtils.calculateAge(dateOfBirth);
		}
	}
}
