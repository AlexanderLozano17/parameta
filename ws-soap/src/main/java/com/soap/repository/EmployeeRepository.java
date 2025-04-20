package com.soap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soap.dto.EmployeedDTO;
import com.soap.entity.EmployeeEntity;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	@Query("""
			 SELECT new com.soap.dto.EmployeedDTO(
		        e.id,
		        p.id,
		        e.dateVinculation,
		        e.salary,
		        new com.soap.dto.RoleDTO(r.id, r.rol)
		    )
		    FROM EmployeeEntity e
		    JOIN e.person p
		    JOIN e.role r
		    WHERE p.id = :personId			
			""")
	Optional<EmployeedDTO> findEmployeeByIdperson(@Param("personId") Long personId);

}
