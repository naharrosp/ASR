package service;

import java.io.Writer;
import java.util.Set;

import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

class Room{
		  public Room( String chat) {
					 consumer = new KafkaMessagesConsumer( chat );
		  }


		  // Cada room tiene múltiples productores y consumidores
		  private Set<Writer> writers;  //Set de outpt writers (envío e mensajes)
		  //private Set<MessageHandler.Whole<InputMessage>> readers;  //Set de message handelers 
		  private KafkaMessagesConsumer consumer;

		  //Añadir mensajes de broadcast 

}

