package com.soap.service;

import java.util.Optional;

import demo.soap.pojos.PersonEmployeePojo;
import demo.soap.pojos.SaveEmployeeResponse;

public interface CreateEmployeeService {

	Optional<SaveEmployeeResponse> createEmployee(PersonEmployeePojo pojo);

}
