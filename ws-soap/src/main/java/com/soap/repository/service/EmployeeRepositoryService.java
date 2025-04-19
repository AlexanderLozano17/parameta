package com.soap.repository.service;

import java.util.Optional;

import com.soap.dto.EmployeedDTO;

public interface EmployeeRepositoryService {

	Optional<EmployeedDTO> addEmployee(EmployeedDTO employee);

	Optional<EmployeedDTO> findEmployeeByIdPerson(Long personId);

}
