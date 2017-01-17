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
    <fmt:message bundle="${loc}" key="local.button_edit" var="button_edit" />
    <fmt:message bundle="${loc}" key="local.button_delete" var="button_delete" />
    <fmt:message bundle="${loc}" key="local.button_start" var="button_start" />
    <fmt:message bundle="${loc}" key="local.questions" var="questions" />
    <fmt:message bundle="${loc}" key="local.answer_question" var="answer_question" />
    <fmt:message bundle="${loc}" key="local.add_question" var="add_question" />
    <fmt:message bundle="${loc}" key="local.delete_question" var="delete_question" />
    <c:if test="${requestScope.errorMessage!= null && requestScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${requestScope.errorMessage}" var="errorMessage" />
    </c:if>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <main>       
      <div class="container poll-list">
        <div class="row">
          <div class="col-lg-12">
            <h2>${requestScope.poll.theme}</h2>
            <img class="poll-image single" src="images/${requestScope.poll.image}" alt="Question">
            <p class="poll-information">Дата создания опроса-<c:out value="${requestScope.poll.date}"/></p>
            <p class="poll-information">Статус-<c:out value="${requestScope.poll.state}"/></p>
            <c:out value="${errorMessage}"/>
            <c:if test="${sessionScope.active != null && sessionScope.active==true && sessionScope.user.role=='admin'}">
              <a class="mybutton mybutton-green" href="Controller?commandName=GET_IMAGES&pageRedirect=jsp/polledit.jsp&id=${requestScope.poll.id}">${button_edit}</a>
              <a class="mybutton mybutton-green" href="Controller?commandName=DELETE_POLL&id=${requestScope.poll.id}">${button_delete}</a>
              <a class="mybutton mybutton-green" href="jsp/addquestion.jsp?opinionPollId=${requestScope.poll.id}">${add_question}</a>
            </c:if>    
          </div>
        </div>          
      </div>
      <div class="container poll-list question-list">
        <div class="row">
          <h2 class="question-title">${questions}</h2>  
          <c:forEach var="question" items="${requestScope.questions}">
            <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 one-question">
              <img class="poll-image" src="images/question.jpg" alt="Question">
              <h2>${question.questionText}</h2>
              <a class="mybutton mybutton-green" href="Controller?commandName=GET_QUESTION_BY_ID&id=${question.id}&pollId=${requestScope.poll.id}&pollState=${requestScope.poll.state}">${answer_question}</a>
              <c:if test="${sessionScope.active != null && sessionScope.active==true && sessionScope.user.role=='admin'}">
                <a class="mybutton mybutton-green" href="Controller?commandName=DELETE_QUESTION&questionId=${question.id}&opinionPollId=${requestScope.poll.id}">${delete_question}</a>
              </c:if>
            </div>
          </c:forEach>
        </div>
      </div>
    </main>
    <jsp:include page="footer.jsp"/>
