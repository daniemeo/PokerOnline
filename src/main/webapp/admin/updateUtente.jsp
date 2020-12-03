<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>modifica utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	   <c:forEach items= "${requestScope.errorMessage}" var= "errore">
		 <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"role="alert">
			${errore}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		</c:forEach>
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
				<h5>Modifica utente</h5>
			</div>
			<div class='card-body'>
				<form method="post" action="${pageContext.request.contextPath}/admin/ExecuteUpdateUtenteServlet" novalidate="novalidate">
					<div class="form-row">
						<input type="hidden" name="idUser" id="idUser" value="${requestScope.userId}">
						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger"></span></label> 
							<input type="text" name="nome" id="nome" class="form-control" value="${requestScope.userAttribute.nome}">
						</div>
						
						<div class="form-group col-md-6">
							<label>Cognome<span class="text-danger"></span></label>
							<input type="text" name="cognome" id="cognome" class="form-control" value="${requestScope.userAttribute.cognome}">
						</div>
							<div class="form-group col-md-6">
							<label>Username <span class="text-danger"></span></label>
							<input type="text" name="username" id="username" class="form-control" value="${requestScope.userAttribute.username}">
						</div>
					<c:if test="${isCreato == true }">
						<div class="form-group col-md-6">
	                          <label>Stato</label>
	                             <select id="listaStati" name="stato" class="custom-select browser-default" value="${userAttribute.stato}">	
				                   <option value="${NULL}">- Seleziona Stato -</option>
					               <c:forEach items="${listaStati}" var="stato">
					                <c:if test="${stato != 'NULL' }">
					                <option value="${stato}" ${stato == userAttribute.stato ? 'selected="selected"' : '' }><c:out value="${stato}" /></option>
						            </c:if>
					               </c:forEach>
				                 </select>
				   		</div>
				   		
				   		 <div class="custom-control custom-checkbox">
								   
								  <c:forEach items="${requestScope.listaRuoli}" var="ruolo" >
							    		<input type="checkbox" class="form-check-input" id="ruolo" name="listaRuoli" value="${ruolo.id}"
							    		<c:forEach items="${userAttribute.ruoli}" var="ruoloUtente" >
							    		 	${ruoloUtente eq ruolo.id ? 'checked' : '' }
							    		</c:forEach>
							    		/>
								    	${ruolo.codice}
								    	<br>
								   </c:forEach>
				 
					     </div>
					 </c:if>
					</div>
					<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
				</form>
			</div>
			
			<!-- end card-body -->
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>