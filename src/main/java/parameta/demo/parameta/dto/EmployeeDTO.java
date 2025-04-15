package parameta.demo.parameta.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	@Valid
	private RoleDTO roleDTO;
	
	@NotNull(message = "El salario es obligatorio")
	private Double salary;
	
	@NotNull(message = "La fecha de vinculación es obligatoria")
	private LocalDate dateVinculation;	
}
