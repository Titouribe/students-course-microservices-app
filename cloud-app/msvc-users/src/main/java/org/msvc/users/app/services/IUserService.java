package org.msvc.users.app.services;

import org.msvc.users.app.model.entities.User;

import java.util.List;

public interface IUserService {

    User findById(Long id);

    List<User> findAll();

    List<User> findAllByName(String name);

    User findByName(String name);

    String deleteById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User user);

}
