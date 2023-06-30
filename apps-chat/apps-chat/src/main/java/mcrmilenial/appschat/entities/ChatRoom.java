package mcrmilenial.appschat.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class ChatRoom implements Serializable {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
