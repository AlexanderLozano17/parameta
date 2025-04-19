package parameta.demo.parameta.service;

import java.util.Optional;

import parameta.demo.parameta.dto.PersonEmployeeDTO;

public interface EmployeeService {

	Optional<PersonEmployeeDTO> createEmployee(PersonEmployeeDTO personEmployeeDTO);
	
	Optional<PersonEmployeeDTO> findEmployeedByIdPerson(Long personId);
}
