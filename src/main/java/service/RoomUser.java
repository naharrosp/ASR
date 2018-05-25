package service;
import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

public class RoomUser{ //Handeler de los mensajes
					 String name;
					 String color;

					 KafkaMessagesProducer producer;
					 KafkaMessagesConsumer consumer;

					 public static RoomUser userFactory( String id, String roomStr ){ //Valorar si mover al service
								return null;
					 }

					 public RoomUser(String name, String userid, String roomStr) {
								this.name = name;
								this.color = "red"; //Hacer aleatorio
								KafkaMessagesProducer producer = new KafkaMessagesProducer( roomStr );
								KafkaMessagesProducer consumer = new KafkaMessagesProducer( userid );
					 }

					 public String getName() {
								return name;
					 }

					 public void setName(String name) {
								this.name = name;
					 }

					 public String getColor() {
								return color;
					 }

					 public void setColor(String color) {
								this.color = color;
					 }

					 public void fwMessage( String message ){
								producer.send( message, "chatRoom");
					 }
		  }
