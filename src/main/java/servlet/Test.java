package servlet;
/*
 *
 * Un header chostico
 * Huy huy huy
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IBMKafkaConnector.MessageHubConsoleSample;
import daoCloudant.CloudantUsuarioDAO;
import dominio.Usuario;
import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*//Ejemplo KAFKA
		MessageHubConsoleSample.initMessageHubProperties("kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093,   kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093", "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443", "ToMM873rB0nmgldXovJ9B2cwUgbRKTxvsQjVf6pwTIkGCbZF");

		MessageHubConsoleSample.createTopic("chat2");

		System.out.println("\n\n"+MessageHubConsoleSample.getTopics()+"\n\n");

		KafkaMessagesConsumer consumer = new KafkaMessagesConsumer("chat2");
		Thread consumerThread = new Thread(consumer, "consumer Thread");
		consumerThread.start();

		KafkaMessagesProducer producer= new KafkaMessagesProducer("chat1");
		producer.send("Hola", "chat2");
		response.getWriter().append("Served at: ").append(request.getContextPath());*/

		Usuario prueba= new Usuario ("pablosn", "Pablo");
        // chotis
        // otis motis
		prueba.addChat("chat2");
		prueba.addChat("chat3");

		CloudantUsuarioDAO dao= new CloudantUsuarioDAO();
		dao.persist(prueba);
		System.out.println(dao.get("pablosn"));
		dao.delete("pablosn");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
