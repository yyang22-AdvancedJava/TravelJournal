<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <header class="custom-purple-border p-4 text-center mb-3 shadow-sm">
        <h1 class="display-5 fw-bold text-dark">Travel Journal</h1>
    </header>
</div>

<nav class="custom-purple-border mb-5 shadow-sm bg-white">
    <ul class="nav justify-content-around py-2 align-items-center">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${sessionScope.isAdmin}">
                            <a class="nav-link text-dark ${activePage == 'main' ? 'active-link' : ''}" href="displayAllJournals">Main</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link text-dark ${activePage == 'main' ? 'active-link' : ''}" href="displayJournalsByUser">Main</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark ${activePage == 'add' ? 'active-link' : ''}" href="addJournal">Add a journal</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark ${activePage == 'cities' ? 'active-link' : ''}" href="searchByCity">View by city</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark ${activePage == 'weather' ? 'active-link' : ''}" href="searchByWeather">View by weather</a>
                </li>
                <li class="nav-item d-flex align-items-center">
                    <span class="navbar-text me-3 fw-bold text-primary">
                        <i class="bi bi-person-fill"></i> ${sessionScope.user.userName}
                    </span>
                    <a class="btn btn-outline-danger btn-sm fw-bold" href="logout">Log out</a>
                </li>
            </c:when>
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