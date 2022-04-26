package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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

    public List<Area> sortAreasByID()
    {
        return areaRepo.sortAreasByID();
    }

    public List<Area> sortAreasByIDReversed()
    {
        return areaRepo.sortAreasByIDReversed();
    }

    public List<Area> sortAreasByName()
    {
        return areaRepo.sortAreasByName();
    }

    public List<Area> sortAreasByNameReversed()
    {
        return areaRepo.sortAreasByNameReversed();
    }

    public List<Area> sortAreasByDescription()
    {
        return areaRepo.sortAreasByDescription();
    }

    public List<Area> sortAreasByDescriptionReversed()
    {
        return areaRepo.sortAreasByDescriptionReversed();
    }

    public Area searchAreaByName(String name)
    {
        return areaRepo.searchAreaByName(name);
    }

    public List<Area> searchAreasByDescription(String description)
    {
        return areaRepo.searchAreasByDescription(description);
    }

    public Area getArea(int id)
    {
        return areaRepo.getArea(id);
    }

    public List<Area> getAreaValues()
    {
        return areaRepo.getAreaValues();
    }

    public void createArea(Area area)
    {
        areaRepo.createArea(area);
    }

    public void updateArea(int id, Area area)
    {
        areaRepo.updateArea(id, area);
    }

    public void deleteArea(int id)
    {
        areaRepo.deleteArea(id);
    }
}
