<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>

<c:set var="activePage" value="main" scope="request" />
<c:import url="header.jsp" />

<main class="container mt-5 pb-5">
  <div class="card edit-card shadow-sm border-0">
    <div class="card-body p-5">
      <h2 class="text-center fw-bold mb-4 edit-title">Edit Your Memory</h2>

      <form action="editJournal" method="post">
        <input type="hidden" name="id" value="${journal.id}">

        <%-- 관리자일 경우에만 작성자 정보 표시 --%>
        <c:if test="${sessionScope.isAdmin == true}">
          <div class="row mb-4 align-items-center">
            <label class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Author</label>
            <div class="col-sm-9">
              <input type="text" class="form-control custom-input"
                     value="${journal.user.firstName} ${journal.user.lastName} (${journal.user.userName})"
                     readonly>
              <small class="text-muted">* Admin Mode: Viewing Author Information</small>
            </div>
          </div>
        </c:if>

        <%-- Title --%>
        <div class="row mb-4 align-items-center">
          <label for="title" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Title</label>
          <div class="col-sm-9">
            <input type="text" name="title" id="title" class="form-control custom-input"
                   value="${journal.title}" required>
          </div>
        </div>

        <%-- City --%>
        <div class="row mb-4 align-items-center">
          <label for="city" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">City</label>
          <div class="col-sm-9">
            <input type="text" name="city" id="city" class="form-control custom-input"
                   value="${journal.location.name}" required>
          </div>
        </div>

        <%-- Date --%>
        <div class="row mb-4 align-items-center">
          <label for="date" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Date</label>
          <div class="col-sm-9">
            <input type="date" name="date" id="date" class="form-control custom-input"
                   value="${journal.createdAt.toLocalDate()}" required>
          </div>
        </div>

        <%-- Weather --%>
        <div class="row mb-4 align-items-center">
          <label for="weather" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Weather</label>
          <div class="col-sm-9">
            <input type="text" name="weather" id="weather" class="form-control custom-input"
                   value="${journal.weather}" readonly>
          </div>
        </div>

        <%-- Content --%>
        <div class="row mb-4">
          <label for="content" class="col-sm-3 col-form-label fw-bold fs-5 text-dark">Content</label>
          <div class="col-sm-9">
                        <textarea name="content" id="content" class="form-control custom-input"
                                  rows="6" required>${journal.content}</textarea>
          </div>
        </div>

        <%-- Buttons --%>
        <div class="text-center mt-5">
          <button type="submit" class="btn btn-update shadow-sm">
            Update Journal
          </button>
          <a href="displayJournalsByUser" class="btn btn-cancel shadow-sm">
            Cancel
          </a>
        </div>
      </form>
    </div>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>