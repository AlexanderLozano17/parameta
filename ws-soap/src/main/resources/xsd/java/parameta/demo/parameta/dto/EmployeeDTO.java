package parameta.demo.parameta.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import parameta.demo.parameta.util.DateUtils;

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
	
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String timeVinculation;

	public EmployeeDTO(Long id, RoleDTO roleDTO, Double salary, LocalDate dateVinculation) {
		this.id = id;
		this.roleDTO = roleDTO;
		this.salary = salary;
		this.dateVinculation = dateVinculation;
		calcularDatosDerivados();
	}
    
    public void calcularDatosDerivados() {
        if (dateVinculation != null) {            
            this.timeVinculation = DateUtils.calculateVinculationTime(dateVinculation);
        }
    }

}
