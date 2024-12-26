<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Admin Panel</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
    }
    th {
      background-color: #f2f2f2;
      text-align: left;
    }
    form {
      margin: 10px 0;
    }
    .logout {
      text-align: right;
      margin: 10px 0;
    }
  </style>
</head>
<body>
<h1>Admin Panel</h1>

<div class="logout">
  <a href="/logout">Logout</a>
</div>

<!-- Search Form -->
<form method="get" action="/admin">
  <input type="text" name="search" placeholder="Search by username or role" value="${param.search}">
  <button type="submit">Search</button>
</form>



<!-- Users Table -->
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Email</th>
    <th>Role</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  <c:if test="${empty users}">
    <tr>
      <td colspan="5" style="text-align: center;">No users found</td>
    </tr>
  </c:if>
  <c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
  </c:if>
  <c:forEach var="user" items="${users}">
    <tr>
      <td>${user.id}</td>
      <td>${user.username}</td>
      <td>${user.email}</td>
      <td>
        <form method="post" action="/admin">
          <input type="hidden" name="userId" value="${user.id}">
          <input type="hidden" name="action" value="updateRole">
          <select name="role">
            <option value="user" ${user.role == 'user' ? 'selected' : ''}>User</option>
            <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
          </select>
          <button type="submit">Update Role</button>
        </form>
      </td>
      <td>
        <form method="post" action="/admin">
          <input type="hidden" name="userId" value="${user.id}">
          <input type="hidden" name="action" value="delete">
          <button type="submit" onclick="return confirm('Are you sure you want to delete this user?')">Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>

  </tbody>
</table>

</body>
</html>
