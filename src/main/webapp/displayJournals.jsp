<%--
<%@include file="taglib.jsp"%>
<c:set var="title" value="My Journals" />
&lt;%&ndash;<%@include file="head.jsp"%>&ndash;%&gt;
<c:import url="head.jsp" />
<html>
<body>
<h3>Welcome ${userName}</h3>
&lt;%&ndash;

<c:if test="${not empty message}">
    <div class="alert alert-info">
            ${message}
    </div>
</c:if>
&ndash;%&gt;

&lt;%&ndash;
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
&ndash;%&gt;

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
&lt;%&ndash;

            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
&ndash;%&gt;

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
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>

<%--<h3 class="mb-4">Welcome, ${userName}</h3>--%>

<c:forEach items="${journals}" var="journal" varStatus="status">
    <%-- custom.css에 정의한 클래스를 사용하여 이미지 속 디자인을 재현 --%>
    <div class="journal-card ${status.index % 2 == 0 ? 'bg-journal-gray' : 'bg-journal-mint'} shadow-sm">

        <div class="d-flex align-items-baseline mb-3">
            <h3 class="journal-title mb-0">${journal.title}</h3>
            <h4 class="journal-city ms-auto">${journal.location}</h4>
        </div>

        <div class="journal-body">
            <h5 class="text-secondary mb-2">${journal.weather}</h5>
            <p class="fs-5">${journal.content}</p>
        </div>

        <div class="text-end">
            <form action="deleteJournal" method="post" style="display:inline">
                <input type="hidden" name="id" value="${journal.id}">
                <button type="submit" style="background:none; border:none; padding:0; cursor:pointer;">
                    <img src="img/trashbin.png" style="width: 45px" alt="Delete">
                </button>
            </form>
        </div>
    </div>
</c:forEach>
