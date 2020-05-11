package testweb3.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;

import testweb3.beans.Message;

@Controller
public class ChatController {

	@MessageMapping("/message")
	@SendTo("/chat_1/messages")
	public Message getMessages(Message message, SimpMessageHeaderAccessor headerAccessor) {
		
		return message;
	}
	
	@MessageMapping("/direct/{sender}/to/{reciever}")
	@SendTo("/queue/{reciever}")
	public Message direct(@Payload Message message, @DestinationVariable String sender, @DestinationVariable String reciever) {
		
		return message;
	}
	
	@MessageMapping("/greetings")
	@SendToUser("/queue/greetings")
	public Message reply(@Payload Message message, Principal user, @DestinationVariable String dest) {
		
		return message;
	}

}