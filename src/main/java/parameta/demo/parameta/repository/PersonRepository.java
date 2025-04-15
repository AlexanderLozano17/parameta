package parameta.demo.parameta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parameta.demo.parameta.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

}
