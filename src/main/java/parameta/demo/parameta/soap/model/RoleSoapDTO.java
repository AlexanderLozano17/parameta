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
public class RoleSoapDTO implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 20, message = "El nombre del rol debe tener como máximo 20 caracteres")
    private String rol;
}