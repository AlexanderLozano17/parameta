package com.soap.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonEmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String names;
	private String lastNames;
	private TypeDocumentDTO typeDocumentDTO;
	private String dni;
	private LocalDate dateOfBirth;
	private EmployeedDTO employeeDTO;
	private String age;

}
