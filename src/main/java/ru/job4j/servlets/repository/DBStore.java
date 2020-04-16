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
        createTable();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    public void createTable() {
        try {
            Connection connection = SOURCE.getConnection();
            Statement st = connection.prepareStatement("create TABLE if not exists users" +
                    "(id serial primary key," +
                    "name varchar(20)," +
                    "login varchar(20)," +
                    "password varchar(20)," +
                    "email varchar(20)," +
                    "role varchar(20)," +
                    "create_date varchar (100)," +
                    "photoId varchar (50))");
            ((PreparedStatement) st).execute();
            connection.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User add(User model) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("insert into users" +
                     "(name, login, password, email, role, create_date, photoId) values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, model.getName());
            st.setString(2, model.getLogin());
            st.setString(3, model.getPassword());
            st.setString(4, model.getEmail());
            st.setString(5, model.getRole());
            st.setString(6, model.getCreateDate().toString());
            st.setString(7,model.getPhotoId());
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
    public void update(User model) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("update users set name = ?," +
                     " login = ?," +
                     " email = ?," +
                     " role = ?" +
                     " where id = ?")) {
            st.setString(1, model.getName());
            st.setString(2, model.getLogin());
            st.setString(3, model.getEmail());
            st.setString(4, model.getRole());
            st.setInt(5, model.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Date parseDate(String date) {
        Date result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            result = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUser(ResultSet rs) {
        User result = null;
        try {
            result = new User(rs.getInt("id"),
                     rs.getString("name"),
                     rs.getString("login"),
                     rs.getString("password"),
                     rs.getString("email"),
                     new Role(rs.getString("role")),
                     parseDate(rs.getString("create_date")),
                     rs.getString("photoId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}