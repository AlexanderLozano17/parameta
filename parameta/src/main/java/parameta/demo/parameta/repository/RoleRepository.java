package parameta.demo.parameta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import parameta.demo.parameta.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
