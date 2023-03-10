package com.webcamp5.foodhandongserver.service;

import com.webcamp5.foodhandongserver.model.Restaurant;
import com.webcamp5.foodhandongserver.model.User;
import com.webcamp5.foodhandongserver.model.request.UserCreationRequest;
import com.webcamp5.foodhandongserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User readUser(String id) {
        Optional<User> user = userRepository.findByUserId(id);
        if (user.isPresent()) {
            return user.get();
        }

        throw new EntityNotFoundException("Cant find any user under given ID");
    }

    public User readUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return user.get();
        }

        throw new EntityNotFoundException("Can't find any user under given ID");
    }

    public User readUserByPassword(String password) {
        Optional<User> user = userRepository.findByPassword(password);
        if (user.isPresent()) {
            return user.get();
        }

        throw new EntityNotFoundException("Can't find any user under given Password");
    }
    public User createUser(UserCreationRequest user){
        User userToCreate = new User();
        BeanUtils.copyProperties(user, userToCreate);
        return userRepository.save(userToCreate);
    }

    public void deleteUser(Long id) { userRepository.deleteById(id);}


    public User updateUser (Long id, UserCreationRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new EntityNotFoundException(
                    "Member not present in the database");
        }

        User user = optionalUser.get();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setProfileUrl(request.getProfileUrl());
        user.setNickname(request.getNickname());
        user.setUserId(request.getUserId());
        user.setPassword(request.getPassword());
        user.setLoginCount(request.getLoginCount());
        return userRepository.save(user);
    }
}
