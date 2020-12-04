<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>modifica libro</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
<script type="text/javascript">
    
        $(document).ready(function() {
    	$("form").submit(function( event ) {
    		$("#errorDenominazione").hide();
			$("#errorEsperienza").hide();
			$("#errorCifra").hide();
			
			
			var controlli = true;
			if(!$("#denominazione")[0].value){
				$("#errorDenominazione").show();
				controlli= false;
			}
			if(!$("#esperienza")[0].value) {
				$("#errorEsperienza").show();
				controlli= false;
			}
			if(!$("#cifra")[0].value){
				$("#errorCifra").show();
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
	<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${tavoloErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica tavolo</h5>
			</div>
			<div class='card-body'>
				<form method="post" action="${pageContext.request.contextPath}/tavolo/ExecuteUpdateTavoloServlet" novalidate="novalidate">
					<div class="form-row">
						<input type="hidden" name="idTavolo" id="idTavolo" value="${requestScope.tavoloAttribute.id}">
						<input type="hidden" name="idUser" id="idUser" value="${requestScope.userAttribute.id}">
						<div class="form-group col-md-6">
							<label>Denominazione <span class="text-danger"></span></label> 
							<input type="text" name="denominazione" id="denominazione" class="form-control" value="${requestScope.tavoloAttribute.denominazione}">
							<div class="invalid-feedback" id= "errorDenominazione">
                             Attenzione! Devi inserire la denominazione del tavolo!
                            </div>
						</div>
						
						<div class="form-group col-md-6">
							<label>Esperienza Minima <span class="text-danger"></span></label>
							<input type="number" name="esperienza" id="esperienza" class="form-control" value="${requestScope.tavoloAttribute.expMin}"min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;">
							<div class="invalid-feedback" id= "errorEsperienza">
                             Attenzione! Devi inserire esperienza minima del tavolo!
                        </div>
						</div>
							<div class="form-group col-md-6">
							<label>Cifra Minima <span class="text-danger"></span></label>
							<input type="number" name="cifra" id="cifra" class="form-control" value="${requestScope.tavoloAttribute.cifraMin}" min="0" max="9999" maxlength="4" 
							oninput="this.value=this.value.slice(0,this.maxLength||1/1);this.value=(this.value < 1) ? (0) : this.value;">
							<div class="invalid-feedback" id= "errorCifra">
                             Attenzione! Devi inserire cifra minima del tavolo!
                             </div>
						</div>
						
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