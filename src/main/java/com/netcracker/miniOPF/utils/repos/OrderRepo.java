package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.order.Order;
import com.netcracker.miniOPF.model.order.OrderImpl;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.springmvc.services.ServiceService;
import com.netcracker.miniOPF.utils.storageUtils.OrderUtils;
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
        /* TODO Не очень хорошая практика отлавливать ексепшены без корректной обработки
         *   нужно либо выше прокидывать ошибку с сообщением, либо решать проблему в catch блоке*/
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private final AdminRepo adminRepo;
    private final ServiceService serviceService;

    private final Storage storage;
    private final OrderUtils orderUtils;

    @Autowired
    public OrderRepo(AdminRepo adminRepo,
                     ServiceService serviceService,
                     Storage storage, OrderUtils orderUtils)
    {
        this.adminRepo = adminRepo;
        this.serviceService = serviceService;
        this.storage = storage;
        this.orderUtils = orderUtils;
    }


    public void suspendOrder(int id)
    {
        storage.getOrder(id).setAction(OrderAction.SUSPEND);
    }

    public void resumeOrder(int id)
    {
        storage.getOrder(id).setAction(OrderAction.RESUME);
    }

    /* TODO Во всех методах, где создается сущность из resultSet эти строки одинаковые
     *   нужно вынести эти строки в метод, который на вход принимает resultSet и возвращает
     *   Order. Это сильно сократит количество кода
     */
    public List<Order> sortOrdersByID()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY order_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    /* TODO Вместо reversed методов добавить в обычный метод параметр в зависимости от которого
     *   будет обратный порядок или прямой. Например можно добавлять DESC через тернарный оператор */
    public List<Order> sortOrdersByIDReversed()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY order_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByAdminID()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY admin_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByAdminIDReversed()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY admin_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByServiceID()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY service_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByServiceIDReversed()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY service_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByStatus()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY order_status ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByStatusReversed()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY order_status DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByAction()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY order_action ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> sortOrdersByActionReversed()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" ORDER BY order_action DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> searchOrdersByAdminID(
            int id)
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"order\" WHERE admin_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> searchOrdersByServiceID(
            int serviceID)
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" WHERE service_id=?");
            preparedStatement.setInt(1, serviceID);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> searchOrdersByStatus(
            String status)
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" WHERE order_status=?");
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> searchOrdersByAction(
            String action)
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"order\" WHERE order_action=?");
            preparedStatement.setString(1, action);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrder(int id)
    {
        Order order = null;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"order\" WHERE order_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                order = new OrderImpl();
                order.setId(resultSet.getInt("order_id"));
                order.setAdmin(adminRepo.getAdmin(resultSet.getInt("admin_id")));
                order.setStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
                order.setAction(OrderAction.valueOf(resultSet.getString("order_action")));
                order.setService(serviceService.getService(resultSet.getInt("service_id")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return order;
    }

    public List<Order> getOrderValues()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"order\"");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

    public void createOrder(Order order)
    {
        try
        {
            var result = this.getOrderValues();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"order\" VALUES((select coalesce(max(order_id) + 1, 1) from \"order\"), ?, ?, ?, ?)");
            if(order.getAdmin() != null)
            {preparedStatement.setString(1, Integer.toString(order.getAdmin().getId()));}
            else preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, order.getStatus().toString());
            preparedStatement.setString(3, order.getAction().toString());
            preparedStatement.setInt(4, order.getService().getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateOrder(int id, Order order)
    {
        try
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"order\" WHERE order_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
