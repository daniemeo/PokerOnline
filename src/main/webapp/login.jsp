<!doctype html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="it">
	<head>
	  <!-- Required meta tags -->
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	  <!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="./assets/css/bootstrap.min.css" >
	   
	  <title>Accedi</title>
	  
	  <!-- Favicons -->
	<link rel="apple-touch-icon" href="./assets/img/favicons/apple-touch-icon.png" sizes="180x180">
	<link rel="icon" href="./assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
	<link rel="icon" href="./assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
	<link rel="manifest" href="./assets/img/favicons/manifest.json">
	<link rel="mask-icon" href="./assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
	<link rel="icon" href="./assets/img/favicons/favicon.ico">
	<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
	<meta name="msapplication-config" content="./assets/img/favicons/browserconfig.xml">
	<meta name="theme-color" content="#563d7c">
	
	<script type="text/javascript">
    
        $(document).ready(function() {
    	$("form").submit(function( event ) {
    		
			$("#errorUsername").hide();
			$("#errorPassword").hide();
			
			var controlli = true;
			if(!$("#username")[0].value) {
				$("#errorUsername").show();
				controlli= false;
			}
			if(!$("#password")[0].value){
				$("#errorPassword").show();
				controlli= false;
			}
			if(!controlli) {
				event.preventDefault();
			} 
		});
    }) 
		

</script>
	
	   <style>
	    .bd-placeholder-img {
	      font-size: 1.125rem;
	      text-anchor: middle;
	      -webkit-user-select: none;
	      -moz-user-select: none;
	      -ms-user-select: none;
	      user-select: none;
	    }
	
	    @media (min-width: 768px) {
	      .bd-placeholder-img-lg {
	        font-size: 3.5rem;
	      }
	    }
	  </style>
	  
	  <!-- Custom styles for this template -->
	  <link href="./assets/css/signin.css" rel="stylesheet">
	</head>
	<body class="text-center">
		
	   	<form class="form-signin" action="LoginServlet" method="post" novalidate="novalidate">
	   	
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
	   	
		
			
			
		  <img class="mb-4" src="./assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		  <label for="inputUsername" class="sr-only">Username</label>
		  <input type="text" id="Idusername" name="username" class="form-control" placeholder="Username" required autofocus>
		   <div class="invalid-feedback" id= "errorUsername">
           Attenzione! Devi inserire username!
            </div>
		  <label for="password" class="sr-only">Password</label>
		  <input type="password" id="Idpassword" name="password" class="form-control" placeholder="password" required>
		  <div class="invalid-feedback" id= "errorPassword">
           Attenzione! Devi inserire la password!
          </div>
		  <div class="checkbox mb-3">
		    <label>
		      <input type="checkbox" value="remember-me"> Remember me
		    </label>
		  </div>
		  
	
		  <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">Sign in</button>
		  <br>
		  <a href="${pageContext.request.contextPath}/PrepareRegistrazioneServlet"> Registrati!!</a>
		  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
		</form>
	</body>
</html>