package mcrmilenial.appschat.services;

import mcrmilenial.appschat.entities.ChatMessage;
import mcrmilenial.appschat.exeptions.RecourceNotFoundExeption;
import mcrmilenial.appschat.models.MessageStatus;
import mcrmilenial.appschat.repositories.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatMessageSerive {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setId(UUID.randomUUID().toString());
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public Long countMassage(String senderId, String recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> messages = chatId.map(cit -> chatMessageRepository.findByChatId(cit))
                .orElse(new ArrayList<>());

        if (!messages.isEmpty()) {
            chatMessageRepository.updateStatus(MessageStatus.DELIVERED, senderId, recipientId);
        }
        return messages;
    }

    public ChatMessage findById(String Id) {
        return chatMessageRepository.findById(Id)
                .map(cm -> {
                    cm.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(cm);
                })
                .orElseThrow(() -> new RecourceNotFoundExeption("Pesan Tidak Di Temukan"));
    }
}
