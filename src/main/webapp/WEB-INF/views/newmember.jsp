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
<meta charset="UTF-8">
<title>Signup Here</title>
</head>
<body>

<div><form action="signup" method= "post">
<input type="text" name= "name" placeholder= "name">
<input type="text" name= "username" placeholder= "username">
<input type="password" name= "password" placeholder= "password">
<input type="password" name= "confirmpassword" placeholder= "re-eneter password">
<button class="btn btn-outline-primary">Submit</button>
</form>
</div>

</body>
</html>