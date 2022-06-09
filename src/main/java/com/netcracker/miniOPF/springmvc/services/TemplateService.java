package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class TemplateService
{
    TemplateRepo templateRepo;
    AreaRepo areaRepo;

    @Autowired
    public TemplateService(TemplateRepo templateRepo, AreaRepo areaRepo)
    {
        this.templateRepo = templateRepo;
        this.areaRepo = areaRepo;
    }

    public String showTemplates(String type, String sort, String value, Model model)
    {
        try
        {
            if (Objects.nonNull(value))
            {
                switch (type)
                {
                    case "id" -> model.addAttribute("table",
                                                    templateRepo.getTemplate(Integer.parseInt(value)));
                    case "name" -> model.addAttribute("table",
                                                      templateRepo.searchTemplateByName(value));
                    case "description" -> model.addAttribute("table",
                                                             templateRepo.searchTemplatesByDescription(value));
                    case "price" -> model.addAttribute("table",
                                                       templateRepo.searchTemplatesByPrice(Double.parseDouble(value)));
                    case "area" -> model.addAttribute("table",
                                                      templateRepo.searchTemplatesByArea(Integer.parseInt(value)));
                }
                return "admin/templates";
            }
            boolean reversed = sort.equals("desc");
            model.addAttribute("table", templateRepo.sortTemplatesByID(reversed));
            if (Objects.nonNull(type))
            {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateRepo.sortTemplatesByID(reversed));
                    case "name" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByName(reversed));
                    case "description" -> model.addAttribute("table",
                                                             templateRepo.sortTemplatesByDescription(reversed));
                    case "price" -> model.addAttribute("table",
                                                       templateRepo.sortTemplatesByPrice(reversed));
                    case "area" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByAreaID(reversed));
                }
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "admin/templates";
    }

    private boolean checkParams(String areaId, StringBuilder errorMessage) throws SQLException
    {
        boolean error = false;
        if (areaRepo.getArea(Integer.parseInt(areaId)) == null)
        {
            errorMessage.append("There is no such area! ");
            error = true;
        }
        return error;
    }

    public String updateTemplates(TemplateImpl template, String areaId, Model model)
    {
        try
        {
            String errorMessage = "";
            StringBuilder stringBuilder = new StringBuilder(errorMessage);
            if (checkParams(areaId, stringBuilder))
            {
                stringBuilder.append("Error index: ").append(template.getId());
                model.addAttribute("errorMessage", stringBuilder.toString());
            }
            else
            {
                template.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
                templateRepo.updateTemplate(template.getId(), template);
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return this.showTemplates(null, "none", null, model);
    }

    public String createTemplate(Template template, String areaId, Model model)
    {
        try
        {
            String errorMessage = "";
            StringBuilder stringBuilder = new StringBuilder(errorMessage);
            if (checkParams(areaId, stringBuilder))
            {
                stringBuilder.append("Error index: new object creation");
                model.addAttribute("errorMessage", stringBuilder.toString());
            }
            else
            {
                template.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
                templateRepo.createTemplate(template);
            }
        }
        catch (SQLException e)
        {
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return this.showTemplates(null, "none", null, model);
    }

    public List<Template> sortTemplatesByID(boolean reversed) throws SQLException
    {
        return templateRepo.sortTemplatesByID(reversed);
    }

    public List<Template> sortTemplatesByName(boolean reversed) throws SQLException
    {
        return templateRepo.sortTemplatesByName(reversed);
    }

    public List<Template> sortTemplatesByDescription(boolean reversed) throws SQLException
    {
        return templateRepo.sortTemplatesByDescription(reversed);
    }

    public List<Template> sortTemplatesByPrice(boolean reversed) throws SQLException
    {
        return templateRepo.sortTemplatesByPrice(reversed);
    }

    public List<Template> sortTemplatesByAreaID(boolean reversed) throws SQLException
    {
        return templateRepo.sortTemplatesByAreaID(reversed);
    }

    public Template searchTemplateByName(String name) throws SQLException
    {
        return templateRepo.searchTemplateByName(name);
    }

    public List<Template> searchTemplatesByDescription(String description) throws SQLException
    {
        return templateRepo.searchTemplatesByDescription(description);
    }

    public List<Template> searchTemplatesByPrice(double price) throws SQLException
    {
        return templateRepo.searchTemplatesByPrice(price);
    }

    public List<Template> searchTemplatesByArea(int areaID) throws SQLException
    {
        return templateRepo.searchTemplatesByArea(areaID);
    }

    public Template getTemplate(int id) throws SQLException
    {
        return templateRepo.getTemplate(id);
    }

    public List<Template> getTemplateValues() throws SQLException
    {
        return templateRepo.getTemplateValues();
    }

    public void updateTemplate(int id, Template template) throws SQLException
    {
        templateRepo.updateTemplate(id, template);
    }

    public void deleteTemplate(int id) throws SQLException
    {
        templateRepo.deleteTemplate(id);
    }

    public void createTemplate(Template template) throws SQLException
    {
        templateRepo.createTemplate(template);
    }
}
