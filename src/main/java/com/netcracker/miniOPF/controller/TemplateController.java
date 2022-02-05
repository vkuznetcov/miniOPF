package com.netcracker.miniOPF.controller;

import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.utils.storageUtils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class TemplateController
{
    private final Storage storage;
    private final TemplateUtils templateUtils;

    @Autowired
    public TemplateController(Storage storage,
                              TemplateUtils templateUtils)
    {
        this.storage = storage;
        this.templateUtils = templateUtils;
    }

    public List<Template> sortTemplatesByID()
    {
        return templateUtils.sortTemplatesByID(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByIDReversed()
    {
        return templateUtils.sortTemplatesByIDReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByName()
    {
        return templateUtils.sortTemplatesByName(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByNameReversed()
    {
        return templateUtils.sortTemplatesByNameReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByDescription()
    {
        return templateUtils.sortTemplatesByDescription(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByDescriptionReversed()
    {
        return templateUtils.sortTemplatesByDescriptionReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByPrice()
    {
        return templateUtils.sortTemplatesByPrice(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByPriceReversed()
    {
        return templateUtils.sortTemplatesByPriceReversed(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByAreaName()
    {
        return templateUtils.sortTemplatesByAreaName(storage.getTemplateValues());
    }

    public List<Template> sortTemplatesByAreaNameReversed()
    {
        return templateUtils.sortTemplatesByAreaNameReversed(storage.getTemplateValues());
    }

    public Template searchTemplateByID(
            int id)
    {
        return templateUtils.searchTemplateByID(storage.getTemplateValues(), id);
    }

    public Template searchTemplateByName(
            String name)
    {
        return templateUtils.searchTemplateByName(storage.getTemplateValues(), name);
    }

    public Template searchTemplateByDescription(
            String description)
    {
        return templateUtils.searchTemplateByDescription(storage.getTemplateValues(), description);
    }

    public List<Template> searchTemplatesByPrice(
            double price)
    {
        return templateUtils.searchTemplatesByPrice(storage.getTemplateValues(), price);
    }

    public List<Template> searchTemplatesByArea(
            Area area)
    {
        return templateUtils.searchTemplatesByArea(storage.getTemplateValues(), area);
    }

    public Template getTemplate(int id)
    {
        return storage.getTemplate(id);
    }

    public List<Template> getTemplateValues()
    {
        return storage.getTemplateValues();
    }
}
