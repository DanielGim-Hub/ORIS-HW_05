<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Travel Planner Catalog</title>
  <link rel="stylesheet" href="/css/catalog.css">
</head>
<body>
<header>
  <h1>Travel Planner Catalog</h1>
</header>
<div class="filters">
  <input type="text" placeholder="Search destinations..." id="searchInput">
  <select id="regionFilter">
    <option value="">All Regions</option>
    <option value="Asia">Asia</option>
    <option value="Europe">Europe</option>
    <option value="America">America</option>
  </select>
  <select id="priceFilter">
    <option value="">Any Price</option>
    <option value="Low">Low</option>
    <option value="Medium">Medium</option>
    <option value="High">High</option>
  </select>
</div>
<div class="catalog">
  <c:forEach var="place" items="${places}">
    <div class="place-card">
      <div class="favorite" title="Add to favorites">♥</div>
      <img src="${place.image}" alt="${place.name}">
      <div class="content">
        <h2>${place.name}</h2>
        <p>${place.description}</p>
        <p><strong>Location:</strong> ${place.location}</p>
        <div class="rating">
          <span>⭐</span><span>⭐</span><span>⭐</span><span>⭐</span><span>☆</span>
        </div>
        <button>View Details</button>
      </div>
    </div>
  </c:forEach>
</div>
<script src="/js/catalog.js"></script>
</body>
</html>
