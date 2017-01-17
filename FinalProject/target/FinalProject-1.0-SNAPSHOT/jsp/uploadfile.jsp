<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OpinionPolls</title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/css/style.css" rel="stylesheet">
  </head>
  <body>
    <form class="form-signin" action="${pageContext.servletContext.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
      <input name="name" type="file"><br>
      <input class="btn btn-lg btn-primary btn-block" type="submit"><br>
    </form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>  
  </body>
</html>
