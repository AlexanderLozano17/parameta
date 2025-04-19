package com.soap.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.soap.dto.PersonDTO;
import com.soap.dto.TypeDocumentDTO;
import com.soap.repository.service.PersonRepositoryService;

import demo.soap.util.LogHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class PersonRepository implements PersonRepositoryService {

	private final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<PersonDTO> findPersonByDni(String dni) {
	    logger.info(LogHelper.start(getClass(), "findPersonByDni"));

	    try {
	        String sql = """
	            SELECT p.id, p.date_of_birth, p.dni, p.last_names, p.names,
	                   td.id AS td_id, td.code AS td_code, td.document AS td_document
	            FROM person p
	            INNER JOIN type_document td ON p.type_document_id = td.id
	            WHERE p.dni = :dni
	        """;

	        Object[] result = (Object[]) entityManager
	            .createNativeQuery(sql)
	            .setParameter("dni", dni)
	            .getSingleResult();

	        PersonDTO person = new PersonDTO();
	        person.setId(((Number) result[0]).longValue());
	        person.setDateOfBirth(((java.sql.Date) result[1]).toLocalDate());
	        person.setDni((String) result[2]);
	        person.setLastNames((String) result[3]);
	        person.setNames((String) result[4]);

	        TypeDocumentDTO typeDocument = new TypeDocumentDTO();
	        typeDocument.setId(((Number) result[5]).longValue());
	        typeDocument.setCode((String) result[6]);
	        typeDocument.setDocument((String) result[7]);
	        person.setTypeDocument(typeDocument);

	        logger.info(LogHelper.end(getClass(), "findPersonByDni"));
	        return Optional.of(person);

	    } catch (NoResultException e) {
	        logger.warn("No person found with DNI: {}", dni);
	        return Optional.empty();
	    } catch (Exception e) {
	        logger.error(LogHelper.error(getClass(), "findPersonByDni", e.getMessage()), e);
	        return Optional.empty();
	    }
	}

}
