package parameta.demo.parameta.soap.endpoint;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import parameta.demo.parameta.dto.EmployeeDTO;
import parameta.demo.parameta.dto.PersonEmployeeDTO;
import parameta.demo.parameta.dto.RoleDTO;
import parameta.demo.parameta.dto.TypeDocumentDTO;
import parameta.demo.parameta.service.EmployeeService;
import parameta.demo.parameta.soap.model.EmployeeResponse;
import parameta.demo.parameta.soap.model.EmployeeSoapDTO;
import parameta.demo.parameta.soap.model.PersonEmployeeSoapDTO;
import parameta.demo.parameta.soap.model.RoleSoapDTO;
import parameta.demo.parameta.soap.model.TypeDocumentSoapDTO;
import parameta.demo.parameta.util.ApiMessages;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@Endpoint
public class EmployeeEndpoint {
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);


	private static final String NAMESPACE_URI = "http://localhost:8080/soap/employee";
	private final EmployeeService service;
	
	public EmployeeEndpoint(EmployeeService service) {
		this.service = service;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployee")
	@ResponsePayload
	public EmployeeResponse saveEmployee(@RequestPayload PersonEmployeeSoapDTO request) {
		logger.info(LogHelper.start(getClass(), "saveEmployee"));
		
		// Aquí haces la lógica para guardar
		PersonEmployeeDTO personEmployeeDTO = convertToPersonEmployeeDTO(request);
		
		Optional<PersonEmployeeDTO> personEmployeeOpt = service.createEmployee(personEmployeeDTO);
		
		EmployeeResponse response = new EmployeeResponse();
		
		if (personEmployeeOpt.isPresent()) {	
			logger.info(LogHelper.success(getClass(), "saveEmployee", String.format(LogMessages.ENTITY_SAVE_SUCCESS, personEmployeeOpt.get().getId())));
			response.setStatus(ApiMessages.SUCCESS);
			response.setMessage(ApiMessages.SAVE_SUCCESS);
			response.setData(convertToPersonEmployeeSoapDTO(personEmployeeOpt.get()));
			
		} else {
			logger.error(LogHelper.error(getClass(), "saveEmployee", LogMessages.ENTITY_SAVE_ERROR));
			response.setStatus(ApiMessages.ERROR);
			response.setMessage(ApiMessages.SAVE_ERROR);
			response.setData(null);			
		}
		logger.info(LogHelper.end(getClass(), "saveEmployee"));
		return response;		
	}
	
	/**
	 * 
	 * @param employeeDTO
	 * @return
	 */
	private PersonEmployeeSoapDTO convertToPersonEmployeeSoapDTO(PersonEmployeeDTO employeeDTO) {
		
		TypeDocumentSoapDTO documentSoapDTO = new TypeDocumentSoapDTO();
		documentSoapDTO.setId(employeeDTO.getTypeDocumentDTO().getId());
		documentSoapDTO.setCode(employeeDTO.getTypeDocumentDTO().getCode());
		documentSoapDTO.setDocument(employeeDTO.getTypeDocumentDTO().getDocument());				
	
		RoleSoapDTO roleSoapDto = new RoleSoapDTO();
		roleSoapDto.setId(employeeDTO.getEmployeeDTO().getRoleDTO().getId());
		roleSoapDto.setRol(employeeDTO.getEmployeeDTO().getRoleDTO().getRol());
		
		EmployeeSoapDTO employeeSoapDTo = new EmployeeSoapDTO();
		employeeSoapDTo.setId(employeeDTO.getEmployeeDTO().getId());
		employeeSoapDTo.setSalary(employeeDTO.getEmployeeDTO().getSalary());
		employeeSoapDTo.setDateVinculation(employeeDTO.getEmployeeDTO().getDateVinculation());
		employeeSoapDTo.setRole(roleSoapDto);		
		
		PersonEmployeeSoapDTO personEmployeeSoapDto = new PersonEmployeeSoapDTO(); 
		personEmployeeSoapDto.setId(employeeDTO.getId());
		personEmployeeSoapDto.setNames(employeeDTO.getNames());
		personEmployeeSoapDto.setLastames(employeeDTO.getLastames());
		personEmployeeSoapDto.setDni(employeeDTO.getDni());
		personEmployeeSoapDto.setDateOfBirth(employeeDTO.getDateOfBirth());
		personEmployeeSoapDto.setTypeDocument(documentSoapDTO);
		personEmployeeSoapDto.setEmployee(employeeSoapDTo);
		return personEmployeeSoapDto;
	}
	
	/**
	 * 
	 * @param employeeSoapDTO
	 * @return
	 */
	private PersonEmployeeDTO convertToPersonEmployeeDTO(PersonEmployeeSoapDTO employeeSoapDTO) {
		
		if (employeeSoapDTO.getTypeDocument() == null) {
		    throw new IllegalArgumentException("El tipo de documento no puede ser null");
		}
		
		TypeDocumentDTO documentDTO = new TypeDocumentDTO();
		documentDTO.setId(employeeSoapDTO.getTypeDocument().getId());
		documentDTO.setCode(employeeSoapDTO.getTypeDocument().getCode());
		documentDTO.setDocument(employeeSoapDTO.getTypeDocument().getDocument());				
	
		if (employeeSoapDTO.getEmployee().getRole() == null) {
		    throw new IllegalArgumentException("El tipo de rol no puede ser null");
		}
		
		RoleDTO roleDto = new RoleDTO();
		roleDto.setId(employeeSoapDTO.getEmployee().getRole().getId());
		roleDto.setRol(employeeSoapDTO.getEmployee().getRole().getRol());
		
		if (employeeSoapDTO.getEmployee() == null) {
		    throw new IllegalArgumentException("El campo 'employee' no puede ser null");
		}
		
		EmployeeDTO employeeDTo = new EmployeeDTO();
		employeeDTo.setId(employeeSoapDTO.getEmployee().getId());
		employeeDTo.setSalary(employeeSoapDTO.getEmployee().getSalary());
		employeeDTo.setDateVinculation(employeeSoapDTO.getEmployee().getDateVinculation());
		employeeDTo.setRoleDTO(roleDto);		
		
		PersonEmployeeDTO personEmployeeDto = new PersonEmployeeDTO(); 
		personEmployeeDto.setId(employeeSoapDTO.getId());
		personEmployeeDto.setNames(employeeSoapDTO.getNames());
		personEmployeeDto.setLastames(employeeSoapDTO.getLastames());
		personEmployeeDto.setDni(employeeSoapDTO.getDni());
		personEmployeeDto.setDateOfBirth(employeeSoapDTO.getDateOfBirth());
		personEmployeeDto.setTypeDocumentDTO(documentDTO);
		personEmployeeDto.setEmployeeDTO(employeeDTo);
		return personEmployeeDto;
	}
}
