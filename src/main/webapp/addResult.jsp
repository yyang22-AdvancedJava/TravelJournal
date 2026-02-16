<%@ include file="head.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 1/24/26
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Result Page</title>
</head>
<body>
<%--    <h2>Add Success!</h2>--%>
    <!-- Check if the message attribute is set -->
    <c:if test="${not empty message}">
        <div class="alert alert-info">
                ${message}
        </div>
    </c:if>

    <!-- You can also add a redirect or link to return to the user list -->
    <a href="signup.jsp">Go back to the Sign Up page</a>
</body>
</html>
