<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Cerca Libro</title>
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

         <div class='card'>
		    <div class='card-header'>
		        <h5>Cerca tavolo</h5> 
		    </div>
		 	<%-- alert con lista errori --%>
			<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
				<c:forEach var = "errorItem" items="${tavoloErrors }">
		        	<ul>
						<li> ${errorItem }</li>	
					</ul>
		      	</c:forEach>
			</div>
		<!-- Main jumbotron for a primary marketing message or call to action -->

		<div class='card-body'>
			<form action="${pageContext.request.contextPath}/tavolo/ExecuteSearchTavoloServlet" method="get">
			<input class = "form-control" type = "hidden" name ="idUser" id = "idUtente" value = "${userAttribute.id}">
				
					<div class="form-group col-md-6">
						<label>Esperienza Minima </label> <input class="form-control" type="number" id= "esperienzaMin"name="esperienza" >
					</div>
					<div class="form-group col-md-6">
						<label>Puntata Minima</label> <input class="form-control" type="number" step = "0.50" id= "cifraMin" name="cifra" >
					</div>
					<div class="form-group col-md-6">
						<label>Denominanzione</label> <input class="form-control" type="text" id= "denom" name="denominazione" >
					</div>
					<div class="form-group col-md-6">
						<label>Data Creazione</label> <input class="form-control" type="date" id= "data" name="data" >
					</div>

				
				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Cerca</button>

			</form>
			</div>
			<div class='card-footer'>
					<a href="${pageContext.request.contextPath}/tavolo/gestioneTavolo.jsp" class='btn btn-outline-secondary' style='width: 80px'> 
						<i class='fa fa-chevron-left'></i> Back
					</a>
				</div>
		</div>





	</main>
</body>
</html>