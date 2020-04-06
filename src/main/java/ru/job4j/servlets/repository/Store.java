package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.List;

public interface Store {
    User add(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findById(int id);
}