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
package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public User add(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        for (User u : this.store.values()) {
            if (u.getId() == user.getId()) {
                u.setName(user.getName());
                u.setLogin(user.getLogin());
                u.setEmail(user.getEmail());
                System.out.println("The User is update");
                result = true;
            } else {
                throw new IllegalStateException("The User is not update");
            }
        }
        return result;
    }

    @Override
    public void delete(User user) {
        for (User u : this.store.values()) {
            if (u.getId() == user.getId()) {
                this.store.remove(u.getId(), u);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(this.store.values());
    }

    @Override
    public List<String> findAllCities() {
        return null;
    }

    @Override
    public List<String> findAllCountries() {
        return null;
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (User user : this.store.values()) {
            if (user.getId() == id) {
                result = user;
            } else {
                throw new IllegalStateException("The user with this id not found");
            }
        }
        return result;
    }

    @Override
    public String addCountry(String country) {
        return null;
    }

    @Override
    public void addCity(String city, int countryId) {
    }

    @Override
    public String getCountryFromId(int countryId) {
        return null;
    }

    @Override
    public List<String> getCitiesFromCountry(int countryId) {
        return null;
    }

    @Override
    public boolean checkInputValues(String name, String login, String password) {
        return false;
    }
}
