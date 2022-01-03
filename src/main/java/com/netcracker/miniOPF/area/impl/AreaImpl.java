package com.netcracker.miniOPF.area.impl;

import com.netcracker.miniOPF.area.Area;

public class AreaImpl implements Area
{
    int id;
    String name;
    String description;

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
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }
}
