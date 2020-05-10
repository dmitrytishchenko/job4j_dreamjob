package ru.job4j.servlets.repository;

import ru.job4j.servlets.model.User;

import java.util.List;

public interface Validate {
    User add(User user);

    boolean update(User user);

    void delete(User user);

    List<User> findAll();

    List<String> findAllCities();

    List<String> findAllCountries();

    User findById(int id);

    String addCountry(String country);

    void addCity(String city, int countryId);

    String getCountryFromId(int countryId);

    List<String> getCitiesFromCountry(int countryId);

    boolean checkInputValues(String name, String login, String password);
}
