<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="activePage" value="main" scope="request" />

<%-- 1. 현재 페이지가 'main'임을 선언 --%>
<c:set var="activePage" value="main" scope="request" />

<%-- 헤더 포함 --%>
<c:import url="head.jsp" />

<div class="container mt-5">
    <c:forEach items="${journals}" var="journal">

        <%-- 1. 날씨 텍스트 기반 색상 클래스 설정 --%>
        <c:set var="w" value="${fn:toLowerCase(journal.weather)}" />
        <c:set var="weatherClass" value="bg-weather-default" />
        <c:choose>
            <c:when test="${fn:contains(w, 'sun') || fn:contains(w, 'clear')}">
                <c:set var="weatherClass" value="bg-weather-sunny" />
            </c:when>
            <c:when test="${fn:contains(w, 'cloud') || fn:contains(w, 'overcast')}">
                <c:set var="weatherClass" value="bg-weather-clouds" />
            </c:when>
            <c:when test="${fn:contains(w, 'rain') || fn:contains(w, 'drizzle') || fn:contains(w, 'shower')}">
                <c:set var="weatherClass" value="bg-weather-rain" />
            </c:when>
            <c:when test="${fn:contains(w, 'mist') || fn:contains(w, 'fog') || fn:contains(w, 'haze')}">
                <c:set var="weatherClass" value="bg-weather-mist" />
            </c:when>
            <c:when test="${fn:contains(w, 'thunder') || fn:contains(w, 'storm')}">
                <c:set var="weatherClass" value="bg-weather-storm" />
            </c:when>
            <c:when test="${fn:contains(w, 'snow') || fn:contains(w, 'ice')}">
                <c:set var="weatherClass" value="bg-weather-snow" />
            </c:when>
        </c:choose>

        <%-- 2. 저널 카드 --%>
        <div class="journal-card ${weatherClass} shadow-sm mb-4" style="border-radius: 20px; padding: 1.5rem 2rem;">

                <%-- 상단 영역: 제목, 위치/날씨, 버튼들 --%>
            <div class="d-flex align-items-center mb-3">

                    <%-- [왼쪽] 제목 --%>
                <h3 class="journal-title mb-0 fw-bold text-dark me-auto">${journal.title}</h3>

                    <%-- [오른쪽] 위치와 날씨 정보 --%>
                <div class="d-flex align-items-center text-dark fw-bold me-4" style="font-size: 1rem; opacity: 0.8;">
                        <%-- 도시 이름 출력 --%>
                    <i class="bi bi-geo-alt-fill me-1"></i> ${journal.location.name}
                    <span class="mx-2 text-dark-50">|</span>
                        <%-- 날씨 출력 --%>
                    <i class="bi bi-cloud-check-fill me-1"></i> ${journal.weather}
                </div>

                    <%-- [버튼 영역] 수정 및 삭제 --%>
                <div class="d-flex align-items-center gap-3">
                        <%-- 수정 버튼 (연필 아이콘) --%>
                    <a href="editJournal?id=${journal.id}" class="text-primary" title="Edit">
                        <i class="bi bi-pencil-square fs-3"></i>
                    </a>

                        <%-- 삭제 버튼 (쓰레기통 아이콘) --%>
                    <form action="deleteJournal" method="post" style="display:inline; margin:0;">
                        <input type="hidden" name="id" value="${journal.id}">
                        <button type="submit" class="btn btn-link text-danger p-0"
                                style="border:none; text-decoration: none;"
                                onclick="return confirm('정말 삭제하시겠습니까?');">
                            <i class="bi bi-trash3-fill fs-3"></i>
                        </button>
                    </form>
                </div>
            </div>

                <%-- 본문 영역 --%>
            <div class="journal-body">
                <p class="fs-5 text-dark mb-0" style="line-height: 1.6;">${journal.content}</p>
            </div>

        </div>
    </c:forEach>
</div>

</body>
</html>