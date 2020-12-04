<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Cerca Utente</title>
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="/navbar.jsp" />

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
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
			<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty userErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${userErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

     <div class='card'>
	    <div class='card-header'>
	        <h5>Cerca Utente</h5> 
	    </div>

		<!-- Main jumbotron for a primary marketing message or call to action -->

		<div class='card-body'>
			<form action="${pageContext.request.contextPath}/admin/ExecuteSearchUtenteServlet" method="post">
			
				
					<div class="form-group col-md-6">
						<label>Nome</label> <input class="form-control" type="text" id= "nome" name="nome" >
					</div>
					<div class="form-group col-md-6">
						<label>Cognome</label> <input class="form-control" type="text" id= "cognome" name="cognome" >
					</div>
					<div class="form-group col-md-6">
						<label>Username</label> <input class="form-control" type="text" id= "username" name="username" >
					</div>
					<div class="form-group col-md-6">
						<label>Data Registrazione</label> <input class="form-control" type="date" id= "data" name="data" >
					</div>
					
					<div class="form-group col-md-6">
                         <label>Stato</label>
                            <select id="listaStati" name="stato" class="form-control" >	
		                   		<option value="${NULL}">- Seleziona Stato -</option>
			               		<c:forEach items="${listaStati}" var="stato">
			                		<c:if test="${stato != 'NULL' }">
				             			<option value="${stato}"><c:out value="${stato}"/></option>
				            		</c:if>
			               		</c:forEach>
		                 	</select>
				   </div>
				   	<div class="form-group col-md-6">
                         <label>Ruoli</label>
                         	<select id="listaRuoli" name="idRuolo" class="form-control" >	
		                  	 	<option value="${NULL}">- Seleziona Ruolo -</option>
		               			<c:forEach items="${listaRuoli}" var="ruolo">
			                		<c:if test="${ruolo != 'NULL' }">
				             			<option value="${ruolo.id}">"${ruolo.codice}"</option>
				            		</c:if>
			             	   </c:forEach>
		                	 </select>
				   </div>
				   
				<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
			</form>
		</div>
		<div class='card-footer'>
			<a href="${pageContext.request.contextPath}/home.jsp"
				class='btn btn-outline-secondary' style='width: 80px'> <i
				class='fa fa-chevron-left'></i> Back
			</a>
		</div>
	</div>





	</main>
</body>
</html>