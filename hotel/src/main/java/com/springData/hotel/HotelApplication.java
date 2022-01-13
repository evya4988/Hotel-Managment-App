package com.springData.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springData.hotel.entities.Room;
import com.springData.hotel.entities.Visitor;
import com.springData.hotel.facade.HotelFacade;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(HotelApplication.class, args);

		HotelFacade hotelFacade = ctx.getBean(HotelFacade.class);
		System.out.println("spring is running");
		Room r1 = ctx.getBean(Room.class);
		Room r2 = ctx.getBean(Room.class);
		Room r3 = ctx.getBean(Room.class);
		Room r4 = ctx.getBean(Room.class);
		Room r5 = ctx.getBean(Room.class);

		Visitor v1 = ctx.getBean(Visitor.class);
		Visitor v2 = ctx.getBean(Visitor.class);
		Visitor v3 = ctx.getBean(Visitor.class);
		Visitor v4 = ctx.getBean(Visitor.class);
		Visitor v5 = ctx.getBean(Visitor.class);

		r1.setFloorNumber(1);
		r1.setSince(LocalDate.now().minusDays(1));
		r1.setUntil(LocalDate.now().plusDays(1));

		r2.setFloorNumber(2);
		r2.setSince(LocalDate.now().minusDays(2));
		r2.setUntil(LocalDate.now().plusDays(4));

		r3.setFloorNumber(3);
		r3.setSince(LocalDate.now().minusDays(3));
		r3.setUntil(LocalDate.now());

		r4.setFloorNumber(6);
		r5.setFloorNumber(5);

		v1.setBirthDate(LocalDate.now().minusYears(10));
		v1.setFirstName("anonimous");
		v1.setLastName("name");

		v2.setBirthDate(LocalDate.now().minusYears(15));
		v2.setFirstName("Shem");
		v2.setLastName("Tov");

		v3.setBirthDate(LocalDate.now().minusYears(40).minusMonths(5));
		v3.setFirstName("Rami");
		v3.setLastName("Younes");

		v4.setBirthDate(LocalDate.now().minusYears(34));
		v4.setFirstName("dani");
		v4.setLastName("wolf");

		v5.setBirthDate(LocalDate.now().minusYears(34));
		v5.setFirstName("moti");
		v5.setLastName("Ben");

		hotelFacade.addVisitor(v1, r1);
		hotelFacade.addVisitor(v2, r2);
		hotelFacade.addVisitor(v3, r3);
		hotelFacade.addVisitor(v4, r2);

		hotelFacade.addRoom(r4);
		hotelFacade.addRoom(r5);

		Room r6 = hotelFacade.getRoom(1);
		System.out.println("Is room number " + r6.getRoomNumber() + " occupied?");
		System.out.println(hotelFacade.isOccupied(r6));
		System.out.println("---------------------------------");
		System.out.println("All rooms I can use");
		System.out.println(hotelFacade.getAllAvailableRooms());
		System.out.println("---------------------------------");
		System.out.println("all rooms I can use tomorrow");
		System.out.println(hotelFacade.getAllAvailableRoomsTomorrow(LocalDate.now().plusDays(1)));

		Visitor v6 = ctx.getBean(Visitor.class);
		Visitor v7 = ctx.getBean(Visitor.class);
		v6.setBirthDate(LocalDate.now().minusYears(36));
		v6.setFirstName("Leo");
		v6.setLastName("Messi");
		v7.setBirthDate(LocalDate.now().minusYears(37));
		v7.setFirstName("Christiano");
		v7.setLastName("Ronaldo");

		System.out.println("---------------------------------");
		List<Visitor> familiyList1 = new ArrayList<>();
		familiyList1.add(v1);
		familiyList1.add(v2);
		hotelFacade.addFamaliyToAvaliableRoom(familiyList1, LocalDate.now().minusDays(2), LocalDate.now().plusDays(4));
		List<Visitor> famaliyList2 = new ArrayList<>();
		famaliyList2.add(v3);
		famaliyList2.add(v4);
		famaliyList2.add(v5);
		famaliyList2.add(v6);
		hotelFacade.addFamaliyToAvaliableRoom(famaliyList2, LocalDate.now().plusDays(1), LocalDate.now().plusDays(5));
		System.out.println(famaliyList2.size());
		System.out.println("---------------------------------");

//		ctx.close();
	}

}
