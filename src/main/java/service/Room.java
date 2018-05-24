package service;

import java.util.Collection;

import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

public class Room{ //HUB de mensajes kafka

		  public Room( String chat) {
					 //consumer = new KafkaMessagesConsumer( chat );
		  }


		  // Cada room tiene múltiples productores y consumidores
		  //private Set<MessageHandler.Whole<InputMessage>> readers;  //Set de message handelers 
		  private Collection<KafkaMessagesConsumer> consumer;
		  private Collection<KafkaMessagesProducer> producer;

		  //Añadir mensajes de broadcast 

}

