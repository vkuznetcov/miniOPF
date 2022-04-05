package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.area.AreaImpl;
import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.utils.storageUtils.AreaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaRepo
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
    private final AreaUtils areaUtils;

    @Autowired
    public AreaRepo(Storage storage,
                    AreaUtils areaUtils)
    {
        this.storage = storage;
        this.areaUtils = areaUtils;
    }

    public List<Area> sortAreasByID()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area ORDER BY area_id ASC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public List<Area> sortAreasByIDReversed()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area ORDER BY area_id DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public List<Area> sortAreasByName()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM area ORDER BY area_name ASC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public List<Area> sortAreasByNameReversed()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM area ORDER BY area_name DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public List<Area> sortAreasByDescription()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM area ORDER BY area_description ASC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public List<Area> sortAreasByDescriptionReversed()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM area ORDER BY area_description DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public Area searchAreaByName(String name)
    {
        Area area = null;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area WHERE area_name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return area;
    }

    public List<Area> searchAreasByDescription(String description)
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM area WHERE area_description=?");
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public Area getArea(int id)
    {
        Area area = null;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area WHERE area_id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return area;
    }

    public List<Area> getAreaValues()
    {
        List<Area> areas = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Area area = new AreaImpl();

                area.setID(resultSet.getInt("area_id"));
                area.setName(resultSet.getString("area_name"));
                area.setDescription(resultSet.getString("area_description"));

                areas.add(area);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return areas;
    }

    public void createArea(Area area)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO area VALUES((select max(area_id)+1 from area), ?, ?)");
            preparedStatement.setString(1, area.getName());
            preparedStatement.setString(2, area.getDescription());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void updateArea(int id, Area area)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE area SET area_name=?, admin_description=? WHERE area_id=?");

            preparedStatement.setString(1, area.getName());
            preparedStatement.setString(2, area.getDescription());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteArea(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM area WHERE area_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
