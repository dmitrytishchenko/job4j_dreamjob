package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStore implements Store {
    private static final MemoryStore MEMORY_STORE = new MemoryStore();
    private List<User> userList = new CopyOnWriteArrayList<User>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return MEMORY_STORE;
    }

    @Override
    public User add(User user) {
        this.userList.add(user);
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        for (User u : this.userList) {
            if (u.getId() == user.getId()) {
                u.setName(user.getName());
                result = true;
            }
        }
        return result;
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
        for (User user : this.userList) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    @Override
    public String addCountry(String country) {
        return country;
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
}
