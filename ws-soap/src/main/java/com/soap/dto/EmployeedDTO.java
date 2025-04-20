package com.soap.dto;

import java.io.Serializable;
import java.time.LocalDate;

import demo.soap.util.FunctionUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeedDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long personId;
	private LocalDate datevinculation;
	private double salary;
	private RoleDTO role;
	private String timeVinculation;
		
	public EmployeedDTO(Long id, Long personId, LocalDate datevinculation, double salary, RoleDTO role) {
		super();
		this.id = id;
		this.personId = personId;
		this.datevinculation = datevinculation;
		this.salary = salary;
		this.role = role;
		calcularDatosDerivados();
	}
	
    public void calcularDatosDerivados() {
        if (datevinculation != null) {            
            this.timeVinculation = FunctionUtils.calculateVinculationTime(datevinculation);
        }
    }
}
