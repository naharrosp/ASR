/**
 * 
 */

/*-------------------------------------------------------
 PHOTO LOGIN
 -------------------------------------------------------*/
var photoLoginModule = (function(){

		  Webcam.set({
								width: 320,
								height: 240,
								image_format: 'jpeg',
								jpeg_quality: 90
		  });
		  Webcam.attach( '#my_camera' );

		  var dataSrc;

		  /*
			Cambiar la función para quizá mostrar la foto y otra para subirla
			*/
		  return{
					 take_snapshot: function(){
								// take snapshot and get image data
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







