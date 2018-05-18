<%@ taglib prefix = "c"  uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		  <head>
					 <c:import url="/imports.jsp"/>
					 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
					 <title>Log In</title>
		  </head>
		  <body>
					 <c:import url="/navbar.jsp"/>

					 <div class="container text-center">

								<div class="col-sm-3"> </div>
								<form role="form" action="" method="POST" class="col-sm-6 form-signin">
								<%--IMAGEN --%>
										  <label for="inputEmail" class="sr-only">Email address</label>
										  <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
										  <label for="inputPassword" class="sr-only">Password</label>
										  <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
										  <label class=mb-4">Nombre de usuario:</label>
										  <input type="text" name="username" required autofocus>
										  <br>
										  <label>Contraseña:</label>
										  <input type="password" name="password">
										  <br>
										  <input type="submit" class="btn btn-default" value="Login!">
										  <br>
								</form>
								<div class="col-sm-3"></div>
					 </div>
		  </body>
</html>
