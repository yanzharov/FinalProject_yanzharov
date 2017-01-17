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
    <fmt:message bundle="${loc}" key="local.registration.form.title" var="title" />
    <fmt:message bundle="${loc}" key="local.registration.form.name" var="name" />
    <fmt:message bundle="${loc}" key="local.registration.form.login" var="login" />
    <fmt:message bundle="${loc}" key="local.registration.form.password" var="password" />
    <fmt:message bundle="${loc}" key="local.registration.form.submit" var="submit" />
    <c:if test="${sessionScope.errorMessage!= null && sessionScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${sessionScope.errorMessage}" var="sessionErrorMessage" />
    </c:if>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
      <form class="form-signin" action="${pageContext.servletContext.contextPath}/Controller" method="post">
        <h2 class="form-signin-heading">${title}</h2>
        <input name="commandName" type="hidden" value="ADD_NEW_USER"/>
        <label for="inputName" class="sr-only">${name}</label>
        <input type="text" name="name" id="inputName" class="form-control" placeholder="${name}" required autofocus>
        <label for="inputLogin" class="sr-only">${login}</label>
        <input type="text" name="login" id="inputLogin" class="form-control" placeholder="${login}" required autofocus>
        <label for="inputPassword" class="sr-only">${password}</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="${password}" required>
        <input name="role" type="hidden" value="user"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">${submit}</button>
      </form>
      <p class="autho-error-message"><c:out value="${sessionErrorMessage}"/></p>  
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
  </body>
</html>
