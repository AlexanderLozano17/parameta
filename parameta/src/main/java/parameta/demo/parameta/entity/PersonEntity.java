package parameta.demo.parameta.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
	
	@Column(length = 40)
	@Size(max = 40)
	private String names;	
	
	@Column(length = 40)
	@Size(max = 40)
	private String lastNames;
	
	@Column(unique = true)
	private String dni;	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@ManyToOne
    @JoinColumn(name = "type_document_id", nullable = false)
	private TypeDocumentEntity typeDocument;
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private EmployeeEntity employee;
	
	public PersonEntity(String names, String lastNames, TypeDocumentEntity typeDocument, String dni, LocalDate dateOfBirth) {
		this.names = names;
		this.lastNames = lastNames;
		this.typeDocument = typeDocument;
		this.dni = dni;
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param typeDocument the typeDocument to set
	 */
	public void setTypeDocument(TypeDocumentEntity typeDocument) {
		this.typeDocument = typeDocument;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

}
