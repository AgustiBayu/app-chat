package mcrmilenial.appschat.controllers;

import mcrmilenial.appschat.services.ChatMessageSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatMessageController {

    @Autowired
    private ChatMessageSerive chatMessageSerive;

    @GetMapping("/message/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ) {
        return ResponseEntity.ok(chatMessageSerive.countMassage(senderId,recipientId));
    }

    @GetMapping("/message/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ) {
        return ResponseEntity.ok(chatMessageSerive.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<?> findMessage(
            @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(chatMessageSerive.findById(id));
    }
}
