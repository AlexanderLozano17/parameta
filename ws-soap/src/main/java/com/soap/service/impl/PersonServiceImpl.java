package com.soap.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.soap.dto.PersonDTO;
import com.soap.dto.TypeDocumentDTO;
import com.soap.entity.PersonEntity;
import com.soap.entity.TypeDocumentEntity;
import com.soap.repository.PersonRepository;
import com.soap.service.PersonService;

import demo.soap.util.LogHelper;

@Service
public class PersonServiceImpl implements PersonService {

	private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	private final PersonRepository repository;

	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<PersonDTO> findPersonByDni(String dni) {
		logger.info(LogHelper.start(getClass(), "findPersonByDni"));
		
		try {		
			Optional<PersonEntity> person = repository.findByDni(dni);

			logger.info(LogHelper.end(getClass(), "findPersonByDni"));
			if (person.isEmpty()) {
				return Optional.empty();
			}
			
			return Optional.of(new PersonDTO(person.get().getId(), 
					person.get().getDni(), 
					person.get().getNames(),
					person.get().getLastNames(),
					new TypeDocumentDTO(person.get().getTypeDocument().getId(), 
							person.get().getTypeDocument().getCode(), 
							person.get().getTypeDocument().getDocument()),
					person.get().getDateOfBirth()));

		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "findPersonByDni", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "findPersonByDni"));
			return Optional.empty();
		}
	}
}
