package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.AreaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class AreaController
{
    private final Storage storage;
    private final AreaHandler areaHandler;

    @Autowired
    public AreaController(Storage storage,
                          AreaHandler areaHandler)
    {
        this.storage = storage;
        this.areaHandler = areaHandler;
    }

    public List<Area> sortAreasByID()
    {
        return areaHandler.sortAreasByID(storage.getAreaValues());
    }

    public List<Area> sortAreasByIDReversed()
    {
        return areaHandler.sortAreasByIDReversed(storage.getAreaValues());
    }

    public List<Area> sortAreasByName()
    {
        return areaHandler.sortAreasByName(storage.getAreaValues());
    }

    public List<Area> sortAreasByNameReversed()
    {
        return areaHandler.sortAreasByNameReversed(storage.getAreaValues());
    }

    public List<Area> sortAreasByDescription()
    {
        return areaHandler.sortAreasByDescription(storage.getAreaValues());
    }

    public List<Area> sortAreasByDescriptionReversed()
    {
        return areaHandler.sortAreasByDescriptionReversed(storage.getAreaValues());
    }

    public List<Area> searchAreasByID(int ID)
    {
        return areaHandler.searchAreasByID(storage.getAreaValues(), ID);
    }

    public List<Area> searchAreasByName(String name)
    {
        return areaHandler.searchAreasByName(storage.getAreaValues(), name);
    }

    public List<Area> searchAreasByDescription(String description)
    {
        return areaHandler.searchAreasByDescription(storage.getAreaValues(), description);
    }

}
