<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<body>
<c:if test="${not empty message}">
    <div class="alert alert-info">
            ${message}
    </div>
</c:if>
<div class="container">
    <h2>User Display Exercise</h2>
    <form action="searchUser" class="form-inline">
        <div class="form-group">
            <label for="searchTerm">Search</label>
            <input type="text" class="form-control" id="searchTerm" name="searchTerm" placeholder="Type last name to search">
        </div>
        <button type="submit" name="submit" value="search" class="btn btn-primary">Search</button>
        <button type="submit" name="submit" value="ViewAll" class="btn btn-primary">View All Users / Edit User</button>
    </form>
</div>
</body>
</html>
