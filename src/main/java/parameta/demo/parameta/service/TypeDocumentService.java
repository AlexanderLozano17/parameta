package parameta.demo.parameta.service;

import java.util.List;
import java.util.Optional;

import parameta.demo.parameta.dto.TypeDocumentDTO;

public interface TypeDocumentService {

	Optional<TypeDocumentDTO> createTypeDocument(TypeDocumentDTO typeDocumentDTO);
	
	Optional<TypeDocumentDTO> findTypeDocumentById(Long Id);
	
	List<TypeDocumentDTO> findAllTypeDocument();
	
}
