package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Dispatcher {
    private final Validate logic = ValidateService.getValidateService();
    private Map<String, Consumer<User>> dispatch = new HashMap<>();
    private static final Dispatcher DISPATCHER = new Dispatcher();

    private Dispatcher() {
        init();
        addRoot(new User("root", "root", "root", "root@mail.ru", new Date(), "root"));
    }

    public static Dispatcher getDispatcher() {
        return DISPATCHER;
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

    public boolean isCredential(String login, String password) {
        boolean result = false;
        for (User user : logic.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addRoot(User user) {
        if (logic.findAll().size() != 0) {
            for (User u : logic.findAll()) {
                if (u.getLogin().equals("root")) {
                    break;
                }
            }
        } else {
            this.logic.add(user);
        }
    }
}
