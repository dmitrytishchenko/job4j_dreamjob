package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Dispatcher {
    private final Validate logic = ValidateService.getInstance();
    private Map<String, Consumer<User>> dispatch = new HashMap<>();
    private static final Dispatcher DISPATCHER = new Dispatcher();

    private Dispatcher() {
        init();
        addRoot(new User("admin", "admin", "admin", "admin@mail.ru", "Russia", "MSC", new Date(), "Admin", new Role("admin")));
        addRootCountryAndCity();
    }

    public static Dispatcher getDispatcher() {
        return DISPATCHER;
    }

    public void createNewUser(User user) {
        this.logic.add(user);
    }

    public boolean update(User user) {
        return this.logic.update(user);
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

    public User getRole(String login, String password) {
        User result = null;
        for (User user : logic.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }
        return result;
    }

    private void addRoot(User user) {
        if (logic.findAll().size() != 0) {
            for (User u : logic.findAll()) {
                if (u.getLogin().equals("admin")) {
                    break;
                }
            }
        } else {
            this.logic.add(user);
        }
    }

    private void addCountry(String country) {
        logic.addCountry(country);
    }

    private void addCity(String city, int countryId) {
        logic.addCity(city, countryId);
    }

    private void addRootCountryAndCity() {
        addCountry("Russia");
        addCountry("USA");
        addCity("MSC", 1);
        addCity("SPB", 1);
        addCity("LA", 2);
        addCity("NY", 2);
    }

    public String getCountryFromId(int countryId) {
        return logic.getCountryFromId(countryId);
    }

    public List<String> getCitiesFromCountry(int countryId) {
        return logic.getCitiesFromCountry(countryId);
    }

    public boolean checkInputValues(String name, String login, String password) {
        return logic.checkInputValues(name, login, password);
    }

}
