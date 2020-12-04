<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<title>Registrazione </title>
	
	<!-- style per le pagine diverse dalla index -->
	<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css" >
   
    
 <script type="text/javascript">
    
        $(document).ready(function() {
    	$("form").submit(function( event ) {
			$("#errorNome").hide();
			$("#errorCognome").hide();
			$("#errorUsername").hide();
			$("#errorPassword").hide();
			var controlli = true;
			if(!$("#nomeInputId")[0].value) {
				$("#errorNome").show();
				controlli= false;
			}
			if(!$("#cognomeInputId")[0].value){
				$("#errorCognome").show();
				controlli= false;
			}
			if(!$("#usernameInputId")[0].value){
				$("#errorUsername").show();
				controlli= false;
			}
			if(!$("#passwordInputId")[0].value){
				$("#errorPassword").show();
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

<div class="container">


      
    <div class="page-header">
	  <h3>Registrazione</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty userErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${userErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

      	<form class="form-horizontal" action="${pageContext.request.contextPath }/ExecuteRegistrazioneServlet" method="post" >
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId" name="nome" value="${utenteAttribute.nome }" >
				    <div class="invalid-feedback" id= "errorNome">
                         Attenzione! Devi inserire il nome!
                    </div>
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId" name="cognome" value="${utenteAttribute.cognome }" >
					<div class="invalid-feedback" id = "errorCognome">
                      Attenzione! Devi inserire il cognome!
                    </div>
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="usernameInputId">Username:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="usernameInputId" name="username" value="${utenteAttribute.username }">
					<div class="invalid-feedback" id = "errorUsername">
                      	Attenzione! Devi inserire username!
                 	</div>
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="passwordInputId">Password:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="passwordInputId" name="password" value="${utenteAttribute.password }" required>
					<div class="invalid-feedback" id = "errorPassword">
                      	Attenzione! Devi inserire la password!
                 	</div>
			 	</div>
  			</div>
  			<input type = "hidden" name= "esperienza" id = "esperienza" value="${utenteAttribute.expAccumulata}" />
  			<input type = "hidden" name= "credito" id = " credito" value="${utenteAttribute.creditoAccumulato}" />
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->



</body>
</html>