<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>
<html>
<head>
  <title>Add a Journal</title>
  <%-- 페이지 구조의 핵심: 모든 페이지에서 동일한 CSS를 참조하도록 설정 --%>
  <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%-- 네비게이션 바 및 제목 설정 --%>
<c:set var="activePage" value="add" scope="request"/>

<%-- 헤더 호출 (container 클래스로 감싸서 index.jsp와 정렬 일치) --%>
<div class="container mt-4">
  <c:import url="header.jsp" />
</div>

<main class="container mt-5 pb-5">
  <div class="row justify-content-center">
    <div class="col-md-10 col-lg-8">

      <h2 class="text-center display-6 fw-bold text-highlight-purple">Add a Journal</h2>

      <%-- API 설정 데이터 --%>
      <div id="weather-api-config"
           data-url="${weatherURL}"
           data-key="${weatherKEY}"
           style="display: none;"></div>

      <form action="addJournal" method="post" class="add-journal-form">

        <div class="row mb-4 align-items-center">
          <label for="title" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Title</label>
          <div class="col-sm-9">
            <input type="text" name="title" id="title" class="form-control custom-input" required>
          </div>
        </div>

        <div class="row mb-4 align-items-center">
          <label for="city" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">City</label>
          <div class="col-sm-9">
            <input type="text" name="city" id="city" class="form-control custom-input" onblur="findWeather()" required>

            <div id="weatherDisplay" class="mt-2 fw-bold text-primary d-none">
            </div>
          </div>
        </div>

        <div class="row mb-4 align-items-center">
          <label for="date" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Date</label>
          <div class="col-sm-9">
            <input type="date" name="date" id="date" class="form-control custom-input" required>
          </div>
        </div>

        <div class="row mb-4">
          <label for="content" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Story</label>
          <div class="col-sm-9">
            <textarea name="content" id="content" rows="8" class="form-control custom-input" required></textarea>
          </div>
        </div>

        <input type="hidden" name="weather" id="weatherValue">

        <div class="text-end mt-5 mb-5">
          <button type="submit" class="btn-save shadow">Save</button>
        </div>

      </form>
    </div>
  </div>
</main>

<%-- 스크립트 로드 --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%-- 분리한 자바스크립트 파일 --%>
<script src="${pageContext.request.contextPath}/js/addJournal.js"></script>

</body>
</html>