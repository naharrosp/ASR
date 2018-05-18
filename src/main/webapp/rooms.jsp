<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		  <head>
					 <c:import page=/imports.jsp"/>
					 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
					 <title>Rooms</title>
		  </head>
		  <body>
					 <c:import page="/navbar.jsp"/>
					 <div class="container">
								<ul>
										  <c:forEach var="room" items="roomlist">
													 <%--COMPLETAR CON HREF A ROOM--%>
													 <a href="room?dir=${room.dir}" class "list-group-item">
																${room.name}
													 </a>
										  </c:forEach>
								</ul>
					 </div>
		  </body>
</html>
