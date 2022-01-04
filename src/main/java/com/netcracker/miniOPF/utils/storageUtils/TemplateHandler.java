package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.template.Template;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TemplateHandler
{

    private final Map<Integer, Template> templateMap;

    public TemplateHandler(Map<Integer, Template> templateMap)
    {
        this.templateMap = templateMap;
    }

    public List<Template> sortTemplatesByID()
    {
        return templateMap.values().stream().sorted(Comparator.comparingInt(Template::getID)).toList();
    }

    public List<Template> sortTemplatesByIDReversed()
    {
        return templateMap.values().stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Template> sortTemplatesByName()
    {
        return templateMap.values().stream().sorted(Comparator.comparing(Template::getName)).toList();
    }

    public List<Template> sortTemplatesByNameReversed()
    {
        return templateMap.values().stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Template> sortTemplatesByDescription()
    {
        return templateMap.values().stream().sorted(Comparator.comparing(Template::getDescription)).toList();
    }

    public List<Template> sortTemplatesByDescriptionReversed()
    {
        return templateMap.values()
                          .stream()
                          .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                          .toList();
    }

    public List<Template> sortTemplatesByPrice()
    {
        return templateMap.values().stream().sorted((o1, o2) ->
                                                    {
                                                        if (o1.getPrice() - o2.getPrice() > 0)
                                                        {
                                                            return 1;
                                                        }
                                                        else if (o1.getPrice() == o2.getPrice())
                                                        {
                                                            return 0;
                                                        }
                                                        else
                                                        {
                                                            return -1;
                                                        }
                                                    }).toList();
    }

    public List<Template> sortTemplatesByPriceReversed()
    {
        return templateMap.values().stream().sorted((o1, o2) ->
                                                    {
                                                        if (o2.getPrice() - o1.getPrice() > 0)
                                                        {
                                                            return 1;
                                                        }
                                                        else if (o1.getPrice() == o2.getPrice())
                                                        {
                                                            return 0;
                                                        }
                                                        else
                                                        {
                                                            return -1;
                                                        }
                                                    }).toList();
    }

    public List<Template> sortTemplatesByAreaName()
    {
        return templateMap.values().stream().sorted(Comparator.comparing(o -> o.getArea().getName())).toList();
    }

    public List<Template> sortTemplatesByAreaNameReversed()
    {
        return templateMap.values()
                          .stream()
                          .sorted((o1, o2) -> o2.getArea().getName().compareTo(o1.getArea().getName()))
                          .toList();
    }

    public List<Template> searchTemplateByID(int id)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : templateMap.values())
        {
            if (cur.getID() == id)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Template> searchTemplateByName(String name)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : templateMap.values())
        {
            if (cur.getName().equals(name))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Template> searchTemplateByDescription(String description)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : templateMap.values())
        {
            if (cur.getDescription().equals(description))
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Template> searchTemplateByPrice(double price)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : templateMap.values())
        {
            if (cur.getPrice() == price)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Template> searchTemplateByArea(Area area)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : templateMap.values())
        {
            if (cur.getArea().equals(area))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
