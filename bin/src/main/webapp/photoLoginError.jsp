<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="imports.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error en el reconocimiento facial</title>
</head>
<body>
		  <div class="container">

					 <%--Comparar las estadísticas de las dos fotos--%>
					 <%--Además de mostrar las características de ambos casos, también resaltamos los errores--%>

					 <div class="row">
								<div class="col-sm">
										  <ul class="list-group">
													 <li class="list-group-item ${db.sexo.alert}"> Sexo: ${db.sexo}</li>
													 <li class="list-group-item ${db.edad.alert}"> Edad: ${db.edad}</li>
										  </ul>
								</div>
								<div class="col-sm">
										  <ul class="list-group">
													 <li class="list-group-item ${inst.sexo.alert}"> Sexo: ${inst.sexo} </li>
													 <li class="list-group-item ${inst.edad.alert}"> Edad: ${inst.edad} </li>
										  </ul>
								</div>
					 </div>

					 <%--botón para volver a sacar una foto--%>
					 <%--TODO: referencia al serverlet--%>
					 <a href="" class="button" role="button">Volver a intenarlo</a>
					 <%--botón para volver al inicio--%>
					 <%--TODO: referencia a bienvenida--%>
					 <a href="" class="button" role="button">Volver a página inicial</a>


		  </div>
</body>
</html>
