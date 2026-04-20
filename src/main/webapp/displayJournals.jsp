<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="activePage" value="main" scope="request" />

<c:import url="head.jsp" />

<div class="container mt-5">
    <c:forEach items="${journals}" var="journal">

        <%-- 1. 날씨 클래스 설정 --%>
        <c:set var="w" value="${fn:toLowerCase(journal.weather)}" />
        <c:set var="weatherClass" value="bg-weather-default" />
        <c:choose>
            <c:when test="${fn:contains(w, 'sun') || fn:contains(w, 'clear')}"><c:set var="weatherClass" value="bg-weather-sunny" /></c:when>
            <c:when test="${fn:contains(w, 'cloud') || fn:contains(w, 'overcast')}"><c:set var="weatherClass" value="bg-weather-clouds" /></c:when>
            <c:when test="${fn:contains(w, 'rain') || fn:contains(w, 'drizzle') || fn:contains(w, 'shower')}"><c:set var="weatherClass" value="bg-weather-rain" /></c:when>
            <c:when test="${fn:contains(w, 'mist') || fn:contains(w, 'fog') || fn:contains(w, 'haze')}"><c:set var="weatherClass" value="bg-weather-mist" /></c:when>
            <c:when test="${fn:contains(w, 'thunder') || fn:contains(w, 'storm')}"><c:set var="weatherClass" value="bg-weather-storm" /></c:when>
            <c:when test="${fn:contains(w, 'snow') || fn:contains(w, 'ice')}"><c:set var="weatherClass" value="bg-weather-snow" /></c:when>
        </c:choose>

        <%-- 2. 저널 카드 --%>
        <div class="journal-card ${weatherClass} shadow-sm mb-4">

                <%-- 상단 영역 --%>
            <div class="d-flex align-items-center mb-3">

                    <%-- 제목 --%>
                <h3 class="journal-title mb-0 fw-bold text-dark me-auto">${journal.title}</h3>

                    <%-- 정보 (날짜, 위치, 날씨) --%>
                <div class="d-flex align-items-center text-dark fw-bold me-4 journal-info-box">
                        <%-- 관리자 전용 작성자 표시 --%>
                    <c:if test="${sessionScope.isAdmin}">
                        <span class="mx-2 text-dark-50">|</span>
                        <span class="badge bg-secondary">
                            <i class="bi bi-person-fill me-1"></i> ${journal.user.userName}
                        </span>
                        <span class="mx-2 text-dark-50">|</span>
                    </c:if>

                    <i class="bi bi-calendar-event me-1"></i> ${journal.createdAt.toLocalDate()}
                    <span class="mx-2 text-dark-50">|</span>
                    <i class="bi bi-geo-alt-fill me-1"></i> ${journal.location.name}
                    <span class="mx-2 text-dark-50">|</span>
                    <i class="bi bi-cloud-check-fill me-1"></i> ${journal.weather}
                </div>

                    <%-- 버튼 영역 --%>
                <div class="d-flex align-items-center gap-3">
                    <a href="editJournal?id=${journal.id}" class="text-primary" title="Edit">
                        <i class="bi bi-pencil-square fs-3"></i>
                    </a>
                    <form action="deleteJournal" method="post" style="display:inline; margin:0;">
                        <input type="hidden" name="id" value="${journal.id}">
                        <button type="submit" class="btn btn-link text-danger p-0 btn-delete-link"
                                onclick="return confirm('Are you sure you want to delete this?');">
                            <i class="bi bi-trash3-fill fs-3"></i>
                        </button>
                    </form>
                </div>
            </div>

                <%-- 본문 영역 --%>
            <div class="journal-body">
                <p class="fs-5 text-dark mb-0 journal-content-box">${journal.content}</p>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>