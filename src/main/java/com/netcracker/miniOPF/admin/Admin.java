package com.netcracker.miniOPF.admin;

public interface Admin extends Comparable<Admin> {
    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    int getID();

    void setID(int id);

    String getName();

    void setName(String name);
}
