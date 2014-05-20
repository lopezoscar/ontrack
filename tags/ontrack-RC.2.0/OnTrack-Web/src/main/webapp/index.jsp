<%@page import="com.sappe.ontrack.security.GoogleAuthHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="AR_ES">
<head>
	 	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
		 <meta charset="UTF-8"/>
		 <!-- Bootstrap -->
    	 <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    	 <link href="css/bootstrap-responsive.css" rel="stylesheet">

    	 <script src="js/jquery.js"></script>
    	 <script src="js/bootstrap.js"></script>
		 <!-- CSS  -->
    	 <link rel="css/global.css" rel="stylesheet" />
    	 
<title>OnTrack Login</title>

<style>
	.header{
		height: 15%;
		width:100%;
		border: 1px solid black;
	}
	#header, #footer {
		width:100%;
		float:left;
	}
	#footer{
		
	}
	
	.wrap {
		position:relative;
		margin:0 auto;
               /*replace 900px with your width*/
		width:100%;
		background-color: rgb(F,F,0);
	}
	
	.title{
		font-size: 120px;
		line-height: 1;
		letter-spacing: -2px;
	}
	
	.subtitle{
		font-size: 40px;
		font-weight: 200;
		line-height: 1.25;
	}	
	
</style>





</head>
<body>








	
	<div class="container"> 
		<div >
			<h1 class="text-center title">OnTrack</h1>
			<p class="text-center subtitle">Administra todos tus issues de la manera más fácil</p>
		
		
	
		<div align="center">
		<%
			/*
			 * The GoogleAuthHelper handles all the heavy lifting, and contains all "secrets"
			 * required for constructing a google login url.
			 */
			final GoogleAuthHelper helper = new GoogleAuthHelper();
			
			if (request.getParameter("code") == null
					|| request.getParameter("state") == null) {

				/*
				 * initial visit to the page
				 */
				out.println("<a class='btn btn-primary btn-large' href='" + helper.buildLoginUrl()+"'>Ingresa Con Google</a>");
						
				/*
				 * set the secure state token in session to be able to track what we sent to google
				 */
				session.setAttribute("state", helper.getStateToken());

			} else if (request.getParameter("code") != null && request.getParameter("state") != null
					&& request.getParameter("state").equals(session.getAttribute("state"))) {

				session.removeAttribute("state");

				out.println("<pre>");
				/*
				 * Executes after google redirects to the callback url.
				 * Please note that the state request parameter is for convenience to differentiate
				 * between authentication methods (ex. facebook oauth, google oauth, twitter, in-house).
				 * 
				 * GoogleAuthHelper()#getUserInfoJson(String) method returns a String containing
				 * the json representation of the authenticated user's information. 
				 * At this point you should parse and persist the info.
				 */

				out.println(helper.getUserInfoJson(request.getParameter("code")));

				out.println("</pre>");
			}
		%>
	</div>
	</div>
	</div>
	
	<div id="footer">
		<p></p>
	</div>
	

</body>
</html>
