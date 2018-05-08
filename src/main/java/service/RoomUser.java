package service;
import kafka.KafkaMessagesProducer;

import service.RoomService.Room;

public class RoomUser{ //Handeler de los mensajes
					 String name;
					 Room room;
					 String color;

					 KafkaMessagesProducer producer;

					 /**
					  * @param name
					  * @param room
					  */
					 public RoomUser(String name, Room room, String roomStr) {
								this.name = name;
								this.room = room;
								this.color = "red"; //Hacer aleatorio
								KafkaMessagesProducer producer = new KafkaMessagesProducer( roomStr );
					 }

					 /**
					  * @return the name
					  */
					 public String getName() {
								return name;
					 }

					 /**
					  * @param name the name to set
					  */
					 public void setName(String name) {
								this.name = name;
					 }

					 /**
					  * @return the room
					  */
					 public Room getRoom() {
								return room;
					 }

					 /**
					  * @param room the room to set
					  */
					 public void setRoom(Room room) {
								this.room = room;
					 }

					 /**
					  * @return the color
					  */
					 public String getColor() {
								return color;
					 }

					 /**
					  * @param color the color to set
					  */
					 public void setColor(String color) {
								this.color = color;
					 }
		  }
