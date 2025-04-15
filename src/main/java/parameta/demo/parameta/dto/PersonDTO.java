package parameta.demo.parameta.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 40)
	private String names;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Size(max = 40)
	private String lastames;
	private TypeDocumentDTO typeDocument;
	
	@NotBlank(message = "El DNI es obligatorio")
	@Column(unique = true)
	private String dni;
	
	@NotNull(message = "La fecha es obligatorio")
	private LocalDate dateOfBirth;
}
