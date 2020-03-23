package ru.job4j.servlets.model;

import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String login, String email, Date createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(int id, String name, String login, String email, Date createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                createDate.equals(user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate);
    }
}
