<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>

<c:set var="activePage" value="main" />
<c:set var="title" value="My Journals" />
<%-- 상단 헤더와 메뉴를 불러옵니다 --%>
<c:import url="header.jsp" />

<main class="container mt-4">
    <div class="row">
        <%-- 화면 중앙 왼쪽에 적절히 배치 (부트스트랩 그리드) --%>
        <div class="col-md-10 col-lg-9">

            <%-- 실제 저널 리스트가 출력되는 파일을 불러옵니다 --%>
            <c:import url="displayJournals.jsp" />

        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>