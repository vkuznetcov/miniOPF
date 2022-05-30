package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.service.Service;
import com.netcracker.miniOPF.model.service.ServiceImpl;
import com.netcracker.miniOPF.model.service.enums.ServiceStatus;
import com.netcracker.miniOPF.utils.storageUtils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    private final TemplateRepo templateRepo;


    @Autowired
    public ServiceRepo(TemplateRepo templateRepo)
    {
        this.templateRepo = templateRepo;
    }

    private List<Pair<Integer, Service>> extractResultSet(ResultSet resultSet) throws SQLException
    {
        List<Pair<Integer, Service>> services = new ArrayList<>();

        while (resultSet.next())
        {
            Service service = new ServiceImpl();

            service.setId(resultSet.getInt("service_id"));
            service.setName(resultSet.getString("service_name"));
            service.setDescription(resultSet.getString("service_description"));
            service.setPrice(resultSet.getDouble("service_price"));
            service.setStatus(ServiceStatus.valueOf(resultSet.getString("service_status")
                                                             .toUpperCase(Locale.ROOT)));
            service.setTemplate(templateRepo.getTemplate(resultSet.getInt("template_id")));

            Pair<Integer, Service> pair = new Pair<>();
            pair.setRightValue(service);
            pair.setLeftValue(resultSet.getInt("customer_id"));
            services.add(pair);
        }
        return services;
    }

    public List<Pair<Integer, Service>> sortServicesByID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY service_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> sortServicesByName(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY service_name ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);

    }

    public List<Pair<Integer, Service>> sortServicesByDescription(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY service_description ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> sortServicesByPrice(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY service_price ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> sortServicesByTemplateID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY template_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> sortServicesByCustomerID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY customer_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> sortServicesByStatus(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM service ORDER BY service_status ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> searchServicesByName(String name) throws SQLException
    {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM service WHERE service_name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> searchServicesByDescription(String description) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM service WHERE service_description=?");
        preparedStatement.setString(1, description);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> searchServicesByPrice(double price) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM service WHERE service_price=?");
        preparedStatement.setDouble(1, price);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> searchServiceByTemplateID(int templateID) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM service WHERE template_id=?");
        preparedStatement.setInt(1, templateID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> searchServicesByCustomerID(int customerID) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM service WHERE customer_id=?");
        preparedStatement.setInt(1, customerID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Pair<Integer, Service>> searchServicesByStatus(String status) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM service WHERE service_status=?");
        preparedStatement.setString(1, status);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Pair<Integer, Service> getService(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM service WHERE service_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().findFirst().orElse(null);
    }

    public List<Pair<Integer, Service>> getServiceValues() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM service");
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    private void prepareStatement(Service service, PreparedStatement preparedStatement) throws SQLException
    {
        preparedStatement.setInt(1, service.getTemplate().getId());
        preparedStatement.setInt(2, service.getTemplate().getId());
        preparedStatement.setInt(3, service.getTemplate().getId());
        preparedStatement.setString(4, service.getStatus().toString());
        preparedStatement.setInt(5, service.getTemplate().getId());
        preparedStatement.setInt(6, service.getCustomer().getId());
    }

    public void createService(Service service) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO service VALUES((select coalesce(max(order_id) + 1, 1) from \"order\"), " +
                        "(select template_name from \"template\" where template_id = ?)," +
                        " (select template_description from \"template\" where template_id = ?)," +
                        " (select template_price from \"template\" where template_id = ?), ?, ?, ?)");
        prepareStatement(service, preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void updateService(int serviceID, Service service) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE service SET service_name=(select template_name from \"template\" where template_id = ?)," +
                        " service_description=(select template_description from \"template\" where template_id = ?)," +
                        " service_price= (select template_price from \"template\" where template_id = ?)," +
                        " service_status=?," +
                        " template_id=?," +
                        " customer_id=? WHERE service_id=?");
        prepareStatement(service, preparedStatement);
        preparedStatement.setInt(7, serviceID);
        preparedStatement.executeUpdate();
    }

    public void deleteService(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM service WHERE service_id=?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }
}
