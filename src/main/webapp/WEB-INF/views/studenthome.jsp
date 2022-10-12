<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>Welcome Back!</title>
</head>
<body>
<div>
<h3>Menu</h3>
<li> <a href= "profile">Profile</a> </li>
<li><a href= "registeredClasses">Registered Classes</a></li>
<li><a href= "shopclasses">Shop Classes</a></li>
<li><a href= "messages">Messages (to instructor)</a></li>

<li><form action= "/search">
<input type= "text" name="searchphrase" placeholder= "Search Videos"></a></li>
<button> Submit</button>
</form>
</div>
<br>
<div>
<a href= "http://accounts.google.com/o/oauth2/v2/auth?response_type=code&scope=https://www.googleapis.com/auth/youtube.readonly&access_type=offline&include_granted_scopes=true&state=state_parameter_passthrough_value&client_id=46984612081-k86779oo3t3rvfgbvinevi7tarcq1bjs.apps.googleusercontent.com&redirect_uri=http://localhost:8080/oauth-google-callback">For "My Video List" log in to Google</a>
</div>
<br>
<a href= "logout">Logout</a>
</body>
</html>