package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.HashMap;
import java.util.List;

public class ValidateService implements Validate {
    private static final ValidateService VALIDATE_SERVICE = new ValidateService();

    public static class Holder {
        private static final Store logic = DBStore.getInstance();
    }

    private ValidateService() {
    }

    public static Validate getInstance() {
        return VALIDATE_SERVICE;
    }

    public User add(User user) {
        if (Holder.logic.findById(user.getId()) != null) {
            throw new NullPointerException("The User is exists");
        }
        Holder.logic.add(user);
        return user;
    }

    public void update(User user) {
        if (Holder.logic.findById(user.getId()).getName().equals(user.getName())) {
            Holder.logic.update(user);
            System.out.println("The User is update");
        } else {
            throw new IllegalStateException("The User is not update");
        }
    }

    public void delete(User user) {
        Holder.logic.delete(user);
        for (User u : Holder.logic.findAll()) {
            if (u.equals(user)) {
                throw new IllegalStateException("The User is not delete");
            }
        }
    }

    public List<User> findAll() {
        return Holder.logic.findAll();
    }

    public User findById(int id) {
        return Holder.logic.findById(id);
    }
}
