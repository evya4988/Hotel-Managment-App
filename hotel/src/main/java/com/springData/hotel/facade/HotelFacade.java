package com.springData.hotel.facade;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springData.hotel.entities.Room;
import com.springData.hotel.entities.Visitor;
import com.springData.hotel.repositories.RoomRepository;
import com.springData.hotel.repositories.VisitorRepository;

@Service
@Transactional
public class HotelFacade {
	private final int MAX_AGE = 12;
	private final int MIN_AGE = 2;

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private VisitorRepository visitorsRepository;

	public HotelFacade() {
		super();
	}

	public void addRoom(Room room) {
		roomRepository.save(room);
	}

	public void addVisitor(Visitor visitor, Room room) {
		room.addVisitor(visitor);
		visitor.setRoom((long) room.getRoomNumber());
		// Saving to database using CRUD repository
		roomRepository.save(room);
	}

	public List<Visitor> getVisitor(String firstname, String lastname) {
		return visitorsRepository.findByFirstNameOrLastName(firstname, lastname);
	}

	public Room getRoom(int id) {
		return roomRepository.getById(id);
	}

	public List<Room> getAllAvailableRooms() {
		return roomRepository.findBySinceIsNull();
	}

	public List<Room> getAllAvailableRoomsTomorrow(LocalDate date) {
		return roomRepository.findByUntilLessThan(date);
	}

	public void addFamaliyToAvaliableRoom(List<Visitor> visitors, LocalDate start, LocalDate end) {
		List<Room> rooms = getAllAvailableRooms();
		Room room = rooms.get(0);
		room.setOccupiedBy(visitors);
		room.setSince(start);
		room.setUntil(end);
		roomRepository.save(room);
	}

	public boolean isOccupied(Room room) {
		Room localRoom = roomRepository.getById(room.getRoomNumber());
		if (localRoom.getSince() == null) {
			return false;
		}
		return true;
	}

	public Integer getNumOfChildren() {
		return visitorsRepository.countByBirthDateBetween(LocalDate.now().minusYears(MIN_AGE - 1),
				LocalDate.now().minusYears(MAX_AGE + 1));
	}

	public Integer getNumOfOccupiedRooms(LocalDate date) {
		return roomRepository.countBySinceAfterAndUntilBefore(date.minusDays(1), date.plusDays(1));
	}

	public List<Visitor> getVisitors(LocalDate date) {
		return roomRepository.findOccupiedbyBySinceAfterAndUntilBefore(date.minusDays(1), date.plusDays(1));
	}

}
