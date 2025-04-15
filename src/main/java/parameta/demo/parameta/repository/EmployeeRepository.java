package parameta.demo.parameta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parameta.demo.parameta.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
