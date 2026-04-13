<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>

<%-- 네비게이션 바 및 제목 설정 --%>
<c:set var="activePage" value="add" scope="request"/>

<c:import url="header.jsp" />

<main class="container mt-5 pb-5">
  <div class="row justify-content-center">
    <div class="col-md-10 col-lg-8">

      <h2 class="text-center mb-5 fw-bold display-6">Add a Journal</h2>

      <%--
           [중요] API 설정을 담은 데이터 존
           서블릿(doGet)에서 보낸 값을 HTML 속성에 숨겨둡니다.
      --%>
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
            <%-- onblur 시 findWeather() 함수를 파라미터 없이 깔끔하게 호출 --%>
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
          <button type="submit" class="btn-save shadow">save</button>
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