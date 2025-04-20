package com.soap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soap.entity.PersonEntity;
import java.util.List;


public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
	
	public Optional<PersonEntity> findByDni(String dni);
}
