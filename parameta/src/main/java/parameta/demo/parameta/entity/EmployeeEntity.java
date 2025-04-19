package parameta.demo.parameta.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "person_id")
	private PersonEntity person;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity role;    
    private Double salary;	
	private LocalDate dateVinculation;
	
	public EmployeeEntity(PersonEntity person, RoleEntity role, Double salary, LocalDate dateVinculation) {
		this.person = person;
		this.role = role;
		this.salary = salary;
		this.dateVinculation = dateVinculation;		
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
}
