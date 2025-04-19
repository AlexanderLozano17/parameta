package com.soap.service;

import java.util.Optional;

import com.soap.dto.PersonDTO;

import demo.soap.pojos.PersonEmployeePojo;

public interface PersonService {

	Optional<PersonDTO> findPersonByDni(String dni);
	
}
