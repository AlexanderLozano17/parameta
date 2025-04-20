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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import parameta.demo.parameta.dto.EmployeeDTO;
import parameta.demo.parameta.dto.PersonEmployeeDTO;
import parameta.demo.parameta.dto.ResponseApi;
import parameta.demo.parameta.service.EmployeeService;
import parameta.demo.parameta.util.ApiMessages;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;
import parameta.demo.ws.client.pojo.SaveEmployeeResponse;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "Employee")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	@Operation(
		    summary = "Crear un empleado",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        required = true,
		        content = @Content(
		            schema = @Schema(implementation = PersonEmployeeDTO.class)
		        )
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = ApiMessages.SAVE_SUCCESS, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody PersonEmployeeDTO personEmployeeDTO) {
		 logger.info(LogHelper.start(getClass(), "createEmployee"));

		 try {
			 Optional<SaveEmployeeResponse> result = service.createEmployee(personEmployeeDTO);
			 String id = result.get().getPersonEmployee() != null ? String.valueOf(result.get().getPersonEmployee().getEmployee().getId()) : "";
			 logger.info(LogHelper.success(getClass(), "createEmployee", String.format(LogMessages.ENTITY_SAVE_SUCCESS, id)));
			 logger.info(LogHelper.end(getClass(), "createEmployee"));

			 return ResponseEntity.status(HttpStatus.CREATED).body(result.get());

		 } catch (Exception e) {
			 logger.error(LogHelper.error(getClass(), "createEmployee", e.getMessage()), e);
			 logger.info(LogHelper.end(getClass(), "createEmployee"));
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
		
	@Operation(
		    summary = "Obtener el empleado por ID de la persona",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
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
