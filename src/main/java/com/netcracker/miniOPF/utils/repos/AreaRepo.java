package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.area.AreaImpl;
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

    private List<Area> extractResultSet(ResultSet resultSet) throws SQLException
    {
        List<Area> areas = new ArrayList<>();
        while (resultSet.next())
        {
            Area area = new AreaImpl();

            area.setId(resultSet.getInt("area_id"));
            area.setName(resultSet.getString("area_name"));
            area.setDescription(resultSet.getString("area_description"));

            areas.add(area);
        }

        return areas;
    }

    public List<Area> sortAreasByID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM area ORDER BY area_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Area> sortAreasByName(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM area ORDER BY area_name ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Area> sortAreasByDescription(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM area ORDER BY area_description ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Area searchAreaByName(String name) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area WHERE area_name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().findFirst().orElse(null);
    }

    public List<Area> searchAreasByDescription(String description) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM area WHERE area_description=?");
        preparedStatement.setString(1, description);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Area getArea(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area WHERE area_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().findFirst().orElse(null);
    }

    public List<Area> getAreaValues() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM area");
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public void createArea(Area area) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO area VALUES((select max(area_id)+1 from area), ?, ?)");
        preparedStatement.setString(1, area.getName());
        preparedStatement.setString(2, area.getDescription());
        preparedStatement.executeUpdate();
    }

    public void updateArea(int id, Area area) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE area SET area_name=?, area_description=? WHERE area_id=?");
        preparedStatement.setString(1, area.getName());
        preparedStatement.setString(2, area.getDescription());
        preparedStatement.setInt(3, id);
        preparedStatement.executeUpdate();
    }

    public void deleteArea(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM area WHERE area_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
