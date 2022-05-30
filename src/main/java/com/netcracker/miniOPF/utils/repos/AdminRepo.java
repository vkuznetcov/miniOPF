package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
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

    private List<Admin> extractAdminsResultSet(ResultSet resultSet) throws SQLException
    {
        List<Admin> result = new ArrayList<>();
        while (resultSet.next())
        {
            Admin admin = new AdminImpl();

            admin.setId(resultSet.getInt("admin_id"));
            admin.setLogin(resultSet.getString("admin_login"));
            admin.setName(resultSet.getString("admin_name"));
            admin.setPassword(resultSet.getString("admin_password"));

            result.add(admin);
        }
        return result;
    }

    public List<Admin> sortAdminsByID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM admin ORDER BY admin_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public List<Admin> sortAdminsByLogin(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM admin ORDER BY admin_login ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public List<Admin> sortAdminsByName(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM admin ORDER BY admin_name ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public List<Admin> sortAdminsByPassword(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM admin ORDER BY admin_password ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public Admin searchAdminByLogin(String login) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE admin_login=?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet)
                   .stream()
                   .filter(i -> i.getLogin().equals(login))
                   .findAny()
                   .orElse(null);
    }

    public List<Admin> searchAdminsByName(String name) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE admin_name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public List<Admin> searchAdminsByPassword(String password) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM admin WHERE admin_password=?");
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public Admin getAdmin(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE admin_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet).stream().filter(i -> i.getId() == id).findAny().orElse(null);
    }

    public void deleteAdmin(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM admin WHERE admin_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public List<Admin> getAdminValues() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin");
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractAdminsResultSet(resultSet);
    }

    public void createAdmin(Admin admin) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO admin VALUES((select max(admin_id)+1 from admin), ?, ?, ?)");
        preparedStatement.setString(1, admin.getLogin());
        preparedStatement.setString(2, admin.getPassword());
        preparedStatement.setString(3, admin.getName());
        preparedStatement.executeUpdate();
    }

    public void updateAdmin(int id, Admin admin) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE admin SET admin_name=?, admin_login=?, admin_password=? WHERE admin_id=?");

        preparedStatement.setString(1, admin.getName());
        preparedStatement.setString(2, admin.getLogin());
        preparedStatement.setString(3, admin.getPassword());
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
    }
}
