package parameta.demo.parameta.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String names;	
	private String lastames;
	private String dni;	
	private LocalDate dateOfBirth;
	
	@ManyToOne
    @JoinColumn(name = "type_document_id", nullable = false)
	private TypeDocumentEntity typeDocument;
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private EmployeeEntity employee;
	
	public PersonEntity(String names, String lastames, TypeDocumentEntity typeDocument, String dni, LocalDate dateOfBirth) {
		this.names = names;
		this.lastames = lastames;
		this.typeDocument = typeDocument;
		this.dni = dni;
		this.dateOfBirth = dateOfBirth;
	}
}
