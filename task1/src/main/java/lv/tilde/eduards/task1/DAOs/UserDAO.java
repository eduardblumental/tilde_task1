package lv.tilde.eduards.task1.DAOs;

import lv.tilde.eduards.task1.objects.SystemUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername (String username);
    boolean existsByUsername(String username);
}
