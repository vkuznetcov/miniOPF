package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.area.Area;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class AreaHandler
{
    public List<Area> sortAreasByID(List<Area> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Area::getID)).toList();
    }

    public List<Area> sortAreasByIDReversed(List<Area> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Area> sortAreasByName(List<Area> values)
    {
        return values.stream().sorted(Comparator.comparing(Area::getName)).toList();
    }

    public List<Area> sortAreasByNameReversed(List<Area> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Area> sortAreasByDescription(List<Area> values)
    {
        return values.stream().sorted(Comparator.comparing(Area::getDescription)).toList();
    }

    public List<Area> sortAreasByDescriptionReversed(List<Area> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                .toList();
    }

    public List<Area> searchAreasByID(List<Area> values, int id)
    {
        List<Area> list = new ArrayList<>();
        for (Area cur : values)
        {
            if (cur.getID() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Area> searchAreasByName(List<Area> values, String name)
    {
        List<Area> list = new ArrayList<>();
        for (Area cur : values)
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Area> searchAreasByDescription(List<Area> values, String description)
    {
        List<Area> list = new ArrayList<>();
        for (Area cur : values)
        {
            if (cur.getDescription().equals(description))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
