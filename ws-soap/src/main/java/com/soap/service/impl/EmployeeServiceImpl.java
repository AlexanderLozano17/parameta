package com.soap.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.soap.dto.EmployeedDTO;
import com.soap.dto.RoleDTO;
import com.soap.repository.EmployeeRepository;
import com.soap.service.EmployeeService;

import demo.soap.pojos.PersonEmployeePojo;
import demo.soap.util.FunctionUtils;
import demo.soap.util.LogHelper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);	

	private final EmployeeRepository repository;
	
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Optional<EmployeedDTO>  addEmployee(PersonEmployeePojo employee) {
		logger.info(LogHelper.start(getClass(), "addEmployee"));
		
		EmployeedDTO employeedDTO = new EmployeedDTO();
		employeedDTO.setPersonId(employee.getId());
		employeedDTO.setDatevinculation(FunctionUtils.toLocalDate(employee.getEmployee().getDateVinculation()));
		employeedDTO.setSalary(employee.getEmployee().getSalary());
		
		RoleDTO rol = new RoleDTO(employee.getEmployee().getRole().getId(), employee.getEmployee().getRole().getRol());		
		employeedDTO.setRole(rol);

		Optional<EmployeedDTO> employeeOpt = repository.addEmployee(employeedDTO);
		
		logger.info(LogHelper.end(getClass(), "addEmployee"));
		return employeeOpt;
	}

	@Override
	public Optional<EmployeedDTO> findEmployeeByIdPerson(long id) {
		logger.info(LogHelper.start(getClass(), "findEmployeeByIdPerson"));
		
		Optional<EmployeedDTO> employee = repository.findEmployeeByIdPerson(id);
		
		logger.info(LogHelper.end(getClass(), "findEmployeeByIdPerson"));
		return employee;
	}

}
