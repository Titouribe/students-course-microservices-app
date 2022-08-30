package org.msvc.users.app.services.impl;

import org.msvc.users.app.constants.Constants;
import org.msvc.users.app.constants.ErrorsConstants;
import org.msvc.users.app.exceptions.EntityNotFoundException;
import org.msvc.users.app.model.entities.User;
import org.msvc.users.app.repositories.IUserRepository;
import org.msvc.users.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorsConstants.NOT_FOUND,
                        ErrorsConstants.notFound(Constants.USER, id.toString())));
    }

    @Override
    public List<User> findAll() {
        if(userRepository.findAll().isEmpty()) throw new NullPointerException(ErrorsConstants.EMPTY_LIST);
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByName(String name) {
        if(userRepository.findAllByFirstName(name).isEmpty()) throw new EntityNotFoundException(ErrorsConstants.NOT_FOUND,
                ErrorsConstants.notFound(Constants.USER, name));
        return userRepository.findAllByFirstName(name);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByFirstNameLike(name)
                .orElseThrow(() -> new EntityNotFoundException(ErrorsConstants.NOT_FOUND,
                        ErrorsConstants.notFound(Constants.USER, name)));
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        if (userRepository.findById(id).isEmpty())
            throw new EntityNotFoundException(ErrorsConstants.NOT_FOUND,
                    ErrorsConstants.notFound(Constants.USER, id.toString()));
        userRepository.deleteById(id);
        return "User with id: " + id + " found and delete.";
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorsConstants.NOT_FOUND,
                        ErrorsConstants.notFound(Constants.USER, id.toString())));
        currentUser.setUserEmail(user.getUserEmail());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        return userRepository.save(currentUser);
    }
}
