package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepo
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
        /* TODO Не очень хорошая практика отлавливать ексепшены без корректной обработки
         *   нужно либо выше прокидывать ошибку с сообщением, либо решать проблему в catch блоке*/
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private final ServiceRepo serviceRepo;
    private final AreaRepo areaRepo;

    @Autowired
    public CustomerRepo(ServiceRepo serviceRepo, AreaRepo areaRepo)
    {
        this.areaRepo = areaRepo;
        this.serviceRepo = serviceRepo;
    }

    /* TODO Во всех методах, где создается сущность из resultSet эти строки одинаковые
     *   нужно вынести эти строки в метод, который на вход принимает resultSet и возвращает
     *   Customer. Это сильно сократит количество кода
     */
    private List<Customer> extractResultSet(ResultSet resultSet) throws SQLException
    {
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next())
        {
            Customer customer = new CustomerImpl();
            customer.setId(resultSet.getInt("customer_id"));
            customer.setName(resultSet.getString("customer_name"));
            customer.setLogin(resultSet.getString("customer_login"));
            customer.setPassword(resultSet.getString("customer_password"));
            customer.setBalance(resultSet.getDouble("customer_balance"));
            customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
            List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
            List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
            for (Pair<Integer, Service> pair : pairs)
            {
                Service service = pair.getRightValue();
                service.setCustomer(customer);
                services.add(service);
            }
            customer.setServices(services);

            customers.add(customer);
        }
        return customers;
    }

    public List<Customer> sortCustomersByLogin(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM customer ORDER BY customer_login ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> sortCustomersByPassword(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM customer ORDER BY customer_password ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> sortCustomersByBalance(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM customer ORDER BY customer_balance ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> sortCustomersByName(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM customer ORDER BY customer_name ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> sortCustomersByID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM customer ORDER BY customer_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> sortCustomersByArea(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM customer ORDER BY area_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Customer searchCustomerByLogin(String login) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE customer_login=?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().findFirst().orElse(null);
    }

    public List<Customer> searchCustomersByPassword(String password) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE customer_password=?");
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> searchCustomersByBalance(double balance) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE customer_balance=?");
        preparedStatement.setDouble(1, balance);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> searchCustomersByName(String name) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE customer_name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Customer> searchCustomersByArea(String areaName) throws SQLException
    {
        int areaID = areaRepo.searchAreaByName(areaName).getId();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE area_id=?");
        preparedStatement.setInt(1, areaID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Customer getCustomer(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customer WHERE customer_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().findFirst().orElse(null);
    }

    public List<Customer> getCustomerValues() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public void createCustomer(Customer customer) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO customer VALUES((select max(customer_id)+1 from customer), ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, customer.getLogin());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setDouble(4, customer.getBalance());
        preparedStatement.setInt(5, customer.getArea().getId());
        preparedStatement.executeUpdate();
    }

    public void deleteCustomer(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE customer_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateCustomer(int id, Customer customer) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE customer SET customer_login=?, customer_password=?, customer_name=?, customer_balance=?, area_id=? WHERE customer_id=?");
        preparedStatement.setString(1, customer.getLogin());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setDouble(4, customer.getBalance());
        preparedStatement.setDouble(5, customer.getArea().getId());
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
    }
}
