package com.springData.hotel.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue
	@Column(name = "room_number")
	private int roomNumber;

	@Column(name = "floor_number")
	@Min(value = 1, message = "floorNumber must be higher or equal to 1!")
	@Max(value = 20, message = "floorNumber must be lower or equal to 20!")
	private int floorNumber;

	@Column(name = "since")
	private LocalDate since;

	@Column(name = "until")
	private LocalDate until;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "room")
	List<Visitor> occupiedBy = new ArrayList<Visitor>();

	public Room() {
		super();
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public LocalDate getSince() {
		return since;
	}

	public void setSince(LocalDate since) {
		this.since = since;
	}

	public LocalDate getUntil() {
		return until;
	}

	public void setUntil(LocalDate until) {
		this.until = until;
	}

	public List<Visitor> getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(List<Visitor> occupiedBy) {
		this.occupiedBy = occupiedBy;
	}

	public void addVisitor(Visitor visitor) {
		if (occupiedBy.contains(visitor)) {
			return;
		}
		occupiedBy.add(visitor);
	}

	public void removeVisitor(Visitor visitor) {
		if (!occupiedBy.contains(visitor)) {
			return;
		}
		occupiedBy.remove(visitor);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room (RoomNumber: " + roomNumber + ", FloorNumber: " + floorNumber + ", Since: " + since + ", Until: "
				+ until + ")\n";
	}
}
