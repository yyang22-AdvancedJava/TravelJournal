<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>
<c:set var="activePage" value="add" scope="request"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <c:import url="head.jsp" />
</head>
<body>
<c:import url="header.jsp" />

<main class="container mt-5 pb-5">
  <div class="row justify-content-center">
    <div class="col-md-10 col-lg-8">
      <h2 class="text-center display-6 fw-bold text-highlight-purple">Add a Journal</h2>

      <div id="weather-api-config" data-url="${weatherURL}" data-key="${weatherKEY}" style="display: none;"></div>

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
            <div id="weatherDisplay" class="mt-2 fw-bold text-primary d-none"></div>
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
          <a href="${pageContext.request.contextPath}/displayJournalsByUser"
             class="btn-cancel shadow-sm me-3">Cancel</a>
        </div>
      </form>
    </div>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/addJournal.js"></script>
</body>
</html>