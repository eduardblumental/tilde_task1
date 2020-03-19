package lv.tilde.eduards.task1.DAOs;

import lv.tilde.eduards.task1.objects.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {
    Optional<User> findByUsername (String username);
    boolean existsByUsername(String username);
}
