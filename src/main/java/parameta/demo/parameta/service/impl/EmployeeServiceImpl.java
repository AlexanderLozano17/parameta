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
import parameta.demo.parameta.repository.TypeDocumentRepository;
import parameta.demo.parameta.service.EmployeeService;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private final PersonRepository personRepository;
	private final TypeDocumentRepository documentReposiroty;
	
	public EmployeeServiceImpl(PersonRepository personRepository, TypeDocumentRepository documentReposiroty) {
		this.personRepository = personRepository;
		this.documentReposiroty = documentReposiroty;
	}
	
	@Override
	@Transactional
	public PersonEmployeeDTO createEmployee(PersonEmployeeDTO personEmployeeDTO) {
		logger.info(LogHelper.start(getClass(), "createEmployee"));
		
	    if (personEmployeeDTO == null) {
	        throw new IllegalArgumentException("El objeto PersonEmployeeDTO no puede ser nulo");
	    }

	    
	    // 7. Guardar el empleado
	    employeeRepository.save(employee);

	    // 8. Mapear de vuelta a DTO
	    return mapper.toPersonEmployeeDTO(person, employee); // tú defines el método de mapeo

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
	 * @param personEntity
	 * @return
	 */
	private PersonEmployeeDTO convertToPersonEmployeeDTO(PersonEntity personEntity) {
	    if (personEntity == null) {
	    	logger.info(LogHelper.warn(getClass(), "convertToPersonEmployeeDTO", LogMessages.ENTITY_NOT_FOUND));
	    	return null;
	    }

	    // Convertir tipo de documento
	    TypeDocumentDTO documentDTO = null;
	    if (personEntity.getTypeDocument() != null) {
	        documentDTO = new TypeDocumentDTO(
	            personEntity.getTypeDocument().getId(),
	            personEntity.getTypeDocument().getCode(),
	            personEntity.getTypeDocument().getDocument()
	        );
	    }

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

	    return new PersonEmployeeDTO(personEntity.getId(),
	        personEntity.getNames(),
	        personEntity.getLastames(),
	        documentDTO,
	        personEntity.getDni(),
	        personEntity.getDateOfBirth(),
	        employeeDTO);
	}
	
	/**
	 * 
	 * @param personEmployeeDTO
	 * @return
	 */
	private PersonEntity savePerson(PersonEmployeeDTO personEmployeeDTO) {
	
	    TypeDocumentEntity document = documentReposiroty.findById(personEmployeeDTO.getTypeDocument().getId())
	        .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
	    
	    PersonEntity person = new PersonEntity(personEmployeeDTO.getNames(), 
	    		personEmployeeDTO.getLastames(), 
	    		document, 
	    		personEmployeeDTO.getDni(), 
	    		personEmployeeDTO.getDateOfBirth());


	    person = personRepository.save(person);
	    
	    EmployeeEntity employee = new EmployeeEntity(person, );
	    
	    employee.setPosition(dto.getPosition());
	    employee.setSalary(dto.getSalary());
	    employee.setEntryDate(dto.getEntryDate());
	    employee.setPerson(person); // Relación OneToOne

	    // 6. Asociar Rol si lo tienes
	    if (dto.getRole() != null && dto.getRole().getId() != null) {
	        RoleEntity role = roleRepository.findById(dto.getRole().getId())
	            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
	        employee.setRole(role);
	    }
	   

	}

}
