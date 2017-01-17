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
    <fmt:message bundle="${loc}" key="local.li1" var="li1"/>
    <fmt:message bundle="${loc}" key="local.li2" var="li2" />
    <fmt:message bundle="${loc}" key="local.li3" var="li3" />
    <fmt:message bundle="${loc}" key="local.li4" var="li4" />
    <fmt:message bundle="${loc}" key="local.li5" var="li5" />
    <fmt:message bundle="${loc}" key="local.li6" var="li6" />
    <fmt:message bundle="${loc}" key="local.authorization.form.placeholder1" var="placeholder1"/>
    <fmt:message bundle="${loc}" key="local.authorization.form.placeholder2" var="placeholder2" />
    <fmt:message bundle="${loc}" key="local.authorization.form.button_in" var="button_in" />
    <fmt:message bundle="${loc}" key="local.authorization.form.button_out" var="button_out" />
    <fmt:message bundle="${loc}" key="local.localization.button_en" var="button_en" />
    <fmt:message bundle="${loc}" key="local.localization.button_ru" var="button_ru" />
    <fmt:message bundle="${loc}" key="local.button_open" var="button_open" />
    <fmt:message bundle="${loc}" key="local.hello_message" var="hello_message" />
    <c:if test="${sessionScope.errorMessage!= null && sessionScope.errorMessage != ''}">
      <fmt:message bundle="${loc}" key="${sessionScope.errorMessage}" var="sessionErrorMessage" />
    </c:if>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
    <header>
      <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
              <span class="sr-only">Открыть навигацию</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">OpinionPolls</a>
          </div>
          <div id="responsive-menu" class="navbar-collapse collapse user-hello">
            <ul class="nav navbar-nav">
              <c:if test="${sessionScope.active == null || sessionScope.active==false}">
                <li><a href="Controller?commandName=GET_POLLS&pageRedirect=jsp/polls.jsp">${li1}</a></li>
                <li><a href="jsp/registration.jsp">${li2}</a></li>
                <li><a href="Controller?commandName=GET_POLLS&pageRedirect=jsp/about.jsp">${li4}</a></li>   
              </c:if>
              <c:if test="${sessionScope.active != null && sessionScope.active==true && sessionScope.user.role=='user'}">
                <li><a href="Controller?commandName=GET_POLLS&pageRedirect=jsp/polls.jsp">${li1}</a></li>
                <li><a href="Controller?commandName=GET_POLLS&pageRedirect=jsp/about.jsp">${li4}</a></li>   
              </c:if>
              <c:if test="${sessionScope.active != null && sessionScope.active==true && sessionScope.user.role=='admin'}">
                <li><a href="Controller?commandName=GET_POLLS&pageRedirect=jsp/polls.jsp">${li1}</a></li>
                <li><a href="Controller?commandName=GET_IMAGES&pageRedirect=jsp/addpoll.jsp">${li3}</a></li>
                <li><a href="Controller?commandName=GET_POLLS&pageRedirect=jsp/about.jsp">${li4}</a></li>  
                <li><a href="Controller?commandName=GET_USERS">${li5}</a></li>
                <li><a href="jsp/uploadfile.jsp">${li6}</a></li>  
              </c:if>
            </ul>
            <form class="navbar-right local-form " action="Controller" method="post">
              <input name="commandName" type="hidden" value="CHANGE_LOCALE" />
              <input name="local" type="hidden" value="ru" />
              <button type="submit" class="btn btn-success">${button_ru}</button>
            </form>  
            <form class=" navbar-right local-form" action="Controller" method="post">
              <input name="commandName" type="hidden" value="CHANGE_LOCALE" />
              <input name="local" type="hidden" value="en" />
              <button type="submit" class="btn btn-success">${button_en}</button>
            </form> 
            <c:if test="${sessionScope.active != null && sessionScope.active==true}">
              <p class="nav navbar-nav">
                ${hello_message}<c:out value="${sessionScope.user.name}"/>
              </p>
              <form class="navbar-form navbar-right" action="Controller" method="post">
                <input type="hidden" name="commandName" value="SIGN_OUT"/>
                <button type="submit" class="btn btn-success">${button_out}</button>
              </form>    
            </c:if>
            <c:if test="${sessionScope.active == null || sessionScope.active==false}">
              <p class="nav navbar-nav">
                <c:out value="${sessionErrorMessage}"/>
              </p>
              <form class="navbar-form navbar-right" action="Controller" method="post">
                <input name="commandName" type="hidden" value="SIGN_IN" />
                <div class="form-group">
                  <input type="text" name="login" placeholder="${placeholder1}" class="form-control">
                </div>
                <div class="form-group">
                  <input type="password" name="password" placeholder="${placeholder2}" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">${button_in}</button>
              </form>  
            </c:if>
          </div>
        </div>
      </nav>
      <div id="myCarousel" class="carousel slide slider-opt" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
          <li data-target="#myCarousel" data-slide-to="3"></li>
          <li data-target="#myCarousel" data-slide-to="4"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <c:forEach var="poll" items="${requestScope.polls}" varStatus="loop">
            <c:if test="${loop.index<5}"> 
              <c:if test="${loop.index>0}">  
                <div class="item">
              </c:if>
              <c:if test="${loop.index==0}">  
                <div class="item active">
              </c:if>
              <img class="slide-image" src="images/${poll.image}" alt="slide-image">
              <div class="container">
                <div class="carousel-caption">
                  <h2><c:out value="${poll.theme}"/></h2>
                  <p><a class="mybutton mybutton-green" href="Controller?commandName=GET_POLL_BY_ID&id=${poll.id}">${button_open}</a></p>
                </div>
              </div>
              </div>
            </c:if>
          </c:forEach>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </header>
