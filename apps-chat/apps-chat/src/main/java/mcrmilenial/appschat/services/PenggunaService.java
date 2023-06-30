package mcrmilenial.appschat.services;

import mcrmilenial.appschat.entities.Pengguna;
import mcrmilenial.appschat.repositories.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    public Pengguna findByUsername(String username) {
        Optional<Pengguna> optional = penggunaRepository.findByUsername(username);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Pengguna create(Pengguna pengguna) {
        pengguna.setId(UUID.randomUUID().toString());
        return penggunaRepository.save(pengguna);
    }
}
