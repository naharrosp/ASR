/*-------------------------------------------------------
 PHOTO LOGIN
 -------------------------------------------------------*/

//TODO: incluir subida de la imágen
var photoLoginModule = (function(){

		  if(typeof Webcam != "undefined"){
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
		  //Comprobar de forma asíncrona el usuario		  
})()


/*-------------------------------------------------------
 CHAT
 -------------------------------------------------------*/
var chatModule = (function(){
		  var socket = null;

		  function onMessageChat( message ){

					 console.log("Mensaje recibido: "+message);

					 var data = JSON.parse(message.data); //TODO: comprobar que se debe atender a data

					 //Añadir un mensaje al log de mensajes
					 var htmlcode =  ''+
								'<li class="chatMsg '+ data.color +'">'+
								'<div class="msgContent">'+ data.message +'</div>'+
								'<div class="msgAuthor">'+ data.author +'</div>'+
								'</li>';

					 $('#msgFeed').append(htmlcode);
		  }

		  function onMessageRooms( message ){
					 console.log("Mensaje recibido: "+message);

					 var data = JSON.parse(message.data); //TODO: comprobar que se debe atender a data
					 var target = data.room;
					 var count = $('#'+target).innerHTML
					 $('#'+target).innerHTML = count + 1;
		  }

		  return{
					 connect: function( doOnMsg ){

								if( socket != null )
										  socket.close(); 

								//Obtener id de usuario y nombre de la habitación
								var userid = $('#dataContainer').data('userid'); 
								var room = $('#dataContainer').data('room'); 
								var path = 'ws://'+ window.location.host +'/PracticaFinalASR/wsEndpoint' //TODO: flexibilizar

								socket = new WebSocket(path);

								var msg = JSON.stringify({
													 user: userid,
													 room: room
								});
								socket.addEventListener('open', (open)=>{
										  //Enviamos el mensaje de arranque de la conexión
										  console.log('Socket opened');
										  setTimeout(()=>{
													 socket.send(msg);
													 console.log("Enviado json: "+msg);
										  },1);
								});
								socket.addEventListener('error',(error)=>{
										  alert('Error' + JSON.stingify(error));
										  console.log('Error' + JSON.stingify(error));
										  socket.close();
										  socket = null;
								});
								socket.addEventListener('close',(close)=>{
										  ,gclert('Close' + JSON.stingify(error));
										  console.log('Close' + JSON.stingify(error));
										  socket.close();
										  socket = null;
								});

								switch( doOnMsg ){
								case 'chat':
										  socket.addEventListener('message', onMessageChat );
										  break;
								case 'rooms':
										  socket.addEventListener('message', onMessageChat );
										  break;
								}

					 },
					 
					 send: function( msg ){
								if( socket.readyState != 1 ){
										  alert("El socket no esta conectado, estado: " + socket.readyState);
										  return
								}
								console.log("Enviando mensaje: "+msg);
								//TODO: comprobar si el mensaje es accionado desde un formulario o si se adquiere la información manualmente
								var msg_ = $('#msgTextBox').innerHTML;

								//borramos la caja de texto
								$('#msgTextBox').value = '';
								socket.send(msg);
					 }
		  }
})()

//EXPORTS
function socket_connect( msgHandling ){
		  alert('on socket connect');
		  chatModule.connect(msgHandling);
}
function socket_send(){
		  alert('on socketSend');
		  msg = $('#messageContent').val();
		  alert('msg: '+msg);
		  chatModule.send(msg);
}

/*-------------------------------------------------------
 EVENTS
 -------------------------------------------------------*/
$(document).ready(function(){
		  var loc = window.location.pathname;
		  var pathid = loc.substr(loc.lastIndexOf('/') + 1);
		  switch(pathid){
		  case 'chat':
					 alert('Ejecutando conexión de chat');
					 socket_connect('chat'); break;
		  case 'rooms':
					 socket_connect('rooms');
					 break;
		  }
})

