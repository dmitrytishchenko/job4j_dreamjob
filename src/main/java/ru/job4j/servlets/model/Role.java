package ru.job4j.servlets.model;

import java.util.List;

public class Role {
    private String name;
    private List<Role> roles;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNewRole(String name) {
        roles.add(new Role(name));
    }

    public Role getRole(String name) {
        Role result = null;
        for (Role role : this.roles) {
            if (role.getName().equals(name)) {
                result = role;
            }
        }
        return result;
    }
}
