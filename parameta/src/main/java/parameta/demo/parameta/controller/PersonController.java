package parameta.demo.parameta.controller;

import java.util.List;
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
import parameta.demo.parameta.dto.PersonDTO;
import parameta.demo.parameta.dto.PersonEmployeeDTO;
import parameta.demo.parameta.dto.ResponseApi;
import parameta.demo.parameta.service.PersonService;
import parameta.demo.parameta.util.ApiMessages;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person")
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@Operation(
		    summary = "Obtener una persona por ID",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApi<PersonDTO>> findPersonById(@PathVariable Long id) {
		 logger.info(LogHelper.start(getClass(), "findPersonById"));
		 
		try {
			Optional<PersonDTO> person = personService.findPersonById(id);
			if (person.isPresent()) {
				logger.info(LogHelper.success(getClass(), "findPersonById", String.format(LogMessages.ENTITY_FOUND, id)));
				logger.info(LogHelper.end(getClass(), "findPersonById"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_FOUND, person.get()));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "findPersonById", String.format(LogMessages.ENTITY_NOT_FOUND, id)));
			logger.info(LogHelper.end(getClass(), "findPersonById"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "findPersonById", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "findPersonById"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
	
	@Operation(
		    summary = "Obtener un listado de personas",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping
	public ResponseEntity<ResponseApi<List<PersonDTO>>> findAllPeople() {
		 logger.info(LogHelper.start(getClass(), "getAllPerson"));
		 
		try {
			List<PersonDTO> personDTOs = personService.findAllPeople();
			if (!personDTOs.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "findAllPeople", String.format(LogMessages.ENTITY_LIST_SUCCESS, personDTOs.size())));
				logger.info(LogHelper.end(getClass(), "findAllPeople"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, personDTOs));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "findAllPeople", String.format(LogMessages.ENTITY_LIST_SUCCESS, 0)));
			logger.info(LogHelper.end(getClass(), "findAllPeople"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "findAllPeople", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "findAllPeople"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}
}
