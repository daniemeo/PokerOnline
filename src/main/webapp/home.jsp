<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title> Poker Online!</title>
  </head>
  <body>
  	<jsp:include page="./navbar.jsp"></jsp:include>
  	<main role="main">
  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Benvenuto nel nostro Poker Online !</h1>
	        <c:if test="${sessionScope.isSpecialPlayer eq 'true' || sessionScope.isAdmin eq 'true'}">
	      <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/tavolo/PrepareGestioneTavoloServlet" role="button"> Gestione Tavolo &raquo;</a></p>
	        </c:if>
	       <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareGestionePartitaServlet?idUser=${user.id}" role="button"> Play Management &raquo;</a></p>
	     
	         <c:if test="${sessionScope.isAdmin eq 'true' }">
	        <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/admin/PrepareSearchUserServlet" role="button"> Gestione Amministrazione &raquo;</a></p>
	        </c:if>
	    </div>
	  </div>
	  

      </main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>

