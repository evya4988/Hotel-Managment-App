package com.springData.hotel.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springData.hotel.entities.Room;
import com.springData.hotel.entities.Visitor;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	Integer countBySinceAfterAndUntilBefore(LocalDate since, LocalDate until);

	List<Room> findBySinceIsNull();

	List<Visitor> findOccupiedbyBySinceAfterAndUntilBefore(LocalDate since, LocalDate until);

	List<Room> findByUntilLessThan(LocalDate date);

}