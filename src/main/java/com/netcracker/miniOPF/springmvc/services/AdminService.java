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

    public String showAdmins(@RequestParam(value = AdminService.FormParams.TYPE, required = false) String type,
                             @RequestParam(value = AdminService.FormParams.SORT_ORDER, required = false) String sort,
                             @RequestParam(value = AdminService.FormParams.SEARCH_VALUE, required = false) String value,
                             Model model)
    {
        /* TODO добавить возможность одновременно сортировки и поиска. Можно через sql одновременно искать и сортировать
        *   либо сначала искать через sql и потом уже сортить*/
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
                    case "id" -> model.addAttribute("table", adminRepo.sortAdminsByID());
                    case "name" -> model.addAttribute("table", adminRepo.sortAdminsByName());
                    case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLogin());
                    case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPassword());
                }
            }
            case "desc" -> {
                switch (type)
                {
                    case "id" -> model.addAttribute("table", adminRepo.sortAdminsByIDReversed());
                    case "name" -> model.addAttribute("table", adminRepo.sortAdminsByNameReversed());
                    case "login" -> model.addAttribute("table", adminRepo.sortAdminsByLoginReversed());
                    case "password" -> model.addAttribute("table", adminRepo.sortAdminsByPasswordReversed());
                }
            }
        }

        return "admin/admins";
    }

    public String updateUser(Integer id, AdminImpl admin, Model model, String newPassword, String newPasswordConfirm)
    {
        StringBuilder errorMessage = new StringBuilder("");
        Admin user = adminRepo.getAdmin(id);
        model.addAttribute("self", user);
        if(admin.getPassword().equals("")){
            errorMessage.append("Enter password before confirmation");
            model.addAttribute("errorMessage", errorMessage.toString());
            return "/admin/settings";
        }
        if(!admin.getPassword().equals(user.getPassword())){
            errorMessage.append("Enter valid password before confirmation");
            model.addAttribute("errorMessage", errorMessage.toString());
            return "/admin/settings";
        }
        if(!newPassword.equals(newPasswordConfirm)){
            errorMessage.append("Confirm new password correctly");
            model.addAttribute("errorMessage", errorMessage.toString());
            return "/admin/settings";
        }
        user.setPassword(admin.getPassword());
        user.setName(admin.getName());
        user.setLogin(admin.getLogin());
        adminRepo.updateAdmin(user.getId(), user);

        return "/admin/settings";
    }

    public List<Admin> sortAdminsByID()
    {
        return adminRepo.sortAdminsByID();
    }

    public List<Admin> sortAdminsByIDReversed()
    {
        return adminRepo.sortAdminsByIDReversed();
    }

    public List<Admin> sortAdminsByLogin()
    {
        return adminRepo.sortAdminsByLogin();
    }

    public List<Admin> sortAdminsByLoginReversed()
    {
        return adminRepo.sortAdminsByLoginReversed();
    }

    public List<Admin> sortAdminsByName()
    {
        return adminRepo.sortAdminsByName();
    }

    public List<Admin> sortAdminsByNameReversed()
    {
        return adminRepo.sortAdminsByNameReversed();
    }

    public List<Admin> sortAdminsByPassword()
    {
        return adminRepo.sortAdminsByPassword();
    }

    public List<Admin> sortAdminsByPasswordReversed()
    {
        return adminRepo.sortAdminsByPasswordReversed();
    }

    public Admin searchAdminByLogin(String login)
    {
        return adminRepo.searchAdminByLogin(login);
    }

    public List<Admin> searchAdminsByName(String name)
    {
        return adminRepo.searchAdminsByName(name);
    }

    public List<Admin> searchAdminsByPassword(String password)
    {
        return adminRepo.searchAdminsByPassword(password);
    }

    public Admin getAdmin(int id)
    {
        return adminRepo.getAdmin(id);
    }

    public void deleteAdmin(int id)
    {
        adminRepo.deleteAdmin(id);
    }

    public List<Admin> getAdminValues()
    {
        return adminRepo.getAdminValues();
    }

    public void createAdmin(Admin admin)
    {
        adminRepo.createAdmin(admin);
    }

    public void updateAdmin(int id, Admin admin)
    {
        adminRepo.updateAdmin(id, admin);
    }

    // TODO класс с константами нужно вынести в отдельный файл в interface
    public static class FormParams
    {
        public static final String TYPE = "type";
        public static final String SORT_ORDER = "sort";
        public static final String SEARCH_VALUE = "valueToSearch";
        public static final String ID = "userId";
        public static final String PASSWORD = "newPassword";
        public static final String PASSWORD_CONFIRM = "newPasswordConfirm";
    }
}
