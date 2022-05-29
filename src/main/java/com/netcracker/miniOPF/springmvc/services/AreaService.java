package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.utils.repos.AreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
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

    public String showAreas(String type, String sort, String value, Model model)
    {
        try
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
                        case "id" -> model.addAttribute("table", areaRepo.sortAreasByID(false));
                        case "name" -> model.addAttribute("table", areaRepo.sortAreasByName(false));
                        case "description" -> model.addAttribute("table", areaRepo.sortAreasByDescription(false));
                    }
                }
                case "desc" -> {
                    switch (type)
                    {
                        case "id" -> model.addAttribute("table", areaRepo.sortAreasByID(true));
                        case "name" -> model.addAttribute("table", areaRepo.sortAreasByName(true));
                        case "description" -> model.addAttribute("table", areaRepo.sortAreasByDescription(true));
                    }
                }
            }
        }
        catch(SQLException e){
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }

        return "admin/areas";
    }

    public List<Area> sortAreasByID(boolean reversed) throws SQLException
    {
        return areaRepo.sortAreasByID(reversed);
    }

    public List<Area> sortAreasByName(boolean reversed) throws SQLException
    {
        return areaRepo.sortAreasByName(reversed);
    }

    public List<Area> sortAreasByDescription(boolean reversed) throws SQLException
    {
        return areaRepo.sortAreasByDescription(reversed);
    }

    public Area searchAreaByName(String name) throws SQLException
    {
        return areaRepo.searchAreaByName(name);
    }

    public List<Area> searchAreasByDescription(String description) throws SQLException
    {
        return areaRepo.searchAreasByDescription(description);
    }

    public Area getArea(int id) throws SQLException
    {
        return areaRepo.getArea(id);
    }

    public List<Area> getAreaValues() throws SQLException
    {
        return areaRepo.getAreaValues();
    }

    public void createArea(Area area) throws SQLException
    {
        areaRepo.createArea(area);
    }

    public void updateArea(int id, Area area) throws SQLException
    {
        areaRepo.updateArea(id, area);
    }

    public void deleteArea(int id) throws SQLException
    {
        areaRepo.deleteArea(id);
    }
}
