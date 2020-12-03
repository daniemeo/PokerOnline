<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Visualizza tavolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
		<div class="row">
			<div class="col-sm-6">
				<div class='card'>
				    <div class='card-header'>
				        Dettaglio del Tavolo
				    </div>
				    <div class='card-body'>
				    	
		    			<dl class="row">
							<dt class="col-sm-4 text-right">Id tavolo:</dt>
							<dd class="col-sm-8"><c:out value="${requestScope.tavoloDettaglio.id}"></c:out></dd>
				    	</dl>
				    	<dl class="row">
							<dt class="col-sm-4 text-right">Denominazione:</dt>
							<dd class="col-sm-8"><c:out value="${requestScope.tavoloDettaglio.denominazione}"></c:out></dd>
				    	</dl>
				    	<dl class="row">
							<dt class="col-sm-4 text-right">Esperienza Minima:</dt>
							<dd class="col-sm-8"><c:out value="${requestScope.tavoloDettaglio.expMin}"></c:out></dd>
				    	</dl>
				    	<dl class="row">
							<dt class="col-sm-4 text-right">Cifra Minima</dt>
							<dd class="col-sm-8"><c:out value="${requestScope.tavoloDettaglio.cifraMin}"></c:out></dd>
				    	</dl>
		    		</div>
				    		
		    	</div>
			    <%-- <div class='card-footer'>
			        <a href="${pageContext.request.contextPath}/tavolo/PrepareSearchTavoloServlet"
			         class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div> --%>
			</div>
			<div class="col-sm-6">
				<div class='card'>
				    <div class='card-header'>
				        Giocatori
				    </div>
		    		<div class='card-body'>
		    			<table class='table table-sm table-hover'>
							<thead>
								<tr>
									<th>Nome</th>
									<th>Cognome</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.tavoloDettaglio.users}" var="giocatore">
					    	    	<tr>
						    	    	<td>
							    			<c:out value="${giocatore.nome }"></c:out>
								 		</td>
								 		 <td>
							    			<c:out value="${giocatore.cognome }"></c:out>
								 		 </td>
							 		 </tr>
					    		</c:forEach>
							</tbody>
						</table>
		    		</div>
		    	</div>
			</div>
		</div>
		
			
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>