package com.springData.hotel.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springData.hotel.entities.Room;
import com.springData.hotel.entities.Visitor;
import com.springData.hotel.repositories.RoomRepository;

@RestController
@RequestMapping("room")
public class roomController {

	private final int MAX_FLOOR_NUM = 20;
	private final int Min_FLOOR_NUM = 1;

	@Autowired
	private RoomRepository roomRepository;

	public roomController() {
		super();
	}

	@GetMapping("all")
	public List<Room> getAll() {
		return roomRepository.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<?> addRoom(Room room) {
		if (room.getFloorNumber() < Min_FLOOR_NUM || room.getFloorNumber() > MAX_FLOOR_NUM) {
			return new ResponseEntity<String>("Invalid FloorNumber " + room.getFloorNumber(), HttpStatus.BAD_REQUEST);
		}
		Room roomFromDB = roomRepository.save(room);
		return new ResponseEntity<Room>(roomFromDB, HttpStatus.OK);
	}

//	@PostMapping("add")
//	public ResponseEntity<?> addVisitor(Visitor visitor, Room room) {
//		room.addVisitor(visitor);
//		visitor.setRoom((long) room.getRoomNumber());
//		// Saving to database using CRUD repository
//		Room roomFromDB = roomRepository.save(room);
//		return new ResponseEntity<Room>(roomFromDB, HttpStatus.OK);
//	}

	@GetMapping("")
	public Integer getNumOfOccupiedRooms(LocalDate date) {
		return roomRepository.countBySinceAfterAndUntilBefore(date.minusDays(1), date.plusDays(1));
	}

	public List<Visitor> getVisitors(LocalDate date) {
		return roomRepository.findOccupiedbyBySinceAfterAndUntilBefore(date.minusDays(1), date.plusDays(1));
	}
}
