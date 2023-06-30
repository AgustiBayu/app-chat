package mcrmilenial.appschat.entities;

import lombok.Data;
import mcrmilenial.appschat.models.MessageStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class ChatMessage implements Serializable {

    @Id
    private String id;
    private String chatId;
    private String recipientId;
    private String recipientName;
    private String senderId;
    private String senderName;
    private String content;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
