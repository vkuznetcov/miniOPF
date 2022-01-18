package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.utils.storageUtils.TemplateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class TemplateController
{
    private final Storage storage;
    private final TemplateHandler templateHandler;

    @Autowired
    public TemplateController(Storage storage,
                              TemplateHandler templateHandler)
    {
        this.storage = storage;
        this.templateHandler = templateHandler;
    }

    public List<Template> sortTemplatesByID()
    {
        return templateHandler.sortTemplatesByID(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByIDReversed()
    {
        return templateHandler.sortTemplatesByIDReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByName()
    {
        return templateHandler.sortTemplatesByName(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByNameReversed()
    {
        return templateHandler.sortTemplatesByNameReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByDescription()
    {
        return templateHandler.sortTemplatesByDescription(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByDescriptionReversed()
    {
        return templateHandler.sortTemplatesByDescriptionReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByPrice()
    {
        return templateHandler.sortTemplatesByPrice(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByPriceReversed()
    {
        return templateHandler.sortTemplatesByPriceReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByAreaName()
    {
        return templateHandler.sortTemplatesByAreaName(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByAreaNameReversed()
    {
        return templateHandler.sortTemplatesByAreaNameReversed(storage.getTemplateValues());
    }

    public List<Template> searchTemplateByID(
            int id)
    {
        return templateHandler.searchTemplateByID(storage.getTemplateValues(), id);
    }

    public List<Template> searchTemplateByName(
            String name)
    {
        return templateHandler.searchTemplateByName(storage.getTemplateValues(), name);
    }

    public List<Template> searchTemplateByDescription(
            String description)
    {
        return templateHandler.searchTemplateByDescription(storage.getTemplateValues(), description);
    }

    public List<Template> searchTemplateByPrice(
            double price)
    {
        return templateHandler.searchTemplateByPrice(storage.getTemplateValues(), price);
    }

    public List<Template> searchTemplateByArea(
            Area area)
    {
        return templateHandler.searchTemplateByArea(storage.getTemplateValues(), area);
    }
}
