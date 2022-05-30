package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.springmvc.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepo
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

    private final AdminRepo adminRepo;
    private final ServiceService serviceService;

    @Autowired
    public OrderRepo(AdminRepo adminRepo, ServiceService serviceService)
    {
        this.adminRepo = adminRepo;
        this.serviceService = serviceService;
    }

    private List<Order> extractResultSet(ResultSet resultSet) throws SQLException
    {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next())
        {
            Order order = new OrderImpl();
            order.setId(resultSet.getInt("order_id"));
            order.setAdmin(adminRepo.getAdmin(resultSet.getInt("admin_id")));
            order.setStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
            order.setAction(OrderAction.valueOf(resultSet.getString("order_action")));
            order.setService(serviceService.getService(resultSet.getInt("service_id")));

            orders.add(order);
        }
        return orders;
    }

    public List<Order> sortOrdersByID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM \"order\" ORDER BY order_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> sortOrdersByAdminID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM \"order\" ORDER BY admin_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> sortOrdersByServiceID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM \"order\" ORDER BY service_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> sortOrdersByStatus(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM \"order\" ORDER BY order_status ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);

    }

    public List<Order> sortOrdersByAction(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM \"order\" ORDER BY order_action ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> searchOrdersByAdminID(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"order\" WHERE admin_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> searchOrdersByServiceID(int serviceID) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM \"order\" WHERE service_id=?");
        preparedStatement.setInt(1, serviceID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> searchOrdersByStatus(String status) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM \"order\" WHERE order_status=?");
        preparedStatement.setString(1, status);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Order> searchOrdersByAction(String action) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM \"order\" WHERE order_action=?");
        preparedStatement.setString(1, action);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Order getOrder(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM \"order\" WHERE order_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().filter(i -> i.getId() == id).findAny().orElse(null);
    }

    public List<Order> getOrderValues() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"order\"");
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public void createOrder(Order order) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO \"order\" VALUES((select coalesce(max(order_id) + 1, 1) from \"order\"), ?, ?, ?, ?)");
        if (order.getAdmin() != null)
        {
            preparedStatement.setInt(1, order.getAdmin().getId());
        }
        else
        {
            preparedStatement.setNull(1, Types.INTEGER);
        }
        preparedStatement.setString(2, order.getStatus().toString());
        preparedStatement.setString(3, order.getAction().toString());
        preparedStatement.setInt(4, order.getService().getId());
        preparedStatement.executeUpdate();
    }

    public void updateOrder(int id, Order order) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE \"order\" SET admin_id=?, order_status=?, order_action=?, service_id=? WHERE order_id=?");
        preparedStatement.setInt(1, order.getAdmin().getId());
        preparedStatement.setString(2, order.getStatus().toString());
        preparedStatement.setString(3, order.getAction().toString());
        preparedStatement.setInt(4, order.getService().getId());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    }

    public void deleteOrder(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"order\" WHERE order_id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
