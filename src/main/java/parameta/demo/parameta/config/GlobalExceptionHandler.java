package parameta.demo.parameta.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import parameta.demo.parameta.dto.ResponseApi;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseApi<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		 List<String> errors = ex.getBindingResult().getFieldErrors().stream()
		            .map(err -> err.getField() + ": " + err.getDefaultMessage())
		            .collect(Collectors.toList());
		 
		 return ResponseEntity.badRequest().body(new ResponseApi<>("VALIDATION_ERROR", "Error de validación", errors));
	}
}
