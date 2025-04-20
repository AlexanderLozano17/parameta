package com.soap.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.soap.dto.EmployeedDTO;
import com.soap.dto.RoleDTO;
import com.soap.dto.TypeDocumentDTO;
import com.soap.entity.EmployeeEntity;
import com.soap.entity.PersonEntity;
import com.soap.entity.RoleEntity;
import com.soap.entity.TypeDocumentEntity;
import com.soap.repository.EmployeeRepository;
import com.soap.service.EmployeeService;

import demo.soap.pojos.PersonEmployeePojo;
import demo.soap.util.FunctionUtils;
import demo.soap.util.LogHelper;
import demo.soap.util.LogMessages;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);	

	private final EmployeeRepository repository;
	
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Optional<EmployeedDTO>  saveEmployee(PersonEmployeePojo pojo) {
		logger.info(LogHelper.start(getClass(), "addEmployee"));
				
		EmployeeEntity employeeEntity = createEmployeeEntity(pojo);		
		employeeEntity = repository.save(employeeEntity);
		
		logger.info(LogHelper.end(getClass(), "addEmployee"));
		return Optional.of(createEmployeeDTO(employeeEntity));
	}

	@Override
	public Optional<EmployeedDTO> findEmployeeByIdperson(Long personID) {
		logger.info(LogHelper.start(getClass(), "findEmployeeByIdPerson"));
		
		Optional<EmployeedDTO> employee = repository.findEmployeeByIdperson(personID);
		if (employee.isEmpty()) {	
			
			logger.info(LogHelper.end(getClass(), "findEmployeeByIdPerson"));
			return Optional.empty();			
		}
		
		logger.info(LogHelper.success(getClass(), "findEmployeeByIdPerson", ""));
		logger.info(LogHelper.end(getClass(), "findEmployeeByIdPerson"));
		return employee;		
	}

	/**
	 * 
	 * @param pojo
	 * @return
	 */
	public EmployeeEntity createEmployeeEntity(PersonEmployeePojo pojo) {
		 
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(pojo.getId());

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(pojo.getEmployee().getRole().getId());
		
		return new EmployeeEntity(personEntity, 
				roleEntity, 
				pojo.getEmployee().getSalary(),  
				FunctionUtils.toLocalDate(pojo.getEmployee().getDateVinculation()));
	}
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public EmployeedDTO createEmployeeDTO(EmployeeEntity entity) {				
		return new EmployeedDTO(entity.getId(), 
				entity.getPerson().getId(), 
				entity.getDateVinculation(),
				entity.getSalary(), 				
				new RoleDTO(entity.getRole().getId(), 
						entity.getRole().getRol()));
	}
}
