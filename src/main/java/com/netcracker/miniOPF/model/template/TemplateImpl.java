package com.netcracker.miniOPF.model.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.netcracker.miniOPF.model.area.Area;


@JsonTypeName("template")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateImpl implements Template
{
    private int id;
    private String name;
    private String description;
    private double price;
    private Area area;


    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
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

    @Override
    public double getPrice()
    {
        return price;
    }

    @Override
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public Area getArea()
    {
        return area;
    }

    @Override
    public void setArea(Area area)
    {
        this.area = area;
    }
}
