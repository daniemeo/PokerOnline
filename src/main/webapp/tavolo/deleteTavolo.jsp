<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="/header.jsp" />
     <title>controllo cancellazione</title>
       <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
</head>
<body>
  <jsp:include page="/navbar.jsp" />
  <main role="main" class="container">
   <div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
    <div class='card'>
      <div class='card-header'>
		        Controllo delete
   	  </div>
   	  <div class='card-body'>
	     Vuoi eliminare il tavolo?
	   </div>
	<form method="post" action="${pageContext.request.contextPath}/ExecuteDeleteTavoloServlet" novalidate="novalidate">
				<input type="hidden" name="idTavolo" id="idTavolo" value= "${requestScope.delete.id}">
					
  				<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
  				<a href="${pageContext.request.contextPath}/PrepareGestioneTavoloServlet"
		         class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
	</form>	
    </div>
</main>	
<jsp:include page="/footer.jsp" />			
</body>


</html>