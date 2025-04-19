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
import parameta.demo.parameta.repository.EmployeeRepository;
import parameta.demo.parameta.repository.PersonRepository;
import parameta.demo.parameta.repository.RoleRepository;
import parameta.demo.parameta.repository.TypeDocumentRepository;
import parameta.demo.parameta.service.EmployeeService;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private final PersonRepository personRepository;
	private final TypeDocumentRepository documentReposiroty;
	private final RoleRepository rolReposiroty;
	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(PersonRepository personRepository, TypeDocumentRepository documentReposiroty,
			RoleRepository rolReposiroty, EmployeeRepository employeeRepository) {
		
		this.personRepository = personRepository;
		this.documentReposiroty = documentReposiroty;
		this.rolReposiroty = rolReposiroty;
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	@Transactional
	public Optional<PersonEmployeeDTO> createEmployee(PersonEmployeeDTO personEmployeeDTO) {
		logger.info(LogHelper.start(getClass(), "createEmployee"));

	    try {	        
	        PersonEmployeeDTO savedDTO = processSavePerson(personEmployeeDTO);

  	        logger.info(LogHelper.success(getClass(), "createEmployee", LogMessages.ENTITY_SAVE_SUCCESS));
  	        logger.info(LogHelper.end(getClass(), "createEmployee"));
  	        return Optional.of(savedDTO);

	    } catch (Exception e) {
	        logger.error(LogHelper.error(getClass(), "createEmployee", String.format(LogMessages.ENTITY_SAVE_ERROR, e.getMessage())), e);
	        logger.info(LogHelper.end(getClass(), "createEmployee"));
	        return Optional.empty();
	    }
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
	
	/**
	 * 
	 * @param personEmployeeDTO
	 * @return
	 */
	private PersonEmployeeDTO processSavePerson(PersonEmployeeDTO personEmployeeDTO) {
		logger.info(LogHelper.start(getClass(), "processSavePerson"));		
		
		// Validar si la persona ya existe por DNI
	    Optional<PersonEntity> optionalPerson = personRepository.findByDni(personEmployeeDTO.getDni());
	    PersonEntity personEntity;
		
	    if (optionalPerson.isPresent()) {
	        personEntity = optionalPerson.get();
	    } else {
	        personEntity = savePerson(personEmployeeDTO).orElseThrow(() ->
	                new RuntimeException("No se pudo guardar la persona"));
	    }
		
	    // Verificar si ya tiene un empleado asociado
	    if (personEntity.getEmployee() != null) {
	        throw new RuntimeException("La persona ya tiene un empleado asociado");
	    }
			    
	    RoleEntity roleEntity = null;
		if (personEmployeeDTO.getEmployeeDTO().getRoleDTO() != null && personEmployeeDTO.getEmployeeDTO().getRoleDTO().getId() != null) {	
			roleEntity = rolReposiroty.findById(personEmployeeDTO.getEmployeeDTO().getRoleDTO().getId())
					.orElseThrow(() -> new RuntimeException("Rol no encontrado"));
		}
	    
		saveEmployee(personEntity, personEmployeeDTO.getEmployeeDTO(), roleEntity).get();
				
		logger.info(LogHelper.end(getClass(), "processSavePerson"));
		return convertToPersonEmployeeDTO(personEntity);
	}
	
	/**
	 * 
	 * @param personEmployeeDTO
	 * @return
	 */
	private Optional<PersonEntity> savePerson(PersonEmployeeDTO personEmployeeDTO) {
		logger.info(LogHelper.start(getClass(), "savePerson"));
		
		try {
			TypeDocumentEntity document = documentReposiroty.findById(personEmployeeDTO.getTypeDocumentDTO().getId())
			        .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
			
			 var personEntity = new PersonEntity(personEmployeeDTO.getNames(), 
					 personEmployeeDTO.getLastNames(), 
					 document, 
					 personEmployeeDTO.getDni(), 
					 personEmployeeDTO.getDateOfBirth());

			 personEntity = personRepository.save(personEntity);	
			 
			 logger.info(LogHelper.success(getClass(), "savePerson", String.format(LogMessages.ENTITY_SAVE_SUCCESS, personEntity.getId())));
			 return Optional.of(personEntity);
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "savePerson", String.format(LogMessages.ENTITY_SAVE_ERROR, e.getMessage())), e);
			return Optional.empty();
		} finally {
			logger.info(LogHelper.end(getClass(), "savePerson"));
		}
	}
	
	/**
	 * 
	 * @param personEntity
	 * @param employeeDTO
	 * @param roleEntity
	 * @return
	 */
	private Optional<PersonEntity> saveEmployee(PersonEntity personEntity, EmployeeDTO employeeDTO, RoleEntity roleEntity) {
		logger.info(LogHelper.start(getClass(), "saveEmployee"));
		    
		try {
			var employeeEntity = new EmployeeEntity(
					personEntity, 
					roleEntity, 
					employeeDTO.getSalary(),
					employeeDTO.getDateVinculation());
			
			employeeRepository.save(employeeEntity);
			
			personEntity.setEmployee(employeeEntity);
			
			logger.info(LogHelper.success(getClass(), "saveEmployee", String.format(LogMessages.ENTITY_SAVE_SUCCESS, personEntity.getId())));
			return Optional.of(personEntity);
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "saveEmployee", String.format(LogMessages.ENTITY_SAVE_ERROR, e.getMessage())), e);
			return Optional.empty();
			
		} finally {
			logger.info(LogHelper.end(getClass(), "saveEmployee"));
		}
	}
}
