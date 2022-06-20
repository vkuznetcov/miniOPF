package com.netcracker.miniOPF.springmvc.services;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
import com.netcracker.miniOPF.utils.repos.AdminRepo;
import com.netcracker.miniOPF.utils.repos.OrderRepo;
import com.netcracker.miniOPF.utils.repos.ServiceRepo;
import com.netcracker.miniOPF.utils.repos.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class AdminService
{
    private final AdminRepo adminRepo;
    OrderRepo orderRepo;
    ServiceRepo serviceRepo;
    TemplateRepo templateRepo;

    @Autowired
    public AdminService(AdminRepo adminController,
                        OrderRepo orderController,
                        ServiceRepo serviceController,
                        TemplateRepo templateController)
    {
        this.adminRepo = adminController;
        this.orderRepo = orderController;
        this.serviceRepo = serviceController;
        this.templateRepo = templateController;
    }

    public String showAdmins(String type, String sort, String value, Model model)
    {
        /* TODO добавить возможность одновременно сортировки и поиска. Можно через sql одновременно искать и сортировать
        *   либо сначала искать через sql и потом уже сортить*/
        try
        {
            if (Objects.nonNull(value))
            {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminRepo.getAdmin(Integer.parseInt(value)));
                    case "name" -> model.addAttribute("table", adminRepo.searchAdminsByName(value));
                    case "login" -> model.addAttribute("table", adminRepo.searchAdminByLogin(value));
                    case "password" -> model.addAttribute("table", adminRepo.searchAdminsByPassword(value));
                }
                return "admin/admins";
            }
            switch (sort)
            {
                case "none" -> {
                    model.addAttribute("table", adminRepo.getAdminValues());
                    return "admin/admins";
                }
                case "asc" -> {
                    switch (type)
                    {
                        case "id" -> model.addAttribute("table", adminRepo.sortAdminsByID(false));
                        case "name" -> model.addAttribute("table", adminRepo.sortAdminsByName(false));
                        case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLogin(false));
                        case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPassword(false));
                    }
                }
                case "desc" -> {
                    switch (type)
                    {
                        case "id" -> model.addAttribute("table", adminRepo.sortAdminsByID(true));
                        case "name" -> model.addAttribute("table", adminRepo.sortAdminsByName(true));
                        case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLogin(true));
                        case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPassword(true));
                    }
                }
            }
        }
        catch(SQLException e){
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }
        return "admin/admins";
    }

    public String updateUser(Integer id, AdminImpl admin, Model model, String newPassword, String newPasswordConfirm)
    {
        try
        {
            StringBuilder errorMessage = new StringBuilder();
            Admin user = adminRepo.getAdmin(id);
            model.addAttribute("self", user);
            if (admin.getPassword().equals(""))
            {
                errorMessage.append("Enter password before confirmation");
                model.addAttribute("errorMessage", errorMessage.toString());
                return "/admin/settings";
            }
            if (!admin.getPassword().equals(user.getPassword()))
            {
                errorMessage.append("Enter valid password before confirmation");
                model.addAttribute("errorMessage", errorMessage.toString());
                return "/admin/settings";
            }
            if (!newPassword.equals(newPasswordConfirm))
            {
                errorMessage.append("Confirm new password correctly");
                model.addAttribute("errorMessage", errorMessage.toString());
                return "/admin/settings";
            }
            user.setPassword(newPassword);
            user.setName(admin.getName());
            user.setLogin(admin.getLogin());
            adminRepo.updateAdmin(user.getId(), user);
        }
        catch (SQLException e){
            model.addAttribute("errorMessage", "DataBase error: " + e.getMessage());
            e.printStackTrace();
        }

        return "/admin/settings";
    }

    public List<Admin> sortAdminsByID(boolean reversed) throws SQLException
    {
        return adminRepo.sortAdminsByID(reversed);
    }

    public List<Admin> sortAdminsByLogin(boolean reversed) throws SQLException
    {
        return adminRepo.sortAdminsByLogin(reversed);
    }

    public List<Admin> sortAdminsByName(boolean reversed) throws SQLException
    {
        return adminRepo.sortAdminsByName(reversed);
    }

    public List<Admin> sortAdminsByPassword(boolean reversed) throws SQLException
    {
        return adminRepo.sortAdminsByPassword(reversed);
    }

    public Admin searchAdminByLogin(String login) throws SQLException
    {
        return adminRepo.searchAdminByLogin(login);
    }

    public List<Admin> searchAdminsByName(String name) throws SQLException
    {
        return adminRepo.searchAdminsByName(name);
    }

    public List<Admin> searchAdminsByPassword(String password) throws SQLException
    {
        return adminRepo.searchAdminsByPassword(password);
    }

    public Admin getAdmin(int id) throws SQLException
    {
        return adminRepo.getAdmin(id);
    }

    public void deleteAdmin(int id) throws SQLException
    {
        adminRepo.deleteAdmin(id);
    }

    public List<Admin> getAdminValues() throws SQLException
    {
        return adminRepo.getAdminValues();
    }

    public void createAdmin(Admin admin) throws SQLException
    {
        adminRepo.createAdmin(admin);
    }

    public void updateAdmin(int id, Admin admin) throws SQLException
    {
        adminRepo.updateAdmin(id, admin);
    }

    //    public List<Admin> sortAdminsByID()
//    {
//        return adminRepo.sortAdminsByID();
//    }
//
//    public List<Admin> sortAdminsByIDReversed()
//    {
//        return adminRepo.sortAdminsByIDReversed();
//    }
//
//    public List<Admin> sortAdminsByLogin()
//    {
//        return adminRepo.sortAdminsByLogin();
//    }
//
//    public List<Admin> sortAdminsByLoginReversed()
//    {
//        return adminRepo.sortAdminsByLoginReversed();
//    }
//
//    public List<Admin> sortAdminsByName()
//    {
//        return adminRepo.sortAdminsByName();
//    }
//
//    public List<Admin> sortAdminsByNameReversed()
//    {
//        return adminRepo.sortAdminsByNameReversed();
//    }
//
//    public List<Admin> sortAdminsByPassword()
//    {
//        return adminRepo.sortAdminsByPassword();
//    }
//
//    public List<Admin> sortAdminsByPasswordReversed()
//    {
//        return adminRepo.sortAdminsByPasswordReversed();
//    }
//
//    public Admin searchAdminByLogin(String login)
//    {
//        return adminRepo.searchAdminByLogin(login);
//    }
//
//    public List<Admin> searchAdminsByName(String name)
//    {
//        return adminRepo.searchAdminsByName(name);
//    }
//
//    public List<Admin> searchAdminsByPassword(String password)
//    {
//        return adminRepo.searchAdminsByPassword(password);
//    }
//
//    public Admin getAdmin(int id)
//    {
//        return adminRepo.getAdmin(id);
//    }
//
//    public void deleteAdmin(int id)
//    {
//        adminRepo.deleteAdmin(id);
//    }
//
//    public List<Admin> getAdminValues()
//    {
//        return adminRepo.getAdminValues();
//    }
//
//    public void createAdmin(Admin admin)
//    {
//        adminRepo.createAdmin(admin);
//    }
//
//    public void updateAdmin(int id, Admin admin)
//    {
//        adminRepo.updateAdmin(id, admin);
//    }
}
