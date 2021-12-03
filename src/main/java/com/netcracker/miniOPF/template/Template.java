package com.netcracker.miniOPF.template;

import com.netcracker.miniOPF.area.Area;

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
