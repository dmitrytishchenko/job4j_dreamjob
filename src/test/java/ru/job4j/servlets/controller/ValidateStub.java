//package ru.job4j.servlets.controller;
//
//import ru.job4j.servlets.repository.MemoryStore;
//import ru.job4j.servlets.repository.Store;
//
//public final class ValidateStub {
//    private static final Store STORE = MemoryStore.getInstance();
//
//    private ValidateStub() {
//    }
//
//    public static Store getInstance() {
//        return STORE;
//    }
//}
package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private static final ValidateStub validate = new ValidateStub();
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    private ValidateStub() {
    }

    public static ValidateStub getInstance() {
        return validate;
    }

    @Override
    public User add(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return user;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(this.store.values());
    }

    @Override
    public User findById(int id) {
        return null;
    }
}