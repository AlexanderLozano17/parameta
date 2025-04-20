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
