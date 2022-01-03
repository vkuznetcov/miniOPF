package com.netcracker.miniOPF.area;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netcracker.miniOPF.area.impl.AreaImpl;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = AreaImpl.class, name = "area")
)
public interface Area
{
    int getID();

    void setID(int id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);
}
