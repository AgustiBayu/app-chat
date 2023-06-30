package mcrmilenial.appschat.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Pengguna implements Serializable {
    @Id
    private String id;
    private String username;
    private String pasword;
    private String authority;
    private String namaLengkap;
}
