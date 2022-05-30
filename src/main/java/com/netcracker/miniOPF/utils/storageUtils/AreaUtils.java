package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.area.Area;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class AreaUtils
{
    public List<Area> sortAreasByID(List<Area> values, boolean reversed)
            throws NullPointerException
    {
        ListUtils.checkListIsEmptyOrNull(values);
        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getId() - o1.getId()).toList();
        }
        else
        {
            return values.stream().sorted(Comparator.comparingInt(Area::getId)).toList();
        }
    }

    public List<Area> sortAreasByName(List<Area> values, boolean reversed) throws NullPointerException
    {
        ListUtils.checkListIsEmptyOrNull(values);
        if (reversed)
        {
            return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
        }
        else
        {
            return values.stream().sorted(Comparator.comparing(Area::getName)).toList();
        }
    }

    public List<Area> sortAreasByDescription(List<Area> values, boolean reversed) throws NullPointerException
    {
        ListUtils.checkListIsEmptyOrNull(values);
        if (reversed)
        {
            return values
                    .stream()
                    .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                    .toList();
        }
        else
        {
            return values.stream().sorted(Comparator.comparing(Area::getDescription)).toList();
        }
    }

    public Area searchAreaByID(List<Area> values, int id) throws NullPointerException
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getId() == id).findAny().orElse(null);
    }

    public Area searchAreaByName(List<Area> values, String name) throws NullPointerException
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getName().equals(name)).findAny().orElse(null);
    }

    public List<Area> searchAreasByDescription(List<Area> values, String description) throws NullPointerException
    {
        ListUtils.checkListIsEmptyOrNull(values);
        return values.stream().filter(i -> i.getDescription().equals(description)).toList();
    }
}
