package parameta.demo.parameta.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import parameta.demo.parameta.dto.PersonDTO;
import parameta.demo.parameta.dto.TypeDocumentDTO;
import parameta.demo.parameta.entity.PersonEntity;
import parameta.demo.parameta.entity.TypeDocumentEntity;
import parameta.demo.parameta.repository.PersonRepository;
import parameta.demo.parameta.service.PersonService;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@Service
public class PersonServiceImpl implements PersonService {
	
private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	private final PersonRepository repository;
	
	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Optional<PersonDTO> createPerson(PersonDTO personDTO) {
		logger.info(LogHelper.start(getClass(), "createPerson"));	

		try {
			PersonEntity personEntity = convertToPersonEntity(personDTO);
			personEntity = repository.save(personEntity);
			
			logger.info(LogHelper.success(getClass(), "createPerson", String.format(LogMessages.ENTITY_SAVE_SUCCESS, personEntity.getId())));
			logger.info(LogHelper.end(getClass(), "createPerson"));
			return Optional.of(convertToPersonDTO(personEntity)); 	
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createPerson", String.format(LogMessages.ENTITY_SAVE_ERROR, e.getMessage())), e);
			logger.info(LogHelper.end(getClass(), "createPerson"));
			return Optional.empty();
		}
				
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PersonDTO> findPersonById(Long id) {
		logger.info(LogHelper.start(getClass(), "findPersonById"));			
		
		Optional<PersonEntity> person = repository.findById(id);
		PersonDTO personDTO = convertToPersonDTO(person.get());
		
		if (person.isPresent()) {
			logger.info(LogHelper.success(getClass(), "findPersonById", String.format(LogMessages.ENTITY_FOUND, id)));
			
		} else {
			logger.warn(LogHelper.warn(getClass(), "findPersonById", String.format(LogMessages.ENTITY_NOT_FOUND, id)));
		}
		logger.info(LogHelper.end(getClass(), "findPersonById"));
		return Optional.of(personDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonDTO> findAllPeople() {
		logger.info(LogHelper.start(getClass(), "findAllPeople"));	
		
		List<PersonEntity> listPerson = repository.findAll();
		List<PersonDTO> listPersonDTOs = convertToListPersonDTO(listPerson);
		if (!listPerson.isEmpty()) {
			logger.info(LogHelper.success(getClass(), "findAllPeople", String.format(LogMessages.ENTITY_FOUND, listPerson.size())));
			
		} else {
			logger.warn(LogHelper.warn(getClass(), "findAllPeople", LogMessages.ENTITY_NOT_FOUND));
		}
		logger.info(LogHelper.end(getClass(), "findAllPeople"));	
		return listPersonDTOs;
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	private PersonDTO convertToPersonDTO(PersonEntity person) {
		if (person == null) return null;	
				
		TypeDocumentDTO typeDocumentDTO = new TypeDocumentDTO(person.getTypeDocument().getId(),
				person.getTypeDocument().getCode(),
				person.getTypeDocument().getDocument());

		PersonDTO personDTO = new PersonDTO(person.getId(),
				person.getNames(),
				person.getLastNames(),
				typeDocumentDTO,
				person.getDni(),
				person.getDateOfBirth());

		return personDTO;
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	private PersonEntity convertToPersonEntity(PersonDTO personDTO) {
		if (personDTO == null) return null;
		
		TypeDocumentEntity typeDocumentEntity = new TypeDocumentEntity(personDTO.getTypeDocument().getId(),
				personDTO.getTypeDocument().getCode(), 
				personDTO.getTypeDocument().getDocument());

		PersonEntity entity = new PersonEntity(personDTO.getId(),
				personDTO.getNames(),
				personDTO.getLastNames(),
				personDTO.getDni(),
				personDTO.getDateOfBirth(),
				typeDocumentEntity,				
				null);
		
		return entity;
	}
	
	/**
	 * 
	 * @param listPerson
	 * @return
	 */
	private List<PersonDTO> convertToListPersonDTO(List<PersonEntity> listPerson) {
		if (listPerson.isEmpty()) return new ArrayList<>();		
		return listPerson.stream().map(person -> convertToPersonDTO(person)).collect(Collectors.toList());
	}

}
