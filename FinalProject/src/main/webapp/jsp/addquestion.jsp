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
    <fmt:message bundle="${loc}" key="local.question.add.title" var="title" />
    <fmt:message bundle="${loc}" key="local.question.add.text" var="text" />
    <fmt:message bundle="${loc}" key="local.qustion.add.submit" var="submit" />
    <fmt:message bundle="${loc}" key="local.qustion.add.option" var="option" />
    <c:if test="${requestScope.errorMessage!= null && requestScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${requestScope.errorMessage}" var="errorMessage" />
    </c:if>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
      <form class="form-signin" id="form-signin" action="${pageContext.servletContext.contextPath}/Controller" method="post">
        <h2 class="form-signin-heading">${title}</h2>
        <input name="commandName" type="hidden" value="ADD_QUESTION"/>
        <input name="opinionPollId" type="hidden" value="${param.opinionPollId}"/>
        <label for="inputQuestionText" class="sr-only">${text}</label>
        <input type="text" name="questionText" id="inputQuestionText" class="form-control" placeholder="${text}" required autofocus> 
        <input type="button" value="${option}" id="create-option-button" class="btn btn-lg btn-primary btn-block" onclick="createOption()"/>
        <button class="btn btn-lg btn-primary btn-block" id="question-submit-button" type="submit">${submit}</button>
      </form>
      <p class="autho-error-message"><c:out value="${errorMessage}"/></p>  
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/mainscript.js"></script>
  </body>
</html>
