package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
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
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", templateRepo.getTemplateValues());
                return "admin/templates";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateRepo.sortTemplatesByID());
                    case "name" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByName());
                    case "description" -> model.addAttribute("table",
                                                             templateRepo.sortTemplatesByDescription());
                    case "price" -> model.addAttribute("table",
                                                       templateRepo.sortTemplatesByPrice());
                    case "area" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByAreaID());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", templateRepo.sortTemplatesByIDReversed());
                    case "name" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByNameReversed());
                    case "description" -> model.addAttribute("table",
                                                             templateRepo.sortTemplatesByDescriptionReversed());
                    case "price" -> model.addAttribute("table",
                                                       templateRepo.sortTemplatesByPriceReversed());
                    case "area" -> model.addAttribute("table",
                                                      templateRepo.sortTemplatesByAreaIDReversed());
                }
            }
        }

        return "admin/templates";
    }

    private boolean checkTemplate(TemplateImpl template, String areaId, StringBuilder errorMessage){
        boolean error = false;
        if(areaRepo.getArea(Integer.parseInt(areaId)) == null){
            errorMessage.append("There is no such area! ");
            error = true;
        }
        return error;
    }

    public String updateTemplates(TemplateImpl template, String areaId, Model model)
    {
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if(checkTemplate(template, areaId, stringBuilder)){
            stringBuilder.append("Error index: ").append(template.getId());
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else{
            template.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
            templateRepo.updateTemplate(template.getId(), template);
        }
        return this.showTemplates(null, "none", null, model);
    }

    public String createTemplate(@ModelAttribute("template") TemplateImpl template,
                                  @RequestParam(name = "areaId")String areaId,
                                  Model model)
    {
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder(errorMessage);
        if(checkTemplate(template, areaId, stringBuilder)){
            stringBuilder.append("Error index: new object creation");
            model.addAttribute("errorMessage", stringBuilder.toString());
        }
        else{
            template.setArea(areaRepo.getArea(Integer.parseInt(areaId)));
            templateRepo.createTemplate(template);
        }
        return this.showTemplates(null, "none", null, model);
    }

    public List<Template> sortTemplatesByID()
    {
        return templateRepo.sortTemplatesByID();
    }

    public List<Template> sortTemplatesByIDReversed()
    {
        return templateRepo.sortTemplatesByIDReversed();
    }

    public List<Template> sortTemplatesByName()
    {
        return templateRepo.sortTemplatesByName();
    }

    public List<Template> sortTemplatesByNameReversed()
    {
        return templateRepo.sortTemplatesByNameReversed();
    }

    public List<Template> sortTemplatesByDescription()
    {
        return templateRepo.sortTemplatesByDescription();
    }

    public List<Template> sortTemplatesByDescriptionReversed()
    {
        return templateRepo.sortTemplatesByDescriptionReversed();
    }

    public List<Template> sortTemplatesByPrice()
    {
        return templateRepo.sortTemplatesByPrice();
    }

    public List<Template> sortTemplatesByPriceReversed()
    {
        return templateRepo.sortTemplatesByPriceReversed();
    }

    public List<Template> sortTemplatesByAreaID()
    {
        return templateRepo.sortTemplatesByAreaID();
    }

    public List<Template> sortTemplatesByAreaIDReversed()
    {
        return templateRepo.sortTemplatesByAreaIDReversed();
    }

    public Template searchTemplateByName(String name)
    {
        return templateRepo.searchTemplateByName(name);
    }

    public List<Template> searchTemplatesByDescription(String description)
    {
        return templateRepo.searchTemplatesByDescription(description);
    }

    public List<Template> searchTemplatesByPrice(double price)
    {
        return templateRepo.searchTemplatesByPrice(price);
    }

    public List<Template> searchTemplatesByArea(int areaID)
    {
        return templateRepo.searchTemplatesByArea(areaID);
    }

    public Template getTemplate(int id)
    {
        return templateRepo.getTemplate(id);
    }

    public List<Template> getTemplateValues()
    {
        return templateRepo.getTemplateValues();
    }

    public void updateTemplate(int id, Template template)
    {
        templateRepo.updateTemplate(id, template);
    }

    public void deleteTemplate(int id)
    {
        templateRepo.deleteTemplate(id);
    }

    public void createTemplate(Template template)
    {
        templateRepo.createTemplate(template);
    }
}
