<%@ page import="java.util.*, java.text.*, java.util.List, testweb3.beans.*"  %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html>
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
<body onload="onLoad()">
	
	<div class="invisible" id="userName">${user.userName}</div>
	<div class="invisible" id="contactName">no contact</div>
	
	<div class="chat_window">
	
		<div class="top_menu">
			<div class="title">Chat by Victor Umanskiy</div>
		</div>
		
		<div class = "sidebar">
		<ul class = "contacts" id = "contacts">
		<%
		ArrayList<UserAccount> userList = (ArrayList<UserAccount>) request.getAttribute("userList");
		for (UserAccount u : userList) {
			out.println("<li class = \"contact\">" + u.getUserName() + "</li>");	
		}
		%>
		</ul>   
		</div>
		
		<div class="chat_inner_window">
		
		<table class = "tdcenter">
			<tr>
				<td>
					<p>Select a contact to start messaging...</p>
				</td>
			</tr>
		</table>
		
		<ul class = "messages" id = "messages">
		<%
	    /*
	    UserAccount currentUserAccount = (UserAccount) request.getAttribute("user");
	    String currentUser = currentUserAccount.getUserName();
	    ArrayList<Message> history = (ArrayList<Message>) request.getAttribute("history");
	    String messageStyle = "";
	    String dateStyle = "";
	    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    for (Message m : history) {
	    	
	    	String senderName = m.getSender();
	    	if (senderName.equals(currentUser)) {
	    		messageStyle = "<div class = \"m m-right\">";
	    		dateStyle = "<div class = \"date right\">";
	    	} else {
	    		messageStyle = "<div class = \"m m-left\">";
	    		dateStyle = "<div class = \"date left\">";
	    	}
	    	
	    	out.println("<li>");
	        out.println(messageStyle + m.getText() + "</div>");
	        out.println(dateStyle + formatter.format(m.getDate()) + "</div>");
	        out.println("</li>");
	        
	    }
	    */
    	%>
		</ul>
		</div>
	
		<div class="bottom_wrapper clearfix">
				<div class="message_input_wrapper">
					<input id="message_input_value" class="message_input" placeholder="Type your message here..." />
				</div>
				
				<div class="send_message" id="send_button">
					<div class="icon"></div>
					<div class="text">Send</div>
				</div>
	  			
			</div>
	
		</div>
	
</body>
</html>