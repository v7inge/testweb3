<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">
    <title>Simple chat</title>
	<link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/style.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="/webjars/sockjs-client/sockjs.min.js"></script>
	<script src="/webjars/stomp-websocket/stomp.min.js"></script>
	<script src="/js/script.js"></script>	
	
	<link type="text/css" rel="stylesheet" data-th-href="@{css/style.css}" />
	
</head>

<body>

  <div class="login_window">

    <div class="top_menu">
		<div class="title">Chat by Victor Umanskiy</div>
	</div>

    <h2 class="h2-login active">Log in</h2>
    <h2 class="h2-login inactive">Sign up</h2>
    
    <p style="color: red;">${errorString}</p>

    <form method="POST" action="${pageContext.request.contextPath}/login">
      <input type="text" id="login" name="userName" placeholder="login" value="${user.userName}">
      <input type="text" id="password" name="password" placeholder="password" value="${user.password}">
      <input type="submit" id="login_button" value="Log In">
      <div class="checkbox">
      <input class="custom-checkbox" type="checkbox" id="rememberMe" name="rememberMe" value="Y" checked>
      <label for="rememberMe">Remember me</label>
      </div>
    </form>

    <div id="formFooter">
      <div class="dark-gray">If you forgot you password please contact the developer</div>
    </div>

  </div>

</body>