package ru.job4j.servlets.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Dispatcher {
    private final Validate logic = ValidateService.getValidateService();
    private Map<String, Consumer<User>> dispatch = new HashMap<>();

    public Dispatcher() {
        init();
    }

    public void createNewUser(User user) {
        this.logic.add(user);
    }

    public void update(User user) {
        this.logic.update(user);
    }

    public void delete(User user) {
        this.logic.delete(user);
    }

    public List<User> findAll() {
        return this.logic.findAll();
    }

    public User findById(int id) {
        return logic.findById(id);
    }

    public void load(String action, Consumer<User> handle) {
        this.dispatch.put(action, handle);
    }

    public Dispatcher init() {
        this.load("ADD", this::createNewUser);
        this.load("UPDATE", this::update);
        this.load("DELETE", this::delete);
        return this;
    }

    public void work(String action, User user) {
        for (String act : dispatch.keySet()) {
            if (act.equals(action)) {
                this.dispatch.get(act).accept(user);
            }
        }
    }
}
