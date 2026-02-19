<%@include file="taglib.jsp"%>
<c:set var="title" value="My Journals" />
<%@include file="head.jsp"%>
<html>
<body>
<h3>Welcome ${userName}</h3>
<%--

<c:if test="${not empty message}">
    <div class="alert alert-info">
            ${message}
    </div>
</c:if>
--%>

<%--
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
--%>
<c:forEach items="${journals}" var="journal">
    <div class="card ms-3 mb-3 mt-3" style="width: 50rem; height: 15rem">
        <div class="card-header">
            <div class="d-flex align-items-baseline">
                <h3 class="card-title text-start">${journal.title}</h3>
                <h4 class="card-subtitle mb-2 text-body-secondary text-end ms-auto" >${journal.location}</h4>
            </div>
        </div>
        <div class="card-body">
            <h4 class="card-subtitle mb-2 text-body-secondary text-end ms-auto" >${journal.weather}</h4>
            <h4 class="card-text">${journal.content}</h4>
            <%--
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
            --%>
        </div>
        <div class="text-end mb-3">
            <form action="deleteJournal" method="post" style="display:inline">
                <input type="hidden" name="id" value="${journal.id}">
                <button type="submit" style="background:none; border:none; padding:0; outline:none; cursor:pointer;">
                    <img src="img/trashbin.png" style="width: 50px">
                </button>
            </form>

        </div>
    </div>
</c:forEach>
</body>
</html>
