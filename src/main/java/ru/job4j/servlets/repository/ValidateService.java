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

    @Override
    public String addCountry(String country) {
        Holder.logic.addCountry(country);
        return country;
    }

    @Override
    public void addCity(String city, int countryId) {
        Holder.logic.addCity(city, countryId);
    }

    @Override
    public String getCountryFromId(int countryId) {
        return Holder.logic.getCountryFromId(countryId);
    }

    @Override
    public boolean checkInputValues(String name, String login, String password) {
        boolean result = false;
        if (!name.equals(login) && !login.equals(password) && !name.equals(password)) {
            result = true;
        } else {
            throw new IllegalStateException("Insert the correct data");
        }
        return result;
    }

    public boolean update(User user) {
        return Holder.logic.update(user);
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

    @Override
    public List<String> findAllCities() {
        return Holder.logic.findAllCities();
    }

    @Override
    public List<String> findAllCountries() {
        return Holder.logic.findAllCountries();
    }

    public User findById(int id) {
        return Holder.logic.findById(id);
    }

    public List<String> getCitiesFromCountry(int countryId) {
        return Holder.logic.getCitiesFromCountry(countryId);
    }
}
