<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="imports.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fase de reconocimiento facial</title>
</head>
<body>

		  <div class="container">

					 <h1>SMILE!!!</h1>

					 <div id="my_camera" ></div>

					 <%--completar href para sacar la foto, no subirla--%>
					 <a onclick="take_snapshot()" class="button" role="button">Sacar foto</a>

					 <%--mostrar la imagen sacada, mostrar una silueta si no hay ninguna--%>
					 <div id="photo_result" ></div>

					 <%--botón para subir la imagen mediante post--%>
					 <a onclick="upload_image()" class="button" role="button">Sacar foto</a>

		</div>
</body>
</html>
