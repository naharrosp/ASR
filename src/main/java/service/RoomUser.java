package service;
import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

public class RoomUser{ //Handeler de los mensajes
					 String name;
					 String color;

					 KafkaMessagesProducer producer;
					 KafkaMessagesConsumer consumer;

					 /**
					  * @param name
					  * @param room
					  */
					 public RoomUser(String name, String userid, String roomStr) {
								this.name = name;
								this.color = "red"; //Hacer aleatorio
								KafkaMessagesProducer producer = new KafkaMessagesProducer( roomStr );
								KafkaMessagesProducer consumer = new KafkaMessagesProducer( userid );
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
