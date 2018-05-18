<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		  <head>
					 <c:import page=/imports.jsp"/>
					 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
					 <title>Chat - ${chat.name}</title>
		  </head>
		  <body>
					 <c:import page="/navbar.jsp"/>

					 <%--HIDDEN DIV CON TODA LA INFORMACIÓN ACERCA DE COLORES ASOCIADA --%>
					 <div id="usersInfo" style="visibility:none">
								<%--${}--%>
					 </div>

					 <c:import page="/navbar.jsp"/>
					 <div class="container">
								<ul id="message-board">
								</ul>
					 </div>
					 <div class="container-fluid">
								<%--AÑADIR EL SERVERLET PARA MANEJAR LOS MENSAJES DE ENTRADA--%>
								<form action="" method="POST">
										  <div class="col-sm-8">
													 <%--TEXT INPUT--%>
										  </div>
										  <button type="submit">
													 Enviar
										  </button>
								</form>
					 </div>
		  </body>
</html>
