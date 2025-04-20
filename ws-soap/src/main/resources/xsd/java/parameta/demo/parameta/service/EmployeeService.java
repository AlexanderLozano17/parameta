package parameta.demo.parameta.service;

import java.util.Optional;

import parameta.demo.parameta.dto.PersonEmployeeDTO;
import parameta.demo.ws.client.pojo.SaveEmployeeResponse;

public interface EmployeeService {

	Optional<SaveEmployeeResponse> createEmployee(PersonEmployeeDTO personEmployeeDTO);
	
	Optional<PersonEmployeeDTO> findEmployeedByIdPerson(Long personId);
}
