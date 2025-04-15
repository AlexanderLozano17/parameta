package parameta.demo.parameta.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import parameta.demo.parameta.entity.EmployeeEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long id;    

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 20, message = "El nombre del rol debe tener como máximo 20 caracteres")
    private String rol;
}
