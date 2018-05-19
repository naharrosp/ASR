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
					 <%--<div class="container">--%>
								<div class="row">
										  <div class="col-sm-2"> </div>
										  <div class="col-sm-8">
													 <pre>
#    #    #    ####### #    #    #     #####  #     #    #    ####### 
#   #    # #   #       #   #    # #   #     # #     #   # #      #    
#  #    #   #  #       #  #    #   #  #       #     #  #   #     #    
###    #     # #####   ###    #     # #       ####### #     #    #    
#  #   ####### #       #  #   ####### #       #     # #######    #    
#   #  #     # #       #   #  #     # #     # #     # #     #    #    
#    # #     # #       #    # #     #  #####  #     # #     #    #    
Better than irc!!
													 </pre>
													 <form role="form" action="" method="POST" >
																<%--IMAGEN --%>
																<label for="inputEmail" class="sr-only">Username</label>
																<input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
																<label for="inputPassword" class="sr-only">Password</label>
																<input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
																<br>
																<input type="submit" class="btn btn-default" value="Login!">
													 </form>
										  </div>
										  <div class="col-sm-2"></div>
								</div>
					 </div>
		  </body>
</html>
