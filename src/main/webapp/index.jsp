<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>

<c:set var="activePage" value="main" />
<c:set var="title" value="My Journals" />
<%-- 상단 헤더와 메뉴를 불러옵니다 --%>
<c:import url="header.jsp" />

<main>
    <c:choose>
        <%-- 1. 세션에 유저가 있을 때 --%>
        <c:when test="${not empty sessionScope.user}">
            <div class="container mt-5 text-center">

                <c:choose>
                    <%-- 관리자 여부 플래그 체크 (하드코딩 없음) --%>
                    <c:when test="${sessionScope.isAdmin}">
                        <h2 class="display-6 fw-bold text-highlight-purple">Admin Dashboard</h2>
                        <p class="text-muted mb-5">Managing all user travel records.</p>
<%--                        <c:import url="displayJournals.jsp" />--%>
                    </c:when>

                    <%-- 일반 유저일 때 --%>
                    <c:otherwise>
                        <h2 class="display-6 fw-bold">Welcome back, ${sessionScope.user.userName}!</h2>
                        <p class="text-muted mb-5">Here are your travel memories.</p>
                        <%--                        <c:import url="displayJournals.jsp" />--%>
                    </c:otherwise>
                </c:choose>

                <c:import url="displayJournals.jsp" />

            </div>
        </c:when>

        <%-- 2. 로그인 전 (메인 랜딩 페이지) --%>
        <c:otherwise>
            <c:import url="main.jsp" />
        </c:otherwise>
    </c:choose>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>