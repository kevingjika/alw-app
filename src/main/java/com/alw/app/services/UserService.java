package com.alw.app.services;

import com.alw.app.entities.User;
import com.alw.app.exceptions.NoUserFoundException;
import com.alw.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) throws NoUserFoundException {

        user.setUsername(user.getUsername());
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setAddress(user.getAddress());

        Optional<User> findIfUserExists1 = userRepository.findUserByUsername(user.getUsername());
        Optional<User> findIfUserExists2 = userRepository.findUserByEmail(user.getEmail());

        if (findIfUserExists1.isPresent() && findIfUserExists2.isPresent()) {
            throw new NoUserFoundException("User exists.");
        }

        return userRepository.save(user);

    }

    public User editUser(long id) throws NoUserFoundException {

        Optional<User> findIfUserExists = userRepository.findById(id);
        findIfUserExists.get().setPassword(findIfUserExists.get().getPassword());

        if (!findIfUserExists.isPresent()) {
            throw new NoUserFoundException("User doesn't exists.");
        }

        return userRepository.save(findIfUserExists.get());

    }

    public void deleteUser(long id) throws NoUserFoundException {

        Optional<User> findIfUserExists = userRepository.findById(id);

        if(!findIfUserExists.isPresent()) {
            throw new NoUserFoundException(("User doesn't exists."));
        }

        userRepository.deleteById(id);

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOneById(long id) {
        return userRepository.findById(id);
    }

}
