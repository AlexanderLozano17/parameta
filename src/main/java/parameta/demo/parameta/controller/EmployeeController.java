package parameta.demo.parameta.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import parameta.demo.parameta.dto.EmployeeDTO;
import parameta.demo.parameta.dto.PersonEmployeeDTO;
import parameta.demo.parameta.dto.ResponseApi;
import parameta.demo.parameta.service.EmployeeService;
import parameta.demo.parameta.util.ApiMessages;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseApi<PersonEmployeeDTO>> createEmployee(@Valid @RequestBody PersonEmployeeDTO personEmployeeDTO) {
		 logger.info(LogHelper.start(getClass(), "createEmployee"));
		 
		 try {
			 PersonEmployeeDTO result = service.createEmployee(personEmployeeDTO);
			 logger.info(LogHelper.success(getClass(), "createEmployee", String.format(LogMessages.ENTITY_SAVE_SUCCESS, result.getId())));
			 logger.info(LogHelper.end(getClass(), "createEmployee"));

			 return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi<>(ApiMessages.SUCCESS, ApiMessages.SAVE_SUCCESS, result));

		 } catch (Exception e) {
			 logger.error(LogHelper.error(getClass(), "createEmployee", e.getMessage()), e);
			 logger.info(LogHelper.end(getClass(), "createEmployee"));
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@GetMapping("/person/{personId}")
	public ResponseEntity<ResponseApi<EmployeeDTO>> findEmployee(@PathVariable Long personId) {
	    logger.info(LogHelper.start(getClass(), "findEmployee"));

	    try {
	        Optional<PersonEmployeeDTO> employeeOpt = service.findEmployeedByIdPerson(personId); 
	        if (employeeOpt.isPresent()) {      	
	        	PersonEmployeeDTO personEmployeeDTO = employeeOpt.get();

	            // Verificar si tiene empleado asociado
	            if (personEmployeeDTO.getEmployeeDTO() != null) {
	            	logger.info(LogHelper.success(getClass(), "findEmployee", String.format(LogMessages.ENTITY_FOUND, personId)));
	                logger.info(LogHelper.end(getClass(), "findEmployee"));
	                return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, personEmployeeDTO));
	            
	            } else {
	            	String message = String.format(ApiMessages.EMPLOYEE_NOT_ASSOCIATED_WITH_PERSON, personId);
	                logger.warn(LogHelper.warn(getClass(), "findEmployee", message));
					return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, message, personEmployeeDTO));
	            }
	        }

	        logger.warn(LogHelper.warn(getClass(), "findEmployee", String.format(LogMessages.ENTITY_NOT_FOUND, personId)));
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));

	    } catch (Exception e) {
	        logger.error(LogHelper.error(getClass(), "findEmployee", e.getMessage()), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
	    }
	}
}
