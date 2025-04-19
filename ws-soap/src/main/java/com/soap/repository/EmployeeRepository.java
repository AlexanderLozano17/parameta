package com.soap.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.soap.dto.EmployeedDTO;
import com.soap.dto.RoleDTO;
import com.soap.repository.service.EmployeeRepositoryService;

import demo.soap.util.LogHelper;
import demo.soap.util.LogMessages;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeRepository implements EmployeeRepositoryService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);	

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<EmployeedDTO> findEmployeeByIdPerson(Long personId) {
	    logger.info(LogHelper.start(getClass(), "findEmployeeByIdPerson"));

	    try {
	        String sql = """
	            SELECT e.id, e.person_id, e.date_vinculation, e.salary, 
	                   r.id AS role_id, r.rol AS role_name
	            FROM employee e
	            INNER JOIN role r ON e.role_id = r.id
	            WHERE e.person_id = :personId
	        """;

	        Object[] result = (Object[]) entityManager
	                .createNativeQuery(sql)
	                .setParameter("personId", personId)
	                .getSingleResult();

	        EmployeedDTO employee = new EmployeedDTO();
	        employee.setId(((Number) result[0]).longValue());
	        employee.setPersonId(((Number) result[1]).longValue());
	        employee.setDatevinculation(((java.sql.Date) result[2]).toLocalDate());
	        employee.setSalary(((Number) result[3]).doubleValue());

	        RoleDTO role = new RoleDTO();
	        role.setId(((Number) result[4]).longValue());
	        role.setRol((String) result[5]);
	        employee.setRole(role);

	        logger.info(LogHelper.success(getClass(), "findEmployeeByIdPerson", LogMessages.ENTITY_SAVE_SUCCESS));
	        return Optional.of(employee);

	    } catch (NoResultException e) {
	        logger.warn("No employee found with personId: {}", personId);
	        return Optional.empty();
	    } catch (Exception e) {
	        logger.error(LogHelper.error(getClass(), "findEmployeeByIdPerson", e.getMessage()), e);
	        return Optional.empty();
	    }
	}


	@Override
	public Optional<EmployeedDTO> addEmployee(EmployeedDTO employee) {
		logger.info(LogHelper.start(getClass(), "addEmployee"));
		
		try {
			String sql = """
					    INSERT INTO employee (person_id, date_vinculation, salary, role_id)
					    VALUES (:personId, :datevinculation, :salary, :roleId)
					    RETURNING id
					""";

			Object id = entityManager.createNativeQuery(sql).setParameter("personId", employee.getPersonId())
					.setParameter("datevinculation", employee.getDatevinculation())
					.setParameter("salary", employee.getSalary())
					.setParameter("roleId", employee.getRole().getId())
					.executeUpdate();

			Long idReturn = ((Number) id).longValue();
			employee.setId(idReturn);
			
			logger.info(LogHelper.success(getClass(), "addEmployee", LogMessages.ENTITY_SAVE_SUCCESS));			
			return Optional.of(employee);
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "addEmployee", e.getMessage()), e);
			return Optional.empty();
		}
	}
}
