package com.soap.service;

import java.util.Optional;

import com.soap.dto.EmployeedDTO;

import demo.soap.pojos.PersonEmployeePojo;

public interface EmployeeService {

    public Optional<EmployeedDTO>  saveEmployee(PersonEmployeePojo employee);

	public Optional<EmployeedDTO> findEmployeeByIdperson(Long personID);
}
