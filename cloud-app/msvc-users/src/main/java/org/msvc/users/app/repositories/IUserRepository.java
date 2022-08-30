package org.msvc.users.app.repositories;

import org.msvc.users.app.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String name);

    Optional<User> findByFirstNameLike(String name);
}
