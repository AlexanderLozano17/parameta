package parameta.demo.parameta.service;

import java.util.List;
import java.util.Optional;

import parameta.demo.parameta.dto.PersonDTO;

public interface PersonService {

	Optional<PersonDTO> createPerson(PersonDTO personDTO);
	
	Optional<PersonDTO> findPersonById(Long Id);
	
	List<PersonDTO> findAllPeople();
	
}
