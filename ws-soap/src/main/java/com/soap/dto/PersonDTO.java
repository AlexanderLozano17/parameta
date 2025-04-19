package com.soap.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
}
