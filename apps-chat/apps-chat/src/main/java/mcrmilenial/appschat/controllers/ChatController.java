package mcrmilenial.appschat.controllers;

import mcrmilenial.appschat.entities.ChatMessage;
import mcrmilenial.appschat.models.ChatNotification;
import mcrmilenial.appschat.services.ChatMessageSerive;
import mcrmilenial.appschat.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ChatMessageSerive chatMessageSerive;

    @MessageMapping("/chat")
    public void procesMassage(@Payload ChatMessage chatMessage) {
        Optional<String> chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());
        ChatMessage saved = chatMessageSerive.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/massage", new ChatNotification(chatMessage.getChatId(), chatMessage.getSenderId(), chatMessage.getSenderName()));
        
    }
}
