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
    <fmt:message bundle="${loc}" key="local.popular" var="popular" />
    <fmt:message bundle="${loc}" key="local.site_information" var="site_information" />
    <fmt:message bundle="${loc}" key="local.about.share_title" var="share_title" />
    <fmt:message bundle="${loc}" key="local.about.share_text" var="share_text" />
    <fmt:message bundle="${loc}" key="local.about.found_title" var="found_title" />
    <fmt:message bundle="${loc}" key="local.about.found_text" var="found_text" />
    <fmt:message bundle="${loc}" key="local.about.create_title" var="create_title" />
    <fmt:message bundle="${loc}" key="local.about.create_text" var="create_text" />
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
          <h2 class="popular-title">${popular}</h2>
          <c:forEach var="poll" items="${requestScope.popularPolls}">
            <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
              <img class="poll-image" src="images/${poll.image}" alt="Question">
              <h2>${poll.theme}</h2>
              <a class="mybutton mybutton-green" href="Controller?commandName=GET_POLL_BY_ID&id=${poll.id}">${button_open}</a>
            </div>
          </c:forEach>       
        </div>
      </div>
    </main>
    <div class="container">
      <h2 class="popular-title">${site_information}</h2>  
      <hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">${share_title}</h2>
          <p class="lead">${share_text}</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" src="images/england.jpg" alt="Generic placeholder image">
        </div>
      </div>
      <hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">${found_title}</h2>
          <p class="lead">${found_text}</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" src="images/england.jpg"  alt="Generic placeholder image">
        </div>
      </div>
      <hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">${create_title}</h2>
          <p class="lead">${create_text}</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" src="images/england.jpg"  alt="Generic placeholder image">
        </div>
      </div>
      <hr class="featurette-divider">
    </div>
    <jsp:include page="footer.jsp"/>
   
