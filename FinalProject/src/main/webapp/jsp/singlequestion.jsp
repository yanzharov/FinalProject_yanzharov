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
    <fmt:message bundle="${loc}" key="local.question.request_user" var="request_user" />
    <fmt:message bundle="${loc}" key="local.question.button_answer" var="button_answer" />
    <c:if test="${requestScope.errorMessage!= null && requestScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${requestScope.errorMessage}" var="errorMessage" />
    </c:if>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
    <c:if test="${sessionScope.active == null || sessionScope.active==false}">
      <h2>${request_user}</h2>
    </c:if>
    <c:if test="${sessionScope.active != null && sessionScope.active==true}">
      <c:if test="${requestScope.answered == false && requestScope.pollState eq 'active'}"> 
        <div class="container">
          <form class="col-lg-12 single-question" action="Controller" method="post">
            <input name="commandName" type="hidden" value="ADD_NEW_ANSWER"/>
            <input name="pollId" type="hidden" value="${param.pollId}"/>
            <input name="questionId" type="hidden" value="${requestScope.question.id}"/>
            <h2 class="form-signin-heading">${requestScope.question.questionText}</h2>
            <c:forEach var="option" items="${requestScope.options}">
              <c:if test="${option.text!='text' && option.text!='textarea'}">
                <p><input name="answerText" type="radio" value="${option.text}">${option.text}</p>
              </c:if>
              <c:if test="${option.text=='text'}">
                <input name="answerText" type="text"/>
              </c:if>
              <c:if test="${option.text=='textarea'}">
                <p><textarea rows="5" cols="45" name="answerText"></textarea></p>
              </c:if>
            </c:forEach>
            <button class="btn btn-primary " type="submit">${button_answer}</button>
          </form>
          <p class="autho-error-message"><c:out value="${errorMessage}"/></p>  
        </div>
      </c:if>
      <c:if test="${requestScope.answered==true || requestScope.pollState eq 'closed'}"> 
        <div class="container">
          <div class="row result-bar">
            <h2 class="form-signin-heading">${requestScope.question.questionText}</h2>
            <c:forEach var="answerResult" items="${requestScope.answerResults}">
              <p>${answerResult.answerText}</p>
              <div class="progress procent-center">
                <div class="progress-bar progress-bar-primary" 
                style="min-width:2%;width:${answerResult.count}%;">
                </div>
                <p>${answerResult.count}%</p>
              </div>
            </c:forEach>
          </div>
        </div>
      </c:if> 
    </c:if>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
  </body>
</html>
