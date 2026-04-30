<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>An Error Occurred</title>
</head>
<body>
<h2>Oops! Something went wrong.</h2>
<p>We are sorry for the inconvenience. Our team has been notified.</p>

<%-- Optional: Display the exception message for debugging (remove in production) --%>
<div style="color: red;">
    Error Details: <%= exception != null ? exception.getMessage() : "Unknown error" %>
</div>

<br>
<a href="index.jsp">Return to Home</a>
</body>
</html>