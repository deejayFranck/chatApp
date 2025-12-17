package com.project.chatapp.config;

import com.project.chatapp.entity.Message;
import com.project.chatapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final UserService userService;
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        var headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        var username = Objects.requireNonNull(headerAccesor.getSessionAttributes()).get("username").toString();

        if(Objects.nonNull(username)){
            var user = userService.findByUsername(username);
            user.setOnline(false);

            var leaveMessage = new Message();
            leaveMessage.setType(Message.MessageType.LEAVE);
            leaveMessage.setSender(user);

            messagingTemplate.convertAndSend("/topic/public", leaveMessage);
        }
    }

}
