package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SemanticRolesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

import IBMKafkaConnector.MessageHubConsoleSample;
import IBMToneAnalyzerConnector.ToneAnalyzerConnector;
import daoCloudant.CloudantUsuarioDAO;
import dominio.Usuario;
import dominio.UsuarioConnection;
import javassist.NotFoundException;
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
		producer.send("chat2", "Hola");
		response.getWriter().append("Served at: ").append(request.getContextPath());*/
		
		/*//Prueba CLOUDANT
		Usuario prueba= new Usuario ("pablosn", "Pablo");
		prueba.addChat("chat1");
		prueba.addChat("chat2");
		prueba.addChat("chat3");
		
		CloudantUsuarioDAO dao=CloudantUsuarioDAO.getDao();
		dao.persist(prueba);
		System.out.println(dao.get("pablosn"));
		dao.delete("pablosn");*/
		
		/*//Prueba UsuarioConnection (- Multichat)
		
		//Crear el usuario
		MessageHubConsoleSample.initMessageHubProperties("kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093,   kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093", "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443", "ToMM873rB0nmgldXovJ9B2cwUgbRKTxvsQjVf6pwTIkGCbZF");
		
		String name="naharrops";
		Usuario prueba= new Usuario (name, "Pablo", 22, Usuario.HOMBRE);
		prueba.addChat("chat1");
		prueba.addChat("chat2");
		
		
		CloudantUsuarioDAO dao=CloudantUsuarioDAO.getDao();
		dao.persist(prueba);
		
		System.out.println(dao.get(name));
		
		//Intentar conexión
		UsuarioConnection con=null;
		try{
			con=new UsuarioConnection(name);
		}catch ( NotFoundException e ){
			System.out.println("No se ha encontrado el usuario");
			e.printStackTrace();
			//Crear usuario
		}
		con.enviarMensaje("chat1", "Mensaje Inical");
		con.enviarMensaje("chat2", "Mensaje Inical");
		
		con.entrarChat("chat3");
		con.enviarMensaje("chat3", "Despues de entrar en el chat");
		
		con.salirChat("chat2");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.enviarMensaje("chat2", "Despues de entrar en el chat (NO SE DEBE RECIBIR)");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		
		*/
		


		//Prueba COMPLETA
		
		//Crear el usuario
		MessageHubConsoleSample.initMessageHubProperties("kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093,   kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093", "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443", "ToMM873rB0nmgldXovJ9B2cwUgbRKTxvsQjVf6pwTIkGCbZF");
		
		String name="naharrops";
		Usuario prueba= new Usuario (name, "Pablo", 22, Usuario.HOMBRE);
		prueba.addChat("chat1");
		prueba.addChat("chat2");
		
		
		CloudantUsuarioDAO dao=CloudantUsuarioDAO.getDao();
		dao.persist(prueba);
		
		System.out.println(dao.get(name));
		
		//Intentar conexión
		UsuarioConnection con=null;
		try{
			con=new UsuarioConnection(name);
		}catch ( NotFoundException e ){
			System.out.println("No se ha encontrado el usuario");
			e.printStackTrace();
			//Crear usuario
		}
		con.enviarMensaje("chat1", "Mensaje Inical");
		con.enviarMensaje("chat2", "Mensaje Inical");
		
		con.entrarChat("chat3");
		con.enviarMensaje("chat3", "Awful");
		
		con.salirChat("chat2");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.enviarMensaje("chat2", "Despues de entrar en el chat (NO SE DEBE RECIBIR)");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
