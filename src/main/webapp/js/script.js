/**
 * 
 */

/*-------------------------------------------------------
		PHOTO LOGIN
-------------------------------------------------------*/
(function(){

		Webcam.set({
			width: 320,
			height: 240,
			image_format: 'jpeg',
			jpeg_quality: 90
		});
		Webcam.attach( '#my_camera' );

		/*
		Cambiar la función para quizá mostrar la foto y otra para subirla
		*/
		function take_snapshot() {
			// take snapshot and get image data
			Webcam.snap( function(data_uri) {
				// display results in page
				document
				.getElementById('photo_result')
				.setAtribute('background-image', data_uri);
			}
		}

		function upload_image(){
				//Recuperar la uri de la imagen
				var uri = document
				.getElementById('photo-result')
				.getAttribute('background-image');

				//INVESTIGAR CÓMO SUBIR UNA IMÁGEN EN FORMATO BASE 64
				//EN SU DEFECTO INVESTIGAR CÓMO SE PUEDE ENVIAR EN OTRO FORMATO
		}
})()

/*-------------------------------------------------------
		LOGIN
-------------------------------------------------------*/
(function(){


})()







