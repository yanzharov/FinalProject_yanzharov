<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>OpinionPolls</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.users" var="users" />
    <fmt:message bundle="${loc}" key="local.set_admin" var="set_admin" />
    <fmt:message bundle="${loc}" key="local.delete_admin" var="delete_admin" />
    <c:if test="${requestScope.errorMessage!= null && requestScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${requestScope.errorMessage}" var="errorMessage" />
    </c:if>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
      <div class="row result-bar">
        <h2 class="form-signin-heading">${users}</h2>
        <p class="autho-error-message"><c:out value="${errorMessage}"/></p>
        <c:forEach var="user" items="${requestScope.users}">
          <div class="single-user">
            <p class="user-login">${user.login}</p>
            <c:if test="${user.role eq 'admin'}">
              <a class="admin-button" href="Controller?commandName=DELETE_ADMIN_RIGHTS&id=${user.id}&role=${user.role}">${delete_admin}</a>  
            </c:if>
            <c:if test="${user.role eq 'user'}">
                <a class="admin-button" href="Controller?commandName=SET_ADMIN&id=${user.id}&role=${user.role}">${set_admin}</a>
            </c:if>
          </div>
        </c:forEach>
      </div>
    </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
  </body>
</html>
