package com.soap.repository.service;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.soap.dto.PersonDTO;

public interface PersonRepositoryService {

	Optional<PersonDTO> findPersonByDni(String dni);
}
