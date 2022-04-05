package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.utils.repos.AreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Service
public class AreaService
{
    private final AreaRepo areaRepo;

    @Autowired
    public AreaService(AreaRepo areaRepo)
    {
        this.areaRepo = areaRepo;
    }

    public String showAreas(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                            @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                            @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value, Model model)
    {
        if (Objects.nonNull(value))
        {
            switch (type)
            {
                case "id" -> model.addAttribute("table", areaRepo.getArea(Integer.parseInt(value)));
                case "name" -> model.addAttribute("table", areaRepo.searchAreaByName(value));
                case "description" -> model.addAttribute("table", areaRepo.searchAreasByDescription(value));
            }
            return "admin/areas";
        }
        switch (sort)
        {
            case "none" -> {
                model.addAttribute("table", areaRepo.getAreaValues());
                return "admin/areas";
            }
            case "asc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", areaRepo.sortAreasByID());
                    case "name" -> model.addAttribute("table", areaRepo.sortAreasByName());
                    case "description" -> model.addAttribute("table", areaRepo.sortAreasByDescription());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", areaRepo.sortAreasByIDReversed());
                    case "name" -> model.addAttribute("table", areaRepo.sortAreasByNameReversed());
                    case "description" -> model.addAttribute("table", areaRepo.sortAreasByDescriptionReversed());
                }
            }
        }

        return "admin/areas";
    }
}
