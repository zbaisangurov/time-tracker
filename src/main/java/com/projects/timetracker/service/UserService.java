package com.projects.timetracker.service;

import com.projects.timetracker.entity.User;
import com.projects.timetracker.exception.UserAlreadyExistsException;
import com.projects.timetracker.exception.UserNotFoundException;
import com.projects.timetracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public void createUser(String name) {
        if (userRepository.existsByName(name)) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        User user = new User();
        user.setName(name);
        log.info("Создание нового пользователя: " + user.getName());
        userRepository.save(user);
    }

    @Transactional
    public void updateUser (Long id, String newName) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Пользователь с Id " + id + " не найден"));
        user.setName(newName);
        log.info("Обновляются данные о пользователе {}", user.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser (Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь с Id " + id + " не найден");
        }
        userRepository.deleteById(id);
        log.info("Удаление пользователя с Id {}", id);
    }
}
