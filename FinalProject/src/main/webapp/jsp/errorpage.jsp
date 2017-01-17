<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.ups" var="ups" />
    <fmt:message bundle="${loc}" key="local.something_wrong" var="something_wrong" />
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
    <div class="error-block">
      <img class="error-image" src="images/error.jpg"/>
      <h2>${ups}</h2>
      <p>${something_wrong}</p>
    </div>
  </body>
</html>
