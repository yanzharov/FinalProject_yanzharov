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
    <fmt:message bundle="${loc}" key="local.button_open" var="button_open" />
    <fmt:message bundle="${loc}" key="local.polls" var="polls" />
    <c:if test="${requestScope.errorMessage!= null && requestScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${requestScope.errorMessage}" var="errorMessage" />
    </c:if>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <main>
      <p class="autho-error-message"><c:out value="${errorMessage}"/></p>
      <div class="container poll-list">
        <div class="row">
          <h2 class="popular-title">${polls}</h2>
          <c:forEach var="poll" items="${requestScope.polls}">
            <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
              <img class="poll-image" src="images/${poll.image}" alt="Question">
              <h2>${poll.theme}</h2>
              <a class="mybutton mybutton-green" href="Controller?commandName=GET_POLL_BY_ID&id=${poll.id}">${button_open}</a>
            </div>
          </c:forEach>
        </div>
      </div>
    </main>
    <jsp:include page="footer.jsp"/>
