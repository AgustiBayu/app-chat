package mcrmilenial.appschat.services;

import mcrmilenial.appschat.entities.ChatRoom;
import mcrmilenial.appschat.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist) {

        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map((ChatRoom::getChatId))
                .or(()-> {
                    if(!createIfNotExist) {
                        return Optional.empty();
                    } else {
                        String chatId = String.format("%_s%_s", senderId, recipientId);
                        ChatRoom senderRecipient = new ChatRoom();
                        senderRecipient.setId(UUID.randomUUID().toString());
                        senderRecipient.setChatId(chatId);
                        senderRecipient.setSenderId(senderId);
                        senderRecipient.setRecipientId(recipientId);

                        ChatRoom recipentSender = new ChatRoom();
                        recipentSender.setId(UUID.randomUUID().toString());
                        recipentSender.setChatId(chatId);
                        recipentSender.setSenderId(recipientId);
                        recipentSender.setRecipientId((senderId));

                        chatRoomRepository.save(senderRecipient);
                        chatRoomRepository.save(recipentSender);

                        return Optional.of(chatId);
                    }
                });
    }
}
