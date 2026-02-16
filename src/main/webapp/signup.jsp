<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<body>
<%--
<c:if test="${not empty message}">
  <div class="alert alert-info">
      ${message}
  </div>
</c:if>
--%>
<div class="container">
  <h2 class="text-center">Sign Up</h2>
  <form action="addUser" method="POST">
    <div class="mb-3 row">
      <label for="firstName" class="form-label fs-4">First Name</label>
      <div class="col-10">
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Type First name">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="lastName" class="form-label fs-4">Last Name</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Type Last name">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="userName" class="form-label fs-4">User Name</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="userName" name="userName" placeholder="Type User name">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="password" class="form-label fs-4">Password</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="password" name="password" placeholder="Type Password">
      </div>
    </div>
    <div class="mb-3 row" class="form-label fs-4">
      <label for="reEnterPassword">Re-enter Password</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="reEnterPassword" name="reEnterPassword" placeholder="Re-enter Password">
      </div>
    </div>

    <button type="submit" name="submit" class="btn btn-primary">Add</button>
  </form>
</div>
</body>
</html>