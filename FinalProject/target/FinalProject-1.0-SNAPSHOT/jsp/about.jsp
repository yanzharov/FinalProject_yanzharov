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
    <fmt:message bundle="${loc}" key="local.site_information" var="site_information" />
    <fmt:message bundle="${loc}" key="local.about.share_title" var="share_title" />
    <fmt:message bundle="${loc}" key="local.about.share_text" var="share_text" />
    <fmt:message bundle="${loc}" key="local.about.found_title" var="found_title" />
    <fmt:message bundle="${loc}" key="local.about.found_text" var="found_text" />
    <fmt:message bundle="${loc}" key="local.about.create_title" var="create_title" />
    <fmt:message bundle="${loc}" key="local.about.create_text" var="create_text" />
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
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
