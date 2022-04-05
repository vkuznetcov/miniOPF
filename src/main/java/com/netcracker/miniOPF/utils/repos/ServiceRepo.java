package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import com.netcracker.miniOPF.utils.storageUtils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class ServiceRepo
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

//    private final CustomerRepo customerRepo;
    private final TemplateRepo templateRepo;
    private Storage storage;
    private ServiceUtils serviceUtils;

    @Autowired
    public ServiceRepo(Storage storage,
                       ServiceUtils serviceUtils,
//                       CustomerRepo customerRepo,
                       TemplateRepo templateRepo
                      )
    {
        this.storage = storage;
        this.serviceUtils = serviceUtils;
//        this.customerRepo = customerRepo;
        this.templateRepo = templateRepo;
    }

    public void suspendService(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE service SET service_status='SUSPENDED' WHERE service_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void resumeService(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE service SET service_status='ACTIVE' WHERE service_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void disconnectService(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE service SET service_status='DISCONNECTED' WHERE service_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void connectService(int templateID, int customerID)
    {

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO service VALUES((SELECT MAX(service_id)+1 FROM service)," +
                            " (SELECT template_name from template WHERE template_id=?)," +
                            " (SELECT template_description from template WHERE template_id=?)," +
                            " (SELECT template_price from template WHERE template_id=?), 'CONNECTED', ?)");

            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "INSERT INTO customer_service VALUES(?," +
                            " (SELECT MAX(service_id) FROM service))");

            preparedStatement.setInt(1, templateID);
            preparedStatement.setInt(2, templateID);
            preparedStatement.setInt(3, templateID);
            preparedStatement.setInt(4, templateID);

            preparedStatement1.setInt(1, customerID);


            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Pair<Integer, Service>> sortServicesByID()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setRightValue(service);
                pair.setLeftValue(resultSet.getInt("customer_id"));
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer,Service>> sortServicesByIDReversed()
    {


        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByName()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_name ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByNameReversed()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_name DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByDescription()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_description ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByDescriptionReversed()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_description DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByPrice()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_price ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByPriceReversed()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_price DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByTemplateID()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY template_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByTemplateIDReversed()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY template_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByCustomerID()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY customer_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByCustomerIDReversed()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY customer_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByStatus()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_status ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByStatusReversed()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service ORDER BY service_status DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> searchServicesByName(
            String name)
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service WHERE service_name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> searchServicesByDescription(
            String description)
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service WHERE service_description=?");
            preparedStatement.setString(1, description);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> searchServicesByPrice(
            double price)
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service WHERE service_price=?");
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> searchServiceByTemplateID(int templateID)
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service WHERE template_id=?");
            preparedStatement.setInt(1, templateID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> searchServicesByCustomerID(
            int customerID)
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service WHERE customer_id=?");
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public List<Pair<Integer, Service>> searchServicesByStatus(
            String status)
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM service WHERE service_status=?");
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public Pair<Integer, Service> getService(int id)
    {
        Pair<Integer, Service> pair = null;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM service WHERE service_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return pair;
    }

    public List<Pair<Integer, Service>> getServiceValues()
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM service");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Service service = new ServiceImpl();

                service.setID(resultSet.getInt("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setDescription(resultSet.getString("service_description"));
                service.setPrice(resultSet.getDouble("service_price"));
                service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                                 .toUpperCase(Locale.ROOT)));
//                service.setCustomer(customerRepo.getCustomer(resultSet.getInt("customer_id")));
                service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

                Pair<Integer, Service> pair = new Pair<>();
                pair.setLeftValue(resultSet.getInt("customer_id"));
                pair.setRightValue(service);
                services.add(pair);            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return services;
    }

    public void createService(Service service){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO service VALUES((select max(service_id)+1 from service), ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setDouble(3, service.getPrice());
            preparedStatement.setString(4, service.getStatus().toString());
            preparedStatement.setInt(5, service.getTemplate().getID());
            preparedStatement.setInt(6, service.getCustomer().getID());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void updateService(int serviceID, Service service)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE service SET service_name=?, service_description=?, service_price=?, service_status=?, template_id=?, customer_id=? WHERE service_id=?");
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setDouble(3, service.getPrice());
            preparedStatement.setString(4, service.getStatus().toString());
            preparedStatement.setInt(5, service.getTemplate().getID());
            preparedStatement.setInt(6, service.getCustomer().getID());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void deleteService(int id){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM service WHERE service_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
