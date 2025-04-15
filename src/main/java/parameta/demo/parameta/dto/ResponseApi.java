package parameta.demo.parameta.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Setter
@Getter
@ToString
public class ResponseApi<T> implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	    private String status;   // Código de estado HTTP (Ejemplo: 200, 404, etc.)
	    private String message;   // Mensaje de respuesta (Ejemplo: "Operación exitosa")
	    private T data;           // Datos de la respuesta (puede ser cualquier tipo genérico)

}
