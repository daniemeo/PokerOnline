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
    
    <title>Gestione tavolo!</title>
  </head>
  <body>
  <jsp:include page="../navbar.jsp"></jsp:include>
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Gestione Tavoli !</h1>
	     
	      <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/tavolo/PrepareInsertTavoloServlet" role="button"> Inserisci Tavolo &raquo;</a></p>
	       <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/tavolo/PrepareSearchTavoloServlet?idUser=${user.id}" role="button"> Cerca i tuoi tavoli &raquo;</a></p>
	      
	    </div>
	  </div>
	  
	  
	    <hr>
	
	 
	
	</main>
	
	<jsp:include page="../footer.jsp" />
  </body>
</html>