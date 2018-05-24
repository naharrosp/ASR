/*-------------------------------------------------------
 PHOTO LOGIN
 -------------------------------------------------------*/

//TODO: incluir subida de la imágen

var photoLoginModule = (function(){

		  if(Webcam != null){
					 Webcam.set({
										  width: 320,
										  height: 240,
										  image_format: 'jpeg',
										  jpeg_quality: 90
					 });
					 Webcam.attach( '#my_camera' );
		  }

		  var dataSrc;

		  /*
			Cambiar la función para quizá mostrar la foto y otra para subirla
			*/
		  return{
					 take_snapshot: function(){
								// take snapshot and get image data
								if( Webcam === null){
										  alert("Webacamjs not imported");
										  return;
								}
								Webcam.snap( function(data_uri) {
										  // display results in page
										  dataSrc=data_uri;

										  //document
										  //.getElementById('photo_result').
										  //setAtribute('src', data_uri)
										  $("#photo_result").attr('src',data_uri);
								})
					 },

					 upoad_image: function(){
								if( Webcam === null){
										  alert("Webacamjs not imported");
										  return;
								}
								//Recuperar la uri de la imagen

								//INVESTIGAR CÓMO SUBIR UNA IMÁGEN EN FORMATO BASE 64
								//EN SU DEFECTO INVESTIGAR CÓMO SE PUEDE ENVIAR EN OTRO FORMATO
					 }
		  }

})()

//EXPORTS
function take_snapshot(){
		  photoLoginModule.take_snapshot();
}
function upoad_image(){
		  photoLoginModule.upoad_image();
}

/*-------------------------------------------------------
 LOGIN
 -------------------------------------------------------*/
(function(){


})()


/*-------------------------------------------------------
 CHAT
 -------------------------------------------------------*/
chatModule = (function(){

		  var socket;

		  function onMessage( message ){
					 var data = JSON.parse(message.data); //TODO: comprobar que se debe atender a data

					 //Añadir un mensaje al log de mensajes
					 var htmlcode =  ''+
								'<div class="chatMsg '+ data.color +'">'+
								'<div class="msgContent">'+ data.message +'</div>'+
								'<div class="msgAuthor">'+ data.author +'</div>'+
								'</div>';

					 $.("#msgFeed").append(htmlcode);
		  }

		  return{
					 connect: function(){

								if( websocket != null )
										  return 

								//Obtener id de usuario y nombre de la habitación
								var userid = $.('#dataContainer').data('userid'); //TODO: comprobar código html y div.
								var userid = $.('#dataContainer').data('room'); //TODO: comprobar código html y div.
								var path = window.location.hostname;//TODO: comprobar parámetro

								socket = new WebSockets( path );

								var msg = JSON.stringify({
													 user: userid,
													 room: room
								});

								socket.addEventListener('open', (open)=>{
										  //Enviamos el mensaje de arranque de la conexión
										  socket.send(msg);
								});
								socket.addEventListener('onmessage', onMessage);
								socket.addEventListener('onerror',(error)=>{
										  alert('Error' + JSON.stingify(error));
										  socket.close();
										  socket = null;
								});
								socket.addEventListener('onclose',(close)=>{
										  alert('Error' + JSON.stingify(error));
										  socket.close();
										  socket = null;
								});
					 }
					 
					 send: function( msg ){
								if( socket.readyState != 1 ){
										  alert("El socket no esta conectado, estado: " + socket.readyState);
										  return
								}
								//TODO: comprobar si el mensaje es accionado desde un formulario o si se adquiere la información manualmente
								var msg_ = $.('#msgTextBox').innerHTML;

								//borramos la caja de texto
								$.('#msgTextBox').value = '';
								socket.send(msg);
					 }
		  }
})()

//EXPORTS
function socket_connect(){
		  chatModule.connect();
}
function socket_send(msg){
		  chatModule.send(msg);
}

/*-------------------------------------------------------
 EVENTS
 -------------------------------------------------------*/
$(document).ready(function(){
		  if( window.location.pathname === '/chat' ) //El evento se limita a la página
					 socket_connect();
}
