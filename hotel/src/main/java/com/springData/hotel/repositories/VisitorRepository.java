package com.springData.hotel.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springData.hotel.entities.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	List<Visitor> findByFirstNameOrLastName(String firstName, String lastName);

	Integer countByBirthDateBetween(LocalDate before, LocalDate after);

}
