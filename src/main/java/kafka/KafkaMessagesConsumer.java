/**
 * 	Ejemplo de ejecución	
 * 
 * MessageHubConsoleSample.initMessageHubProperties("kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093,   kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093", "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443", "ToMM873rB0nmgldXovJ9B2cwUgbRKTxvsQjVf6pwTIkGCbZF");

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

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import IBMKafkaConnector.MessageHubConsoleSample;

public class KafkaMessagesConsumer implements Runnable {
	private static final Logger logger = Logger.getLogger(KafkaMessagesConsumer.class);

	private final KafkaConsumer<String, String> kafkaConsumer;
	private volatile boolean closing = false;

	public KafkaMessagesConsumer(String topic) {
		// Create a Kafka consumer with the provided client configuration
		kafkaConsumer = new KafkaConsumer<String, String>(MessageHubConsoleSample.getClientConfig(true));

		// Checking for topic existence before subscribing
		List<PartitionInfo> partitions = kafkaConsumer.partitionsFor(topic);
		if (partitions == null || partitions.isEmpty()) {
			logger.log(Level.ERROR, "Topic '" + topic + "' does not exists - application will terminate");
			kafkaConsumer.close();
			throw new IllegalStateException("Topic '" + topic + "' does not exists - application will terminate");
		} else {
			logger.log(Level.INFO, partitions.toString());
		}

		kafkaConsumer.subscribe(Arrays.asList(topic));
	}

	@Override
	public void run() {
		logger.log(Level.INFO, KafkaMessagesConsumer.class.toString() + " is starting.");

		try {
			while (!closing) {
				try {
					// Poll on the Kafka consumer, waiting up to 3 secs if there's nothing to consume.
					ConsumerRecords<String, String> records = kafkaConsumer.poll(3000);

					if (records.isEmpty()) {
						logger.log(Level.INFO, "No messages consumed");
					} else {
						// Iterate through all the messages received and print their content
						for (ConsumerRecord<String, String> record : records) {
							logger.log(Level.INFO, "Message consumed: " + record.toString());
						}
					}

				} catch (final WakeupException e) {
					logger.log(Level.WARN, "Consumer closing - caught exception: " + e);
				} catch (final KafkaException e) {
					logger.log(Level.ERROR, "Sleeping for 5s - Consumer has caught: " + e, e);
					try {
						Thread.sleep(5000); // Longer sleep before retrying
					} catch (InterruptedException e1) {
						logger.log(Level.WARN, "Consumer closing - caught exception: " + e);
					}
				}
			}
		} finally {
			kafkaConsumer.close();
			logger.log(Level.INFO, KafkaMessagesConsumer.class.toString() + " has shut down.");
		}
	}

	public void shutdown() {
		closing = true;
		kafkaConsumer.wakeup();
		logger.log(Level.INFO, KafkaMessagesConsumer.class.toString() + " is shutting down.");
	}
}
