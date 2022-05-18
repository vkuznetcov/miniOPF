package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.customer.Customer;
import com.netcracker.miniOPF.model.customer.CustomerImpl;
import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.CustomerUtils;
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
    private final Storage storage;
    private final CustomerUtils customerUtils;

    @Autowired
    public CustomerRepo(Storage storage,
                        CustomerUtils customerUtils,
                        ServiceRepo serviceRepo,
                        AreaRepo areaRepo)
    {
        this.areaRepo = areaRepo;
        this.serviceRepo = serviceRepo;
        this.storage = storage;
        this.customerUtils = customerUtils;
    }

    /* TODO Во всех методах, где создается сущность из resultSet эти строки одинаковые
    *   нужно вынести эти строки в метод, который на вход принимает resultSet и возвращает
    *   Customer. Это сильно сократит количество кода
    */
    public List<Customer> sortCustomersByLogin()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_login ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    /* TODO Вместо reversed методов добавить в обычный метод параметр в зависимости от которого
    *   будет обратный порядок или прямой. Например можно добавлять DESC через тернарный оператор */
    public List<Customer> sortCustomersByLoginReversed()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_login DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByPassword()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_password ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByPasswordReversed()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_password DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByBalance()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_balance ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByBalanceReversed()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_balance DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByName()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_name ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByNameReversed()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_name DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> sortCustomersByID()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }


    public List<Customer> sortCustomersByIDReversed()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY customer_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);


                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }
    public List<Customer> sortCustomersByArea()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY area_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }
    public List<Customer> sortCustomersByAreaReversed()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer ORDER BY area_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }
    public Customer searchCustomerByLogin(
            String login)
    {
        Customer customer = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE customer_login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customer;
    }

    public List<Customer> searchCustomersByPassword(
            String password)
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE customer_password=?");
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> searchCustomersByBalance(
            double balance)
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE customer_balance=?");
            preparedStatement.setDouble(1, balance);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> searchCustomersByName(
            String name)
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE customer_name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }
    public List<Customer> searchCustomersByArea(
            String areaName)
    {
        List<Customer> customers = new ArrayList<>();
        int areaID = areaRepo.searchAreaByName(areaName).getId();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE area_id=?");
            preparedStatement.setInt(1, areaID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customers;
    }
    public Customer getCustomer(int id)
    {
        Customer customer = null;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE customer_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customer;
    }

    public List<Customer> getCustomerValues()
    {
        List<Customer> customers = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Customer customer = new CustomerImpl();

                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setLogin(resultSet.getString("customer_login"));
                customer.setPassword(resultSet.getString("customer_password"));
                customer.setBalance(resultSet.getDouble("customer_balance"));
                customer.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
//                customer.setServices(serviceRepo.searchServicesByCustomerID(customer.getID()));
                List<com.netcracker.miniOPF.model.service.Service> services = new ArrayList<>();
                List<Pair<Integer, Service>> pairs = serviceRepo.searchServicesByCustomerID(customer.getId());
                for (int i = 0; i < pairs.size(); i++)
                {

                    com.netcracker.miniOPF.model.service.Service service = pairs.get(i).getRightValue();
                    service.setCustomer(customer);
                    services.add(service);
                }
                customer.setServices(services);
                customers.add(customer);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return customers;
    }

    public void createCustomer(Customer customer)
    {
        try
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE customer_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateCustomer(int id, Customer customer)
    {
        try
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
