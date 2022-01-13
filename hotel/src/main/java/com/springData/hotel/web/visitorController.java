package com.springData.hotel.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springData.hotel.entities.Visitor;
import com.springData.hotel.repositories.VisitorRepository;

@RestController
@RequestMapping("visitor")
public class visitorController {
	private final int MAX_AGE = 12;
	private final int MIN_AGE = 2;
	@Autowired
	private VisitorRepository visitorRepository;

	public visitorController() {
		super();
	}

	@PostMapping("add")
	public ResponseEntity<?> addVisitor(Visitor visitor) {
		Visitor visitorFromDB = visitorRepository.save(visitor);

		return new ResponseEntity<Visitor>(visitorFromDB, HttpStatus.OK);

	}

	@GetMapping("one")
	public List<Visitor> getVisitor(@PathVariable String firstname, @PathVariable String lastname) {
		return visitorRepository.findByFirstNameOrLastName(firstname, lastname);
	}

	@GetMapping("all")
	public List<Visitor> getAll() {
		return visitorRepository.findAll();
	}

	@GetMapping("num")
	public Integer getVisitorsAgeBetween() {
		return visitorRepository.countByBirthDateBetween(LocalDate.now().minusYears(MIN_AGE - 1),
				LocalDate.now().minusYears(MAX_AGE + 1));
	}

}
