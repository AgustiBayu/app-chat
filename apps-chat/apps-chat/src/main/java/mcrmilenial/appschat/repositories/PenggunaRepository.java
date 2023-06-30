package mcrmilenial.appschat.repositories;

import mcrmilenial.appschat.entities.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenggunaRepository extends JpaRepository <Pengguna, String> {
    Optional<Pengguna> findByUsername(String username);
}
