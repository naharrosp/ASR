/**
 * 	Ejemplo de ejecución	
 * 
MessageHubConsoleSample.initMessageHubProperties("kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093,   kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093", "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443", "ToMM873rB0nmgldXovJ9B2cwUgbRKTxvsQjVf6pwTIkGCbZF");
		
		MessageHubConsoleSample.createTopic("chat2");
		
		System.out.println("\n\n"+MessageHubConsoleSample.getTopics()+"\n\n");
		
		KafkaMessagesConsumer consumer = new KafkaMessagesConsumer("chat2");
		Thread consumerThread = new Thread(consumer, "consumer Thread");
		consumerThread.start();
        
		KafkaMessagesProducer producer= new KafkaMessagesProducer("chat1");
		producer.send("Hola", "chat2");
		response.getWriter().append("Served at: ").append(request.getContextPath());
 */

package kafka;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import IBMKafkaConnector.MessageHubConsoleSample;


public class KafkaMessagesProducer {
	
	private static final Logger logger = Logger.getLogger(KafkaMessagesProducer.class);

    private final KafkaProducer<String, String> kafkaProducer;
    private final String topic;
    

    
    public KafkaMessagesProducer(String topic) {
    	
    	
        this.topic = topic;

        // Create a Kafka producer with the provided client configuration
        kafkaProducer = new KafkaProducer<String, String>(MessageHubConsoleSample.getClientConfig(false)); //False porque es producer
        
        try {
            // Checking for topic existence.
            // If the topic does not exist, the kafkaProducer will retry for about 60 secs
            // before throwing a TimeoutException
            // see configuration parameter 'metadata.fetch.timeout.ms'
            List<PartitionInfo> partitions = kafkaProducer.partitionsFor(topic);
            logger.log(Level.INFO, partitions.toString());
        } catch (TimeoutException kte) {
            logger.log(Level.ERROR, "Topic '" + topic + "' may not exist - application will terminate");
            kafkaProducer.close();
            throw new IllegalStateException("Topic '" + topic + "' may not exist - application will terminate", kte);
        }
    }
    
    
    public void send(String message, String conversation) {
        // Simple counter for messages sent
        int producedMessages = 0;
        logger.log(Level.INFO, KafkaMessagesProducer.class.toString() + " is starting.");

        try {
                String key = "key";

                try {
                    // If a partition is not specified, the client will use the default partitioner to choose one.
                    ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                            conversation,key,message);
                    
                    // Send record asynchronously
                    kafkaProducer.send(record);
						  System.out.println("Enviado mensaje en productor: "+ message +" | para conversación: " + conversation);
                    

                } catch (final Exception e) {
                    logger.log(Level.ERROR, "Sleeping for 5s - Producer has caught : " + e, e);
                    try {
                        Thread.sleep(5000); // Longer sleep before retrying
                    } catch (InterruptedException e1) {
                        logger.log(Level.WARN, "Producer closing - caught exception: " + e);
                    }
                }
            
        } finally {
            //kafkaProducer.close(5000, TimeUnit.MILLISECONDS);
            //logger.log(Level.INFO, KafkaMessagesProducer.class.toString() + " has shut down.");
        }
    }

    public void shutdown() {
        kafkaProducer.close(5000, TimeUnit.MILLISECONDS);
        logger.log(Level.INFO, KafkaMessagesProducer.class.toString() + " has shut down.");
    }

}
