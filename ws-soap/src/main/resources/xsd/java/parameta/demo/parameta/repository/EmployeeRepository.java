package parameta.demo.parameta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import parameta.demo.parameta.entity.EmployeeEntity;
import parameta.demo.parameta.entity.PersonEntity;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	Optional<EmployeeEntity> findByPerson(PersonEntity person);
}
