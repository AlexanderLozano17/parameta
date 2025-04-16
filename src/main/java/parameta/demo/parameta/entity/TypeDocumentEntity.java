package parameta.demo.parameta.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "type_document")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class TypeDocumentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(unique = true, length = 3)
	@Size(max = 3)
    private String code;    
	
	@Column(length = 40)
	@Size(max = 40)
	private String document;

}
