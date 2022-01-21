package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.template.Template;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class TemplateUtils
{

    public List<Template> sortTemplatesByID(List<Template> values)
    {
        return values.stream().sorted(Comparator.comparingInt(Template::getID)).toList();
    }

    public List<Template> sortTemplatesByIDReversed(List<Template> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getID() - o1.getID()).toList();
    }

    public List<Template> sortTemplatesByName(List<Template> values)
    {
        return values.stream().sorted(Comparator.comparing(Template::getName)).toList();
    }

    public List<Template> sortTemplatesByNameReversed(List<Template> values)
    {
        return values.stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).toList();
    }

    public List<Template> sortTemplatesByDescription(List<Template> values)
    {
        return values.stream().sorted(Comparator.comparing(Template::getDescription)).toList();
    }

    public List<Template> sortTemplatesByDescriptionReversed(List<Template> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()))
                .toList();
    }

    public List<Template> sortTemplatesByPrice(List<Template> values)
    {
        return values.stream().sorted((o1, o2) ->
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

    public List<Template> sortTemplatesByPriceReversed(List<Template> values)
    {
        return values.stream().sorted((o1, o2) ->
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

    public List<Template> sortTemplatesByAreaName(List<Template> values)
    {
        return values.stream().sorted(Comparator.comparing(o -> o.getArea().getName())).toList();
    }

    public List<Template> sortTemplatesByAreaNameReversed(List<Template> values)
    {
        return values
                .stream()
                .sorted((o1, o2) -> o2.getArea().getName().compareTo(o1.getArea().getName()))
                .toList();
    }

    public Template searchTemplateByID(List<Template> values, int id)
    {
//        List<Template> list = new ArrayList<>();
//        for (Template cur : values)
//        {
//            if (cur.getID() == id)
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Template cur : values)
        {
            if (cur.getID() == id)
            {
                return cur;
            }
        }

        return null;
    }

    public Template searchTemplateByName(List<Template> values, String name)
    {
//        List<Template> list = new ArrayList<>();
//        for (Template cur : values)
//        {
//            if (cur.getName().equals(name))
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Template cur : values)
        {
            if (cur.getName().equals(name))
            {
                return cur;
            }
        }
        return null;
    }

    public Template searchTemplateByDescription(List<Template> values, String description)
    {
//        List<Template> list = new ArrayList<>();
//        for (Template cur : values)
//        {
//            if (cur.getDescription().equals(description))
//            {
//                list.add(cur);
//            }
//        }
//        return list;

        for (Template cur : values)
        {
            if (cur.getDescription().equals(description))
            {
                return cur;
            }
        }
        return null;
    }

    public List<Template> searchTemplatesByPrice(List<Template> values, double price)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : values)
        {
            if (cur.getPrice() == price)
            {
                list.add(cur);
            }
        }
        return list;
    }

    public List<Template> searchTemplatesByArea(List<Template> values, Area area)
    {
        List<Template> list = new ArrayList<>();
        for (Template cur : values)
        {
            if (cur.getArea().equals(area))
            {
                list.add(cur);
            }
        }
        return list;
    }
}
