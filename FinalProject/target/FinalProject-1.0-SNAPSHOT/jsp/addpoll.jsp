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
    <fmt:message bundle="${loc}" key="local.poll.add.title" var="title" />
    <fmt:message bundle="${loc}" key="local.poll.add.theme" var="theme" />
    <fmt:message bundle="${loc}" key="local.poll.add.image" var="image" />
    <fmt:message bundle="${loc}" key="local.poll.add.submit" var="submit" />
    <fmt:message bundle="${loc}" key="local.images" var="images" />
    <fmt:message bundle="${loc}" key="local.choose_image" var="choose_image" />
    <c:if test="${requestScope.errorMessage!= null && requestScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${requestScope.errorMessage}" var="errorMessage" />
    </c:if>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
      <form class="form-signin" action="${pageContext.servletContext.contextPath}/Controller" method="post">
        <h2 class="form-signin-heading">${title}</h2>
        <input name="commandName" type="hidden" value="ADD_POLL"/>
        <label for="inputTheme" class="sr-only">${theme}</label>
        <input type="text" name="theme" id="inputTheme" class="form-control" placeholder="${theme}" required autofocus>
        <label for="inputImage" class="sr-only">${image}</label>
        <input type="text" name="image" id="inputImage" class="form-control" placeholder="${image}" required>
        <div style="text-align: center;">
          <a href="#win" class="button button-orange">${choose_image}</a>
        </div>
        <a href="#x" class="overlay" id="win"></a>
        <div class="popup">
          <h2>${images}</h2>
          <c:forEach var="image" items="${requestScope.images}">
            <img class="is-image" src="${pageContext.servletContext.contextPath}/images/${image.name}" alt="image" onclick="chooseImage('${image.name}')"/>
          </c:forEach>
          <a class="close" title="Закрыть" href="#close"></a>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">${submit}</button>
      </form>
      <p class="autho-error-message"><c:out value="${errorMessage}"/></p>  
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/mainscript.js"></script>
  </body>
</html>