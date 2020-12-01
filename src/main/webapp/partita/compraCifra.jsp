<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Compra credito</title>
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">

	

         <div class='card'>
		    <div class='card-header'>
		        <h5>Compra credito</h5> 
		    </div>
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

		<div class='card-body'>
			<form action="${pageContext.request.contextPath}/ExecuteCompraCreditoServlet?idUser=${user.id}" method="post">
			
				<input class = "form-control" type = "hidden" name ="idUser" id = "idUser" value ="${requestScope.userAttribute.id}">
					<div class="form-group col-md-6">
						<label>Credito </label> <input class="form-control" type="number" id= "cifra"name="cifra" >
					</div>
					

				
				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>


			</form>
			</div>
			<div class='card-footer'>
					<a href="${pageContext.request.contextPath}/PrepareCompraCreditoServlet?idUser=${user.id}"
						class='btn btn-outline-secondary' style='width: 80px'> <i
						class='fa fa-chevron-left'></i> Back
					</a>
				</div>
		</div>





	</main>
</body>
</html>