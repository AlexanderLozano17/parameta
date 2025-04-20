package parameta.demo.parameta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import parameta.demo.parameta.dto.ResponseApi;
import parameta.demo.parameta.dto.RoleDTO;
import parameta.demo.parameta.service.RoleService;
import parameta.demo.parameta.util.ApiMessages;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Rol")
public class RoleController {

	private final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	private final RoleService rolService;
	
	public RoleController(RoleService rolService) {
		this.rolService = rolService;
	}
		
	@Operation(
		    summary = "Obtener un listado de roles",
		    responses = {
		        @ApiResponse(responseCode = "200", description = ApiMessages.RECORD_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "404", description = ApiMessages.RECORD_NOT_FOUND, content = @Content(schema = @Schema(implementation = ResponseApi.class))),
		        @ApiResponse(responseCode = "500", description = ApiMessages.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ResponseApi.class)))
		    }
		)
	@GetMapping
	public ResponseEntity<ResponseApi<List<RoleDTO>>> findAllRole() {
		 logger.info(LogHelper.start(getClass(), "findAllRole"));
		 
		try {
			List<RoleDTO> listRoles = rolService.findAllRole();
			if (!listRoles.isEmpty()) {
				logger.info(LogHelper.success(getClass(), "findAllRole", String.format(LogMessages.ENTITY_LIST_SUCCESS, listRoles.size())));
				logger.info(LogHelper.end(getClass(), "findAllRole"));
				return ResponseEntity.ok(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.LIST_SUCCESS, listRoles));
			} 
			
			logger.warn(LogHelper.warn(getClass(), "findAllRole", String.format(LogMessages.ENTITY_LIST_SUCCESS, 0)));
			logger.info(LogHelper.end(getClass(), "findAllRole"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseApi(ApiMessages.SUCCESS, ApiMessages.RECORD_NOT_FOUND, null));
		
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "findAllRole", e.getMessage()), e);
			logger.info(LogHelper.end(getClass(), "findAllRole"));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseApi(ApiMessages.ERROR, ApiMessages.INTERNAL_SERVER_ERROR, null));
		}
	}

}
