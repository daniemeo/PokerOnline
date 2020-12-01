<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<title>Registrazione </title>
	
	<!-- style per le pagine diverse dalla index -->
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css" >
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

      	<form class="form-horizontal" action="${pageContext.request.contextPath }/ExecuteRegistrazioneServlet" method="post">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId" name="nome" value="${utenteAttribute.nome }">
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId" name="cognome" value="${utenteAttribute.cognome }">
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="usernameInputId">Username:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="usernameInputId" name="username" value="${utenteAttribute.username }">
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="passwordInputId">Password:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="passwordInputId" name="password" value="${utenteAttribute.password }">
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