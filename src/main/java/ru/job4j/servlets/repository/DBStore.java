package ru.job4j.servlets.repository;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTableUsers();
        createTableCountries();
        createTableCities();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private void createTableCountries() {
        try {
            Connection connection = SOURCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("create TABLE if not exists countries" +
                    "(id serial primary key," +
                    "name varchar(30), unique (name))");
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCities() {
        try {
            Connection connection = SOURCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("create TABLE if not exists cities" +
                    "(id serial primary key," +
                    "name varchar(30)," +
                    "country integer, " +
                    "foreign key (country) references countries(id)," +
                    "unique (name))");
            ps.execute();
            connection.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findAllCountries() {
        List<String> list = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from countries")) {
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> findAllCities() {
        List<String> list = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from cities")) {
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void createTableUsers() {
        try {
            Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement("create TABLE if not exists users" +
                    "(id serial primary key," +
                    "name varchar(20)," +
                    "login varchar(20)," +
                    "password varchar(20)," +
                    "email varchar(20)," +
                    "country varchar(20)," +
                    "city varchar(20)," +
                    "role varchar(20)," +
                    "create_date varchar (100)," +
                    "photoId varchar (50))");
            st.execute();
            connection.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addCountry(String country) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into countries" +
                     "(name ) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, country);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public void addCity(String city, int countryId) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into cities" +
                     "(name, country ) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, city);
            ps.setInt(2, countryId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getCitiesFromCountry(int countryId) {
        List<String> cities = new ArrayList<>();
        String id = String.valueOf(countryId);
        String sql = "select name from cities where (country=" + id + ")";
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                cities.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public String getCountryFromId(int countryId) {
        String result = null;
        String id = String.valueOf(countryId);
        String sql = "select name from countries where id=" + id + "";
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                result = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User add(User model) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into users" +
                     "(name, login, password, email, country, city, role, create_date, photoId) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, model.getName());
            st.setString(2, model.getLogin());
            st.setString(3, model.getPassword());
            st.setString(4, model.getEmail());
            st.setString(5, model.getCountry());
            st.setString(6, model.getCity());
            st.setString(7, model.getRole());
            st.setString(8, model.getCreateDate().toString());
            st.setString(9, model.getPhotoId());
            st.executeUpdate();
            try (ResultSet id = st.getGeneratedKeys()) {
                if (id.next()) {
                    model.setId(id.getInt(1));
                    LOG.trace("Добавлена запись", model.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public boolean update(User model) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("update users set name = ?," +
                     " login = ?," +
                     " email = ?," +
                     " country = ?," +
                     " city = ?," +
                     " role = ?" +
                     " where id = ?")) {
            st.setString(1, model.getName());
            st.setString(2, model.getLogin());
            st.setString(3, model.getEmail());
            st.setString(4, model.getCountry());
            st.setString(5, model.getCity());
            st.setString(6, model.getRole());
            st.setInt(7, model.getId());
            st.executeUpdate();
           result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("delete from users where id = ?")) {
            st.setInt(1, user.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from users")) {
            while (rs.next()) {
                userList.add(getUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from users where id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Date parseDate(String date) {
        Date result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            result = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUser(ResultSet rs) {
        User result = null;
        try {
            result = new User(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("country"),
                    rs.getString("city"),
                    new Role(rs.getString("role")),
                    parseDate(rs.getString("create_date")),
                    rs.getString("photoId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}