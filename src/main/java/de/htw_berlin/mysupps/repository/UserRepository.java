package de.htw_berlin.mysupps.repository;

import de.htw_berlin.mysupps.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
