<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>Pagina dei risultati</title>

<!-- style per le pagine diverse dalla index -->
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
			<div class='card-header'>Risultati ricerca utenti</div>
			
			<div class='table-responsive'>
				<table class='table table-striped '>
					<thead>
						<tr>
							<th>Nome</th>
							<th>Cognome</th>
							<th>Username</th>
							<th>Data Registrazione</th>
							<th>Stato</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${requestScope.listaUser}" var="userRes">
							<tr>
								<td><c:out value="${userRes.nome}"></c:out></td>
								<td><c:out value="${userRes.cognome}"></c:out></td>
								<td><c:out value="${userRes.username}"></c:out></td>
								<td><c:out value="${userRes.dataRegistrazione}"></c:out></td>
								<td><c:out value="${userRes.stato}"></c:out></td>
								<td><a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/admin/VisualizzaUtenteServlet?idUser=${userRes.id}"> Visualizza</a> 
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/admin/PrepareUpdateUtenteServlet?idUser=${userRes.id}">Modifica</a>
								    <a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/admin/DisabilitaAbilitaUtenteServlet?idUser=${userRes.id}">Disattiva/Attiva</a>
								     
								</td> 

						   </tr>
					   </c:forEach>
					</tbody>
				</table>
				<div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/PrepareSearchPartitaServlet"
		         class='btn btn-outline-secondary' style='width:80px' >
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
				

			</div>
		</div>
	</main>
	<jsp:include page="/footer.jsp" />
</body>
</html>