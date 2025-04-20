package com.soap.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.soap.dto.EmployeedDTO;
import com.soap.dto.PersonDTO;
import com.soap.service.CreateEmployeeService;
import com.soap.service.EmployeeService;
import com.soap.service.PersonService;

import demo.soap.pojos.PersonEmployeePojo;
import demo.soap.pojos.SaveEmployeeResponse;
import demo.soap.util.ApiMessages;
import demo.soap.util.FunctionUtils;
import demo.soap.util.LogHelper;
import demo.soap.util.LogMessages;
import jakarta.transaction.Transactional;

@Service
public class CreateEmployeeServiceImpl implements CreateEmployeeService {
		
	private final Logger logger = LoggerFactory.getLogger(CreateEmployeeServiceImpl.class); 
	
	private final PersonService personService;
	private final EmployeeService employeeService;
	
	public CreateEmployeeServiceImpl(PersonService personService, EmployeeService employeeService) {
		this.personService = personService;
		this.employeeService = employeeService;
	}

	@Transactional
	@Override
	public Optional<SaveEmployeeResponse> createEmployee(PersonEmployeePojo pojo){
	    logger.info(LogHelper.start(getClass(), "createEmployee"));
	    
	    try {	    	
		    String message = "";
		    
		    // 1. Verificar si la persona existe
		    Optional<PersonDTO> optionalPerson = personService.findPersonByDni(pojo.getDni());
		    if (optionalPerson.isEmpty()) {
	   	
		    	message = String.format(LogMessages.PERSON_NOT_REGISTER, pojo.getDni());
		        logger.info(LogHelper.warn(getClass(), "createEmployee", message));
		        logger.info(LogHelper.end(getClass(), "createEmployee"));
		        return Optional.of(response(ApiMessages.WARNING, LogMessages.PERSON_NOT_REGISTER, null));
		    }

		    PersonDTO person = optionalPerson.get();

		    // 2. Verificar si ya es empleado
		    Optional<EmployeedDTO> optionalEmployee = employeeService.findEmployeeByIdperson(person.getId());
		    if (optionalEmployee.isPresent()) {
		    	
		        EmployeedDTO existingEmployee = optionalEmployee.get();	 
		        message = String.format(LogMessages.EMPLOYEE_RESGISTER, pojo.getDni(), existingEmployee.getDatevinculation());
		        logger.info(LogHelper.warn(getClass(), "createEmployee", message));	     
		        logger.info(LogHelper.end(getClass(), "createEmployee"));
		        return Optional.of(response(ApiMessages.WARNING, message, null));
		    }

		    // 3. Registrar nuevo empleado
		    pojo.setId(person.getId());
		    Optional<EmployeedDTO> savedEmployee = employeeService.saveEmployee(pojo);
		    
		    if (savedEmployee.isEmpty()) {
		        logger.error(LogHelper.error(getClass(), "createEmployee", LogMessages.ENTITY_SAVE_ERROR));
		        return Optional.of(response(ApiMessages.ERROR, LogMessages.ENTITY_SAVE_ERROR, null));
		    }
		    
	    	pojo.getEmployee().setId(savedEmployee.get().getId());
	        message = String.format(LogMessages.ENTITY_SAVE_SUCCESS, savedEmployee.get().getId());
	        logger.info(LogHelper.success(getClass(), "createEmployee", message));
	        logger.info(LogHelper.end(getClass(), "createEmployee"));
	        return Optional.of(response(ApiMessages.SUCCESS, message, pojo));
		    	    	
	    } catch (Exception e) {
	    	logger.error(LogHelper.error(getClass(), "createEmployee", e.getMessage()), e);
	    	logger.info(LogHelper.end(getClass(), "createEmployee"));
			return Optional.of(response(ApiMessages.ERROR, LogMessages.ENTITY_SAVE_ERROR, null));
		}
	}
	
	/**
	 * 
	 * @param accion
	 * @param message
	 * @param pojo
	 * @return
	 */
	private SaveEmployeeResponse response(String accion, String message, PersonEmployeePojo pojo) {
		SaveEmployeeResponse response = new SaveEmployeeResponse();		
		response.setStatus(accion);
        response.setMessage(message);        
        
        if (pojo != null) {
        	// calcula la edad y tiempo de vinculación
            pojo.setAge(FunctionUtils.calculateAge(FunctionUtils.toLocalDate(pojo.getDateOfBirth())));
            pojo.getEmployee().setTimeVinculation((FunctionUtils.calculateVinculationTime(FunctionUtils.toLocalDate(pojo.getEmployee().getDateVinculation()))));        
            
        }
        
        response.setPersonEmployee(pojo);
               
        return response;
	}
	
}
