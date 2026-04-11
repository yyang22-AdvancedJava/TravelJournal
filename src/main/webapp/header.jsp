<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Travel Journal</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">
</head>
<body>
<div class="container py-4">
    <header class="custom-purple-border p-4 text-center mb-3 shadow-sm">
        <h1 class="display-5 fw-bold text-dark">Travel Journal</h1>
    </header>

    <nav class="custom-purple-border mb-5 shadow-sm bg-white">
        <ul class="nav justify-content-around py-2 align-items-center">
            <li class="nav-item">
                <a class="nav-link text-dark ${activePage == 'main' ? 'active-link' : ''}" href="displayJournalsByUser">Main</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark ${activePage == 'addJournal' ? 'active-link' : ''}" href="addJournal.jsp">Add a journal</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark ${activePage == 'cities' ? 'active-link' : ''}" href="#">View by cities</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-dark ${activePage == 'weather' ? 'active-link' : ''}" href="#">View by weather</a>
            </li>

            <c:choose>
                <%-- 1. 세션에 유저가 있을 때: Logout 버튼 표시 --%>
                <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <span class="navbar-text me-3 fw-bold text-primary">${sessionScope.user.userName}님</span>
                        <a class="btn btn-outline-danger btn-sm fw-bold" href="logout">Log out</a>
                    </li>
                </c:when>
                <%-- 2. 세션에 유저가 없을 때 (초기 화면 등): Sign up만 유지 --%>
                <c:otherwise>
                    <li class="nav-item">
                        <%--// To go to Auth.java--%>
                        <a class="nav-link text-dark" href="logIn">Sign In/UP</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>