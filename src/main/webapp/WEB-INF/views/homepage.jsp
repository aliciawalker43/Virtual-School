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
<title>Virtual Art School</title>
</head>
<body>
<div>
<h1>Welcome to Rach's Virtual Art School</h1>
</div>

<p class="message"><c:out value="${ message }"/></p>

<!--<div><a href= "loging">Sign in here</a><br>-->

<form action="login" method= "post">
<input type="name" name= "username" placeholder= "email">
<input type="password" name= "password">
<button class="btn btn-outline-primary">Submit</button>
</form>
</div>
<br>
<div> Not a Student yet? <br>
Click link to <a href= "/signup">Signup</a>.
</div>

<!--<p> <a href=" "> Instructor Login</a></p>-->


</body>
</html>