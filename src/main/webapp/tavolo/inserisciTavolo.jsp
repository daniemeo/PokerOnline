<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Inserisci Tavolo</title>
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
	
	
<script type="text/javascript">
    
        $(document).ready(function() {
    	$("form").submit(function( event ) {
			$("#errorEsperienza").hide();
			$("#errorCifra").hide();
			$("#errorDenominazione").hide();
			
			var controlli = true;
			if(!$("#esperienzaMin")[0].value) {
				$("#errorEsperienza").show();
				controlli= false;
			}
			if(!$("#cifraMin")[0].value){
				$("#errorCifra").show();
				controlli= false;
			}
			if(!$("#denom")[0].value){
				$("#errorDenominazione").show();
				controlli= false;
			}
			
			if(!controlli) {
				event.preventDefault();
			} 
		});
    }) 
		

</script>
</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">
		<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${tavoloErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
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
		        <h5>Inserisci Tavolo</h5> 
		    </div>

		<!-- Main jumbotron for a primary marketing message or call to action -->

		<div class='card-body'>
			<form action="${pageContext.request.contextPath}/tavolo/ExecuteInsertTavoloServlet" method="post" class= "needs-validation">
			
				
					<div class="form-group col-md-6">
						<label>Esperienza Minima </label> 
						<input class="form-control" type="number" id= "esperienzaMin" name="esperienza" min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;" >
						<div class="invalid-feedback" id= "errorEsperienza">
                             Attenzione! Devi inserire esperienza minima del tavolo!
                        </div>
					</div>
					<div class="form-group col-md-6">
						<label>Puntata Minima</label>
						<input class="form-control" type="number" id= "cifraMin" name="cifra" min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;" >
						<div class="invalid-feedback" id= "errorCifra">
                             Attenzione! Devi inserire cifra minima del tavolo!
                        </div>
					</div>
					<div class="form-group col-md-6">
						<label>Denominanzione</label> 
						<input class="form-control" type="text" id= "denom" name="denominazione" >
						<div class="invalid-feedback" id= "errorDenominazione">
                             Attenzione! Devi inserire denominazione del tavolo!
                        </div>
					</div>
					

				<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Inserisci</button>

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