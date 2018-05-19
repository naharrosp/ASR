<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-dark">

		  <%--Añadir link a home y logo en ascii-art si es posible--%>
		  <a class="navbar-brand" id="navbar-logo" href="">KAFKACHAT</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					 <span class="navbar-toggler-icon"></span>
		  </button>

		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
					 <%--ALWAYS CHECK IF HAS USER--%>
					 <ul class="navbar-nav my-lg-0">
								<c:if test="${not empty userf}">
										  <li class="nav-item active">
													 <a class="nav-link" href="#">logout</a>
										  </li>
								</c:if>
								<c:if test="${ empty userf}">
										  <li class="nav-item active">
													 <a class="nav-link" href="#">login</a>
										  </li>
								</c:if>
					 </ul>
		  </div>
</nav>
