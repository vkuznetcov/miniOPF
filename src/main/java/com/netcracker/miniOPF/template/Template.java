package com.netcracker.miniOPF.template;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.template.impl.TemplateImpl;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = TemplateImpl.class, name = "template")
)
public interface Template {
    int getID();

    void setID(int id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    double getPrice();

    void setPrice(double price);

    Area getArea();

    void setArea(Area area);
}
