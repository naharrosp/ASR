<%@ taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		  <head>
					 <c:import url="/imports.jsp"/>
					 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
					 <title>Chat - ${chat.name}</title>
		  </head>
		  <body>
					 <c:import url="/navbar.jsp"/>
					 <%-- INFORMACIÓN PARA EL WEBSOCKET --%>
					 <div id="dataContainer" 
										  data-userid="${userid}"
										  data-room="${roompath}"
										  style="visibility:none">
					 </div>

					 <div class="container">
								<ul id="message-board">
								</ul>
								<div class="row chatInput">
										  <div class="col-sm-3">
													 <%--Padding --%>
										  </div>
										  <div class="col-sm-6">
													 <form action="javascript:socket_send()" method="POST">
																<label>Text box</label>
																<input type = "text"
																					 id = "messageContent"
																					 name = "message"
																					 value = "Escribe aquí" />
																<button type="submit">
																		  Enviar
																</button>
													 </form>
										  </div>
										  <div class="col-sm-3">
													 <%--Padding --%>
										  </div>
								</div>
					 </div>
		  </body>
</html>
