package parameta.demo.parameta.soap.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
public class TypeDocumentSoapDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotBlank(message = "El código del documento es obligatorio")
	@Size(max = 3, message = "El código debe tener como máximo 3 caracteres")
	private String code;

	@NotBlank(message = "El nombre del documento es obligatorio")
	@Size(max = 30, message = "El nombre del documento debe tener como máximo 30 caracteres")
	private String document;
}
