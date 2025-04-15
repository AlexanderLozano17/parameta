package parameta.demo.parameta.service;

import java.util.List;
import java.util.Optional;

import parameta.demo.parameta.dto.RoleDTO;

public interface RoleService {

	Optional<RoleDTO> createRole(RoleDTO RoleDTO);
	
	Optional<RoleDTO> findRoleById(Long Id);
	
	List<RoleDTO> findAllRole();
}
