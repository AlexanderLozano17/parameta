package com.soap.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TypeDocumentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String document;
}