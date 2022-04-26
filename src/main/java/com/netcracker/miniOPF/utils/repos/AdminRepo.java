package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepo
{
    private static final String URL = "jdbc:postgresql://localhost:5432/miniOPF";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "3961";
    private static Connection connection;

    static
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private final Storage storage;
    private final AdminUtils adminUtils;

    @Autowired
    public AdminRepo(Storage storage,
                     AdminUtils adminUtils
                    )
    {
        this.storage = storage;
        this.adminUtils = adminUtils;
    }

    public List<Admin> sortAdminsByID()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));

                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByIDReversed()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByLogin()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_login ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByLoginReversed()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_login DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByName()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_name ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByNameReversed()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_name DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByPassword()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_password ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));

                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> sortAdminsByPasswordReversed()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin ORDER BY admin_password DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public Admin searchAdminByLogin(String login)
    {
        Admin admin = null;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE admin_login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            admin = new AdminImpl();
            admin.setId(resultSet.getInt("admin_id"));
            admin.setLogin(resultSet.getString("admin_login"));
            admin.setName(resultSet.getString("admin_name"));
            admin.setPassword(resultSet.getString("admin_password"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admin;
    }

    public List<Admin> searchAdminsByName(String name)
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE admin_name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public List<Admin> searchAdminsByPassword(String password)
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM admin WHERE admin_password=?");
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
    }

    public Admin getAdmin(int id)
    {
        Admin admin = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE admin_id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

           while(resultSet.next())
           {

               admin = new AdminImpl();
               admin.setId(resultSet.getInt("admin_id"));
               admin.setLogin(resultSet.getString("admin_login"));
               admin.setName(resultSet.getString("admin_name"));
               admin.setPassword(resultSet.getString("admin_password"));
           }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return admin;
    }

    public void deleteAdmin(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM admin WHERE admin_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Admin> getAdminValues()
    {
        List<Admin> admins = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin admin = new AdminImpl();

                admin.setId(resultSet.getInt("admin_id"));
                admin.setLogin(resultSet.getString("admin_login"));
                admin.setName(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("admin_password"));
                admins.add(admin);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return admins;
//        return adminMap.values().stream().toList();
    }

    public void createAdmin(Admin admin)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO admin VALUES((select max(admin_id)+1 from admin), ?, ?, ?)");
            preparedStatement.setString(1, admin.getLogin());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getName());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateAdmin(int id, Admin admin)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE admin SET admin_name=?, admin_login=?, admin_password=? WHERE admin_id=?");

            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getLogin());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
