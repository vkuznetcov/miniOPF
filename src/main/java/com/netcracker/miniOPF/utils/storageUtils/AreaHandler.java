package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.area.Area;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AreaHandler
{

    private final Map<Integer, Area> areaMap;

    public AreaHandler(Map<Integer, Area> areaMap)
    {
        this.areaMap = areaMap;
    }

    public List<Area> sortAreasByID()
    {
        return areaMap.values().stream().sorted(Comparator.comparingInt(Area::getID)).toList();
    }

    public List<Area> sortAreasByIDReversed()
    {
        return areaMap.values().stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Area> sortAreasByName()
    {
        return areaMap.values().stream().sorted(Comparator.comparing(Area::getName)).toList();
    }

    public List<Area> sortAreasByNameReversed()
    {
        return areaMap.values().stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Area> sortAreasByDescription()
    {
        return areaMap.values().stream().sorted(Comparator.comparing(Area::getDescription)).toList();
    }

    public List<Area> sortAreasByDescriptionReversed()
    {
        return areaMap.values()
                      .stream()
                      .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                      .toList();
    }
}
