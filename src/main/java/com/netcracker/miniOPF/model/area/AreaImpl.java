package com.netcracker.miniOPF.model.area;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;


@JsonTypeName("area")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class AreaImpl implements Area
{
    int id;
    String name;
    String description;

    public AreaImpl(){}

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
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        AreaImpl area = (AreaImpl) o;
        return id == area.id && Objects.equals(name, area.name) &&
                Objects.equals(description, area.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString()
    {
        return "AreaImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
