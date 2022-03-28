package com.netcracker.miniOPF.utils.controllers;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.AreaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class AreaController
{
    private final Storage storage;
    private final AreaUtils areaUtils;

    @Autowired
    public AreaController(Storage storage,
                          AreaUtils areaUtils)
    {
        this.storage = storage;
        this.areaUtils = areaUtils;
    }

    public List<Area> sortAreasByID()
    {
        return areaUtils.sortAreasByID(storage.getAreaValues());
    }

    public List<Area> sortAreasByIDReversed()
    {
        return areaUtils.sortAreasByIDReversed(storage.getAreaValues());
    }

    public List<Area> sortAreasByName()
    {
        return areaUtils.sortAreasByName(storage.getAreaValues());
    }

    public List<Area> sortAreasByNameReversed()
    {
        return areaUtils.sortAreasByNameReversed(storage.getAreaValues());
    }

    public List<Area> sortAreasByDescription()
    {
        return areaUtils.sortAreasByDescription(storage.getAreaValues());
    }

    public List<Area> sortAreasByDescriptionReversed()
    {
        return areaUtils.sortAreasByDescriptionReversed(storage.getAreaValues());
    }

    public Area searchAreaByID(int ID)
    {
        return areaUtils.searchAreaByID(storage.getAreaValues(), ID);
    }

    public Area searchAreaByName(String name)
    {
        return areaUtils.searchAreaByName(storage.getAreaValues(), name);
    }

    public List<Area> searchAreasByDescription(String description)
    {
        return areaUtils.searchAreasByDescription(storage.getAreaValues(), description);
    }

    public Area getArea(int id)
    {
        return storage.getArea(id);
    }

    public List<Area> getAreaValues()
    {
        return storage.getAreaValues();
    }
}
