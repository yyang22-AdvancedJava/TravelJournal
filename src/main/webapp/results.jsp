<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results" />
<%@include file="head.jsp"%>
<%--

<script type="text/javascript" class="init">
    $(document).ready( function() {
        $('#userTable').DataTable();
    });
</script>
--%>

<html><body>
<div class="container">
    <h2>Search Results: </h2>
    <table id="userTable" class="table">
        <thead>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>User Name</th>
            <th></th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userName}</td>
                <td>
                    <form action="displayUserInfo" method="get" style="display:inline">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit">Edit</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <a href="index.jsp">Go back to the Homepage</a>
</div>
</body>
</html>
