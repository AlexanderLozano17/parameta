package com.soap.endpoint;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.soap.pojos.SaveEmployeeRequest;
import demo.soap.pojos.SaveEmployeeResponse;
import demo.soap.util.ApiMessages;
import demo.soap.util.FunctionUtils;
import demo.soap.util.LogHelper;
import demo.soap.util.LogMessages;


@Endpoint
public class EmployeeEndpoint {
		
	private final Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);


	private static final String NAMESPACE_URI = "http://localhost:8081/soap/employee";
	
	public EmployeeEndpoint() {
	
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
	@ResponsePayload
	public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
		logger.info(LogHelper.start(getClass(), "saveEmployeeRequest"));
		
		SaveEmployeeResponse response = new SaveEmployeeResponse();		
		
		try {

			FunctionUtils.printJsonPretty(request);			
			

			Optional<?> personEmployeeOpt = Optional.empty();
			
			if (personEmployeeOpt.isPresent()) {	
				logger.info(LogHelper.success(getClass(), "saveEmployeeRequest", String.format(LogMessages.ENTITY_SAVE_SUCCESS, personEmployeeOpt.get())));
				response.setStatus(ApiMessages.SUCCESS);
				response.setMessage(ApiMessages.SAVE_SUCCESS);
				response.setPersonEmployee(null);
				
			} else {
				logger.error(LogHelper.error(getClass(), "saveEmployeeRequest", LogMessages.ENTITY_SAVE_ERROR));
				response.setStatus(ApiMessages.ERROR);
				response.setMessage(ApiMessages.SAVE_ERROR);
				response.setPersonEmployee(null);			
			}
			logger.info(LogHelper.end(getClass(), "saveEmployeeRequest"));
			return response;		
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "saveEmployeeRequest", e.getMessage()), e);
			response.setStatus(ApiMessages.ERROR);
			response.setMessage(ApiMessages.SAVE_ERROR);
			return response;	 
		}
	}
	
}
