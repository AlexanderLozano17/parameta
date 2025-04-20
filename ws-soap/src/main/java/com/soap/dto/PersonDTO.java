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
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String dni;
    private String names;
    private String lastNames;
    private TypeDocumentDTO typeDocument;
    private LocalDate dateOfBirth;
    private String age;
    
	public PersonDTO(Long id, String dni, String names, String lastNames, TypeDocumentDTO typeDocument,
			LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.dni = dni;
		this.names = names;
		this.lastNames = lastNames;
		this.typeDocument = typeDocument;
		this.dateOfBirth = dateOfBirth;
		calcularDatosDerivados();
	}
    
	
	public void calcularDatosDerivados() {
		if (dateOfBirth != null) {
			this.age = FunctionUtils.calculateAge(dateOfBirth);
		}
	}	
}
