package com.soap.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soap.service.CreateEmployeeService;

import demo.soap.pojos.SaveEmployeeRequest;
import demo.soap.pojos.SaveEmployeeResponse;
import demo.soap.util.Constants;
import demo.soap.util.LogHelper;


@Endpoint
public class EmployeeEndpoint {
		
	private final Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);

	private final CreateEmployeeService employeService;
	
	public EmployeeEndpoint(CreateEmployeeService employeService) {
		this.employeService = employeService;
	}

	@PayloadRoot(namespace = Constants.NAMESPACE_URI, localPart = "saveEmployeeRequest")
	@ResponsePayload
	public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
		logger.info(LogHelper.start(getClass(), "saveEmployeeRequest"));
		
		SaveEmployeeResponse response = employeService.createEmployee(request.getPersonEmployee()).get();
		
		logger.info(LogHelper.end(getClass(), "saveEmployeeRequest"));
		return response;		
	}
	
}
