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
import demo.soap.util.LogHelper;
import demo.soap.util.LogMessages;
import jakarta.transaction.Transactional;

@Service
public class CreateEmployeeServiceImpl implements CreateEmployeeService {
	
	// 1. verificar si la persona se encuentra registrada de lo contrario no realiza nada
	// 2. si la persona esta registrada, verificar si ya esta como empleado,
	// 3. si esta como empleado, no se realiza nada
	// 4. si no esta como empleado, se realiza nada
	
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
		
		
		Optional<PersonDTO> person = personService.findPersonByDni(pojo.getDni());
		if (person.isEmpty()) {
			logger.info(LogHelper.warn(getClass(), "createEmployee", String.format(LogMessages.NOT_EXIST_PERSON, pojo.getDni())));
			return Optional.empty();
		}
				
		Optional<EmployeedDTO> employeed = employeeService.findEmployeeByIdPerson(pojo.getId());
		if (employeed.isPresent()) {
			logger.info(LogHelper.warn(getClass(), "createEmployee", String.format(LogMessages.EMPLOYEE_RESGISTER, pojo.getDni(), employeed.get().getDatevinculation())));
			return Optional.empty();
		}
		
		Optional<EmployeedDTO> saveEmpoyee = employeeService.addEmployee(pojo);
	
		
		logger.info(LogHelper.end(getClass(), "createEmployee"));
		return Optional.empty();
	}
	
}
