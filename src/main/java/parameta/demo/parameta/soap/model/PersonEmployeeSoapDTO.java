package parameta.demo.parameta.soap.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import parameta.demo.parameta.util.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
public class PersonEmployeeSoapDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 40)
	private String names;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Size(max = 40)
	private String lastames;
	
	@Valid
	private TypeDocumentSoapDTO typeDocument;
	
	@NotBlank(message = "El DNI es obligatorio")
	@Column(unique = true)
	private String dni;
	
	@NotNull(message = "La fecha es obligatorio")
	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dateOfBirth;
	
	@Valid
	private EmployeeSoapDTO employee;	
	
	private String age;

}
