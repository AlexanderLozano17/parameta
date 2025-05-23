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
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
    private Long id;    
    private String rol;
}