package service;

import java.util.HashMap;

import IBMToneAnalyzerConnector.ToneAnalyzerConnector;

import daoCloudant.CloudantUsuarioDAO;

import dominio.Usuario;

import javassist.NotFoundException;

import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

public class UsuarioConnection {

		  private Usuario usuario=null;
		  private HashMap <String, KafkaMessagesConsumer> receptores;
		  private HashMap <KafkaMessagesConsumer, Thread> matchingThread;
		  private KafkaMessagesProducer productor;

		  public UsuarioConnection(String idUsuario) throws NotFoundException{

					 //Obtenemos el usuario
					 usuario=CloudantUsuarioDAO.getDao().get(idUsuario);
					 if(usuario==null)
								throw new NotFoundException("Usuario No encontrado");


					 //Creamos el productor
					 productor=new KafkaMessagesProducer(usuario.getChats().iterator().next());

					 //Creamos los receptores
					 receptores=new HashMap<String, KafkaMessagesConsumer>();
					 matchingThread=new HashMap<KafkaMessagesConsumer, Thread>();
					 for(String chat: usuario.getChats()){
								KafkaMessagesConsumer consumer= new KafkaMessagesConsumer(chat);
								Thread consumerThread = new Thread(consumer, "consumer Thread");
								consumerThread.start();
								receptores.put(chat, consumer);
								matchingThread.put(consumer, consumerThread);
					 }

		  }



		  public Usuario getUsuario() {
					 return usuario;
		  }



		  public void setUsuario(Usuario usuario) {
					 this.usuario = usuario;
					 public void enviarMensaje(String chat, String mensaje){
								String sentimiento=ToneAnalyzerConnector.getToneAnalyzer().analyzeText(mensaje);
								mensaje=sentimiento+"@"+mensaje;

								productor.send(chat, usuario.get_id(), mensaje);
					 }



					 public void salirChat(String chat){

								KafkaMessagesConsumer consumer=receptores.get(chat);
								consumer.shutdown(); //Cerrar conexión
								//Esperar a que se cierre la conexión
								try {
										  Thread.sleep(4000);
								} catch (InterruptedException e) {
										  // TODO Auto-generated catch block
										  e.printStackTrace();
								}
								matchingThread.get(consumer).interrupt(); //Cerrar Thread

								receptores.remove(chat); //Eliminar de la lista de receptores
								matchingThread.remove(consumer); //Eliminar de la lista de matching;

								usuario.removeChat(chat); //Eliminar del usuario
								CloudantUsuarioDAO.getDao().persist(usuario);

					 }

					 public void entrarChat(String chat){

								KafkaMessagesConsumer consumer= new KafkaMessagesConsumer(chat);
								Thread consumerThread = new Thread(consumer, "consumer Thread");
								consumerThread.start();
								receptores.put(chat, consumer);
								matchingThread.put(consumer, consumerThread);


								usuario.addChat(chat);
								CloudantUsuarioDAO.getDao().persist(usuario);
					 }





					 public void close(){

								productor.shutdown();

								for(KafkaMessagesConsumer kf: receptores.values()){
										  kf.shutdown();
								}

								//Necesario esperare a que se cierren todos los consumidores
								try {
										  Thread.sleep(4000);
								} catch (InterruptedException e) {
										  // TODO Auto-generated catch block
										  e.printStackTrace();
								}

								for(Thread kf: matchingThread.values()){
										  kf.interrupt();
								}

					 }
		  }
