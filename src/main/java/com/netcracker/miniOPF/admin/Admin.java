package com.netcracker.miniOPF.admin;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netcracker.miniOPF.admin.impl.AdminImpl;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminImpl.class, name = "admin")
})
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
