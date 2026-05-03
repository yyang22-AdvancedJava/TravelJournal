<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <c:import url="head.jsp" />
</head>
<body>

<c:set var="activePage" value="weather" scope="request" />
<c:import url="header.jsp" />

<main class="container mt-5 pb-5">
    <div class="row justify-content-center">
        <div class="col-md-10 col-lg-8">

            <h2 class="text-center fw-bold mb-5">View by Weather</h2>

            <%-- 검색 폼 --%>
            <form action="searchByWeather" method="get" class="row g-3 justify-content-center mb-2 align-items-center">
                <div class="col-auto">
                    <input type="text" name="weather" id="weather" class="form-control custom-input" placeholder="Enter weather (e.g. Sunny)" required>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-save shadow-sm">Search</button>
                </div>
            </form>

            <%-- 가이드 텍스트 추가 --%>
            <div class="text-center mb-2">
                <small class="text-muted">Keywords: Sunny / Cloudy / Rain / Snow </small>
            </div>

            <%-- 검색 결과 리스트 --%>
            <c:if test="${not empty journals}">
                <c:forEach var="journal" items="${journals}">

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

                    <div class="journal-card ${weatherClass} shadow-sm mb-4">
                        <div class="d-flex align-items-center mb-3">
                            <h3 class="journal-title mb-0 fw-bold text-dark me-auto">${journal.title}</h3>
                            <div class="d-flex align-items-center text-dark fw-bold me-4 journal-info-box">
                                    <%-- 관리자일 경우 작성자 이름 추가 표시 --%>
                                <c:if test="${sessionScope.isAdmin}">
                                    <span class="mx-2 text-dark-50">|</span>
                                    <i class="bi bi-person-fill me-1"></i> ${journal.user.userName}
                                    <span class="mx-2 text-dark-50">|</span>
                                </c:if>

                                <i class="bi bi-calendar-event me-1"></i> ${journal.createdAt.toLocalDate()}
                                <span class="mx-2 text-dark-50">|</span>
                                <i class="bi bi-geo-alt-fill me-1"></i> ${journal.location.name}
                                <span class="mx-2 text-dark-50">|</span>
                                <i class="bi bi-cloud-check-fill me-1"></i> ${journal.weather}
                            </div>
                            <div class="d-flex align-items-center gap-3">
                                <a href="editJournal?id=${journal.id}" class="text-primary">
                                    <i class="bi bi-pencil-square fs-3"></i>
                                </a>
                                <form action="deleteJournal" method="post" style="display:inline; margin:0;">
                                    <input type="hidden" name="id" value="${journal.id}">
                                    <button type="submit" class="btn btn-link text-danger p-0"><i class="bi bi-trash3-fill fs-3"></i></button>
                                </form>
                            </div>
                        </div>
                        <div class="journal-body">
                            <p class="fs-5 text-dark mb-0 journal-content-box">${journal.content}</p>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${empty journals && param.weather != null}">
                <p class="text-center text-muted mt-5">No journals found for "${param.weather}".</p>
            </c:if>

        </div>
    </div>
</main>
</body>
</html>