package parameta.demo.parameta.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import parameta.demo.parameta.dto.TypeDocumentDTO;
import parameta.demo.parameta.entity.TypeDocumentEntity;
import parameta.demo.parameta.repository.TypeDocumentRepository;
import parameta.demo.parameta.service.TypeDocumentService;
import parameta.demo.parameta.util.LogHelper;
import parameta.demo.parameta.util.LogMessages;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {
	
	private final Logger logger = LoggerFactory.getLogger(TypeDocumentServiceImpl.class);
	
	private final TypeDocumentRepository repository;
	
	public TypeDocumentServiceImpl(TypeDocumentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<TypeDocumentDTO> createTypeDocument(TypeDocumentDTO typeDocumentDTO) {
		logger.info(LogHelper.start(getClass(), "createTypeDocument"));		
		 
		try {
			var document = new TypeDocumentEntity(null, typeDocumentDTO.getCode(), typeDocumentDTO.getDocument());		
			TypeDocumentEntity isSave = repository.save(document);		

			logger.info(LogHelper.success(getClass(), "createTypeDocument", String.format(LogMessages.ENTITY_SAVE_SUCCESS, isSave.getId())));
			return Optional.of(convertToTypeDocumentDTO(isSave));
			
		} catch (Exception e) {
			logger.error(LogHelper.error(getClass(), "createTypeDocument", String.format(LogMessages.ENTITY_SAVE_ERROR, e.getMessage())), e);
			logger.info(LogHelper.end(getClass(), "createTypeDocument"));		
			return Optional.empty();
		}		
	}

	@Override
	public Optional<TypeDocumentDTO> findTypeDocumentById(Long Id) {
		logger.info(LogHelper.start(getClass(), "findTypeDocumentById"));
		
		Optional<TypeDocumentEntity> document = repository.findById(Id);		
		if (document.isPresent()) {
			logger.info(LogHelper.success(getClass(), "findTypeDocumentById", String.format(LogMessages.ENTITY_FOUND, document.get().getId())));
			return Optional.ofNullable(convertToTypeDocumentDTO(document.get()));
		}
		logger.warn(LogHelper.warn(getClass(), "findTypeDocumentById", String.format(LogMessages.ENTITY_NOT_FOUND, Id)));
		logger.info(LogHelper.end(getClass(), "findTypeDocumentById"));
		return Optional.empty();
	}

	@Override
	public List<TypeDocumentDTO> findAllTypeDocument() {
		logger.info(LogHelper.start(getClass(), "findAllTypeDocument"));
		
		List<TypeDocumentEntity> listDocuments = repository.findAll();
		if (!listDocuments.isEmpty()) {
			logger.info(LogHelper.success(getClass(), "findAllTypeDocument", String.format(LogMessages.ENTITY_LIST_SUCCESS, listDocuments.size())));
			return convertToListTypeDocumentDTO(listDocuments);
		}
		
		logger.warn(LogHelper.warn(getClass(), "findAllTypeDocument", String.format(LogMessages.ENTITY_LIST_ERROR, 0)));
		logger.info(LogHelper.end(getClass(), "findAllTypeDocument"));
		return new ArrayList<>();
	}
	
	/**
	 * 
	 * @param typeDocument
	 * @return
	 */
	private TypeDocumentDTO convertToTypeDocumentDTO(TypeDocumentEntity typeDocument) {
		if (typeDocument == null) return null;		
		return new TypeDocumentDTO(typeDocument.getId(), typeDocument.getCode(), typeDocument.getDocument());
	}
	
	/**
	 * 
	 * @param listDocuments
	 * @return
	 */
	private List<TypeDocumentDTO> convertToListTypeDocumentDTO(List<TypeDocumentEntity> listDocuments) {
		if (listDocuments.isEmpty()) return new ArrayList<>();		
		return listDocuments.stream().map(document -> convertToTypeDocumentDTO(document)).collect(Collectors.toList());
	}

}
