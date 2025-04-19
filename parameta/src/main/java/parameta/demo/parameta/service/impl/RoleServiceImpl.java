package parameta.demo.parameta.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import parameta.demo.parameta.dto.RoleDTO;
import parameta.demo.parameta.entity.RoleEntity;
import parameta.demo.parameta.repository.RoleRepository;
import parameta.demo.parameta.service.RoleService;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@Service
public class RoleServiceImpl implements RoleService {
	
private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	private final RoleRepository repository;
	
	public RoleServiceImpl(RoleRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<RoleDTO> createRole(RoleDTO RoleDTO) {
		logger.info(LogHelper.start(getClass(), "createRole"));	
		
		try { 			
			RoleEntity role = new RoleEntity(null, RoleDTO.getRol(), new ArrayList<>());				
			role = repository.save(new RoleEntity(null, RoleDTO.getRol(), null));
			
			logger.info(LogHelper.success(getClass(), "createRole", String.format(LogMessages.ENTITY_SAVE_SUCCESS, role.getId())));
			return Optional.of(convertToRolDTO(role));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createRole", String.format(LogMessages.ENTITY_SAVE_ERROR, e.getMessage())), e);
			logger.info(LogHelper.end(getClass(), "createRole"));	
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<RoleDTO> findRoleById(Long Id) {
		logger.info(LogHelper.start(getClass(), "findRoleById"));
		
		Optional<RoleEntity> role = repository.findById(Id);		
		if (role.isPresent()) {
			logger.info(LogHelper.success(getClass(), "findRoleById", String.format(LogMessages.ENTITY_FOUND, role.get().getId())));
			return Optional.ofNullable(convertToRolDTO(role.get()));
		}
		logger.warn(LogHelper.warn(getClass(), "findRoleById", String.format(LogMessages.ENTITY_NOT_FOUND, Id)));
		logger.info(LogHelper.end(getClass(), "findRoleById"));
		return Optional.empty();
	}

	@Override
	public List<RoleDTO> findAllRole() {
		logger.info(LogHelper.start(getClass(), "findAllRole"));
		
		List<RoleEntity> listRoles = repository.findAll();
		if (!listRoles.isEmpty()) {
			logger.info(LogHelper.success(getClass(), "findAllRole", String.format(LogMessages.ENTITY_LIST_SUCCESS, listRoles.size())));
			return convertToListRolDTO(listRoles);
		}
		
		logger.warn(LogHelper.warn(getClass(), "findAllRole", String.format(LogMessages.ENTITY_LIST_ERROR, 0)));
		logger.info(LogHelper.end(getClass(), "findAllRole"));
		return new ArrayList<>();
	}

	
	/**
	 * 
	 * @param RoleEntity
	 * @return
	 */
	private RoleDTO convertToRolDTO(RoleEntity RoleEntity) {
		if (RoleEntity == null) return null;		
		return new RoleDTO(RoleEntity.getId(), RoleEntity.getRol());
	}
	
	/**
	 * 
	 * @param listRoleEntity) {

	 * @return
	 */
	private List<RoleDTO> convertToListRolDTO(List<RoleEntity> listRoleEntity) {
		if (listRoleEntity.isEmpty()) return new ArrayList<>();		
		return listRoleEntity.stream().map(role -> convertToRolDTO(role)).collect(Collectors.toList());
	}
}
