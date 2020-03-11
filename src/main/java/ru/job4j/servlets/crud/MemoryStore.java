package ru.job4j.servlets.crud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStore implements Store {
    private static final MemoryStore MEMORY_STORE = new MemoryStore();
    private List<User> userList = new CopyOnWriteArrayList<User>();

    private MemoryStore() {
    }

    public static MemoryStore getMemoryStore() {
        return MEMORY_STORE;
    }

    @Override
    public void add(User user) {
        this.userList.add(user);
    }

    @Override
    public void update(User user) {
        for (User u : this.userList) {
            if (u.getId() == user.getId()) {
                u.setName(user.getName());
            }
        }
    }

    @Override
    public void delete(User user) {
        for (User u : this.userList) {
            if (u.getId() == user.getId()) {
                this.userList.remove(u);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return this.userList;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (User user : this.userList) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }
}
