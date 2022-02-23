package com.netcracker.miniOPF.model.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("admin")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminImpl implements Admin
{
    @JsonProperty("password")
    public String password;
    @JsonProperty("name")
    public String name;
    @JsonProperty("login")
    private String login;
    @JsonProperty("id")
    private int id;

    public AdminImpl()
    {
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    @Override
    public void setLogin(String login)
    {
        this.login = login;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public void setID(int id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int compareTo(Admin o)
    {
        return this.getLogin().compareTo(o.getLogin());
    }
}