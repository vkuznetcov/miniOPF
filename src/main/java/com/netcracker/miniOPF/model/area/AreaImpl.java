package com.netcracker.miniOPF.model.area;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("area")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
