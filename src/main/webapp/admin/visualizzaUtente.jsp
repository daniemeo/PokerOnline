<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Visualizza Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza Utente
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id Utente:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.utenteDettaglio.id}"></c:out></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.utenteDettaglio.nome}"></c:out></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.utenteDettaglio.cognome}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Username</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.utenteDettaglio.username}"></c:out></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data Registrazione</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.utenteDettaglio.dataRegistrazione}"/></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Stato</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.utenteDettaglio.stato}"/></dd>
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Ruolo</dt>
				  <dd class="col-sm-9">
				  <c:forEach items="${requestScope.utenteDettaglio.ruoli}" var="ruolo">
				  
				   	<c:out value="${ruolo.codice }"></c:out>
				   	<br>
				  </c:forEach>				 		
		    	</dl>
		    		
					    	    	
					    		
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/PrepareSearchPartitaServlet"
		         class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>