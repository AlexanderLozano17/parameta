package com.soap.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeedDTO {

	private Long id;
	private Long personId;
	private LocalDate datevinculation;
	private double salary;
	private RoleDTO role;
}
