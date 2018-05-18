<%@ taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		  <head>
					 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
					 <title>Fase de reconocimiento facial</title>
		  </head>
		  <body>
					 <c:import url="/navbar.jsp"/>

					 <div class="container row">

								<h1>SMILE!!!</h1>

								<div id="my_camera" ></div>

								<%--completar href para sacar la foto, no subirla--%>
								<a href="javascript:take_snapshot()" class="btn btn-primary" role="button"> Sacar foto</a>

								<%--mostrar la imagen sacada, mostrar una silueta si no hay ninguna--%>
								<%--Investigar por qué no se resuelve silueta--%>
								<img id="photo_result src=img/shadow.png" ></div>

								<%--botón para subir la imagen mediante post--%>
								<a href="javascript:upload_image()" class="btn btn-primary" role="button"> Subir foto</a>

					 </div>
		  </body>
		  <c:import url="imports.jsp" />
</html>
