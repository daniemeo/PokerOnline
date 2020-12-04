<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="../header.jsp" />
    
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    
    <title>Gioca!!!</title>
  </head>
  <body>
  <jsp:include page="../navbar.jsp"></jsp:include>
  
	<main role="main" class="container">
	 
        <div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

	  <!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron" >
	    	<div class="container">
	      		<h2 class="display-3">
	      			Tavolo: ${user.tavolo.denominazione }
					
				</h2>
				<h3>
					Credito Residuo: ${user.creditoAccumulato } Euro
				</h3>
	     
				<c:if test="${!isAlVerde }">
			    	<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/ExecuteGiocaPartitaServlet?idTavolo=${user.tavolo.id}" role="button"> Gioca &raquo;</a></p>
		     	</c:if>
		      	<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareLasciaPartitaServlet?idTavolo=${user.tavolo.id}" role="button"> Lascia &raquo;</a></p>
	      
	    	</div>
	  </div>
	  
	  
	    <hr>
	
	 
	
	</main>
	
	<jsp:include page="../footer.jsp" />
  </body>
</html>