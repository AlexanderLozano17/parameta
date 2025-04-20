package parameta.demo.parameta.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import parameta.demo.parameta.dto.EmployeeDTO;
import parameta.demo.parameta.dto.PersonEmployeeDTO;
import parameta.demo.parameta.dto.RoleDTO;
import parameta.demo.parameta.dto.TypeDocumentDTO;
import parameta.demo.parameta.entity.EmployeeEntity;
import parameta.demo.parameta.entity.PersonEntity;
import parameta.demo.parameta.entity.RoleEntity;
import parameta.demo.parameta.entity.TypeDocumentEntity;
import parameta.demo.parameta.repository.PersonRepository;
import parameta.demo.parameta.service.EmployeeService;
import parameta.demo.parameta.util.ApiMessages;
import parameta.demo.parameta.util.DateUtils;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;
import parameta.demo.ws.client.pojo.EmployeePojo;
import parameta.demo.ws.client.pojo.EmployeePort;
import parameta.demo.ws.client.pojo.EmployeePortService;
import parameta.demo.ws.client.pojo.PersonEmployeePojo;
import parameta.demo.ws.client.pojo.RolePojo;
import parameta.demo.ws.client.pojo.SaveEmployeeRequest;
import parameta.demo.ws.client.pojo.SaveEmployeeResponse;
import parameta.demo.ws.client.pojo.TypeDocumentPojo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private final PersonRepository personRepository;
	
	public EmployeeServiceImpl(PersonRepository personRepository) {		
		this.personRepository = personRepository;
	}
	
	@Override
	@Transactional
	public Optional<SaveEmployeeResponse> createEmployee(PersonEmployeeDTO personEmployeeDTO) {
		logger.info(LogHelper.start(getClass(), "createEmployee"));
		SaveEmployeeResponse response = null;
		
		try {
			EmployeePortService serviceWs = new EmployeePortService();			
			EmployeePort port = serviceWs.getEmployeePortSoap11();
			
			// Construye la request y envia la petición
			SaveEmployeeRequest request = convertToSaveEmployeeRequest(personEmployeeDTO);
			
	        response = port.saveEmployee(request);	 
	        return Optional.of(response);
	        
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createEmployee", e.getMessage()), e);
			response.setStatus(ApiMessages.ERROR);
			response.setMessage(ApiMessages.INTERNAL_SERVER_ERROR);			
		}
		
		logger.info(LogHelper.end(getClass(), "createEmployee"));
		 return Optional.of(response);
	    
	}

	@Override
	public Optional<PersonEmployeeDTO> findEmployeedByIdPerson(Long personId) {
		logger.info(LogHelper.start(getClass(), "findEmployeedByIdPerson"));
		
		Optional<PersonEntity> personEntity = personRepository.findById(personId);
		PersonEmployeeDTO personEmployeeDTO = convertToPersonEmployeeDTO(personEntity.get());
		
		logger.info(LogHelper.end(getClass(), "findEmployeedByIdPerson"));
		return Optional.of(personEmployeeDTO);
	}

	/**
	 * 
	 * @param personEmployee
	 * @return
	 */
	private SaveEmployeeRequest convertToSaveEmployeeRequest(PersonEmployeeDTO personEmployee) {
		
		TypeDocumentPojo typeDocumentPojo = new TypeDocumentPojo();
		typeDocumentPojo.setId(personEmployee.getTypeDocumentDTO().getId());
		typeDocumentPojo.setCode(personEmployee.getTypeDocumentDTO().getCode());
		typeDocumentPojo.setDocument(personEmployee.getTypeDocumentDTO().getDocument());		
		
		RolePojo rolePojo = new RolePojo();
		rolePojo.setId(personEmployee.getEmployeeDTO().getRoleDTO().getId());
		rolePojo.setRol(personEmployee.getEmployeeDTO().getRoleDTO().getRol());
		
		EmployeePojo employeePojo = new EmployeePojo();
		employeePojo.setRole(rolePojo);
		employeePojo.setSalary(personEmployee.getEmployeeDTO().getSalary());
		employeePojo.setDateVinculation(DateUtils.toXmlGregorianCalendar(personEmployee.getEmployeeDTO().getDateVinculation()));
		
		PersonEmployeePojo personEmployeePojo = new PersonEmployeePojo();
		personEmployeePojo.setNames(personEmployee.getNames());
		personEmployeePojo.setLastNames(personEmployee.getLastNames());
		personEmployeePojo.setTypeDocument(typeDocumentPojo);
		personEmployeePojo.setDni(personEmployee.getDni());
		personEmployeePojo.setDateOfBirth(DateUtils.toXmlGregorianCalendar(personEmployee.getDateOfBirth()));
		personEmployeePojo.setEmployee(employeePojo);
		
		SaveEmployeeRequest request = new SaveEmployeeRequest();	
		request.setPersonEmployee(personEmployeePojo); 
		return request;	 
	}
	
	/**
	 * 
	 * @param personEntity
	 * @return
	 */
	private PersonEmployeeDTO convertToPersonEmployeeDTO(PersonEntity personEntity) {
		logger.info(LogHelper.start(getClass(), "convertToPersonEmployeeDTO"));
		
	    if (personEntity == null) {
	    	logger.info(LogHelper.warn(getClass(), "convertToPersonEmployeeDTO", LogMessages.ENTITY_NOT_FOUND));
	    	return null;
	    }

	    // Convertir tipo de documento
	    TypeDocumentDTO documentDTO = convertToTypeDocumentDTO(personEntity.getTypeDocument());
	    
	    // Convertir rol
	    RoleDTO roleDTO = null;
	    if (personEntity.getEmployee() != null && personEntity.getEmployee().getRole() != null) {
	    	RoleEntity role = personEntity.getEmployee().getRole();
	        roleDTO = new RoleDTO(role.getId(), role.getRol());
	    }

	    // Convertir empleado
	    EmployeeDTO employeeDTO = null;
	    if (personEntity.getEmployee() != null) {
	        EmployeeEntity employee = personEntity.getEmployee();
	        employeeDTO = new EmployeeDTO(employee.getId(),
	            roleDTO,	           
	            employee.getSalary(),
	            employee.getDateVinculation());
	    }

	    logger.info(LogHelper.end(getClass(), "convertToPersonEmployeeDTO"));
	    return new PersonEmployeeDTO(personEntity.getId(),
	        personEntity.getNames(),
	        personEntity.getLastNames(),
	        documentDTO,
	        personEntity.getDni(),
	        personEntity.getDateOfBirth(),
	        employeeDTO);
	}
	
	/**
	 * 
	 * @param typeDocumentEntity
	 * @return
	 */
	private TypeDocumentDTO convertToTypeDocumentDTO(TypeDocumentEntity typeDocumentEntity) {
	    TypeDocumentDTO documentDTO = null;
	    if (typeDocumentEntity != null) {
	        documentDTO = new TypeDocumentDTO(
	        		typeDocumentEntity.getId(),
	        		typeDocumentEntity.getCode(),
	        		typeDocumentEntity.getDocument()
	        );
	    }
	    return documentDTO;  		
	}
}
