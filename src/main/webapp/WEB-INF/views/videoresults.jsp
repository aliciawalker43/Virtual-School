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
<title>Video Results</title>
</head>
<body>
<h2>Video Results...Did you find what you were looking for?</h2>
<div>   
        <c:forEach var="result" items= "${results }">
        <p> ${result.snippet.title}
        ${result.id.videoId}
        <img src="${result.snippet.thumbnails.dfault.url}">
        </p>
        </c:forEach>
</div>

<a href= "//">Return</a>



</body>
</html>