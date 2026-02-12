<%@include file="head.jsp"%>
<html>
<head>
    <title>Display User Info</title>
</head>
<body>
<div class="container">
    <h2>User Information</h2>

    <form action="updateUser" method="POST">

        <input type="hidden" name="id" value="${user.id}"> <!-- id는 숨겨서 전송 -->

        <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${user.firstName}" ><br>
        </div>
        <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${user.lastName}" ><br>
        </div>
        <div class="form-group">
        <label for="userName">User Name:</label>
        <input type="text" id="userName" name="userName" value="${user.userName}" ><br>
        </div>
        <button type="submit" name="submit" value="edit" class="btn btn-primary">Save</button>
    </form>
    <hr>
    <a href="index.jsp">Go back to the Homepage</a>
</div>

</body>
</html>
