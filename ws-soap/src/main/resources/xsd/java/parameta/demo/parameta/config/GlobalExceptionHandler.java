package parameta.demo.parameta.config;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import parameta.demo.parameta.dto.ResponseApi;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseApi<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.badRequest()
            .body(new ResponseApi<>("VALIDATION_ERROR", "Error de validación", errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseApi<Object>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        String errorMessage = "Error en el formato del cuerpo de la petición.";

        // Si es por formato de fecha mal escrita
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) ex.getCause();
            if (cause.getTargetType().equals(LocalDate.class)) {
                errorMessage = "La fecha debe tener el formato 'yyyy-MM-dd'.";
            }
        }

        return ResponseEntity.badRequest()
            .body(new ResponseApi<>("FORMAT_ERROR", errorMessage, null));
    }
}

