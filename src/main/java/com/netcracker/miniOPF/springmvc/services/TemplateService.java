package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.utils.repos.AreaRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Service
public class TemplateService
{
    TemplateRepo templateRepo;
    AreaRepo areaRepo;

    @Autowired
    public TemplateService(TemplateRepo templateRepo)
    {
        this.templateRepo = templateRepo;
    }

    public String showTemplates(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                                @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                                @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                                Model model)
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
}
