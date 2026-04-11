<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Travel Journal</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%-- 부트스트랩 아이콘 (선택 사항: 날씨나 유저 아이콘 사용 시 필요) --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
</head>
<body>
<div class="container py-4">
    <header class="custom-purple-border p-4 text-center mb-3 shadow-sm">
        <h1 class="display-5 fw-bold text-dark">Travel Journal</h1>
    </header>

    <nav class="custom-purple-border mb-5 shadow-sm bg-white">
        <ul class="nav justify-content-around py-2 align-items-center">

            <c:choose>
                <%-- 1. 세션에 유저가 있을 때: 모든 메뉴와 Logout 버튼 표시 --%>
                <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <a class="nav-link text-dark ${activePage == 'main' ? 'active-link' : ''}" href="displayJournalsByUser">Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-dark ${activePage == 'add' ? 'active-link' : ''}" href="addJournal">Add a journal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-dark ${activePage == 'cities' ? 'active-link' : ''}" href="#">View by cities</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-dark ${activePage == 'weather' ? 'active-link' : ''}" href="#">View by weather</a>
                    </li>
                    <li class="nav-item d-flex align-items-center">
                        <span class="navbar-text me-3 fw-bold text-primary">
                            <i class="bi bi-person-fill"></i> ${sessionScope.user.userName}
                        </span>
                        <a class="btn btn-outline-danger btn-sm fw-bold" href="logout">Log out</a>
                    </li>
                </c:when>

                <%-- 2. 세션에 유저가 없을 때: Sign In/UP 메뉴만 표시 --%>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link text-dark fw-bold" href="logIn">
                            <i class="bi bi-box-arrow-in-right"></i> Sign In/UP
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>