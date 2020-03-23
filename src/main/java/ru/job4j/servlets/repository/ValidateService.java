package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.List;

public class ValidateService implements Validate {
    private static final ValidateService VALIDATE_SERVICE = new ValidateService();
    private final Store logic = DBStore.getInstance();
//    private final Store logic = MemoryStore.getMemoryStore();

    private ValidateService() {
    }

    public static ValidateService getValidateService() {
        return VALIDATE_SERVICE;
    }

    public void add(User user) {
        if (logic.findById(user.getId()) != null) {
            throw new NullPointerException("The User is exists");
        }
        this.logic.add(user);
    }

    public void update(User user) {
        logic.update(user);
        if (logic.findById(user.getId()).getName().equals(user.getName())) {
            System.out.println("The User is update");
        } else {
            throw new IllegalStateException("The User is not update");
        }
    }

    public void delete(User user) {
        logic.delete(user);
        for (User u : logic.findAll()) {
            if (u.equals(user)) {
                throw new IllegalStateException("The User is not delete");
            }
        }
    }

    public List<User> findAll() {
        return logic.findAll();
    }

    public User findById(int id) {
        return logic.findById(id);
    }
}
