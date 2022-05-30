package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TemplateRepo
{

    // TODO креденшиалы базы чаще всего разные, поэтому эти строки вынести в базу
    private static final String URL = "jdbc:postgresql://localhost:5432/miniOPF";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "3961";
    private static Connection connection;

    static
    {
        /* TODO получение коннекшена нужно вынести в отдельный класс
         *   подумать про Connection Pool https://www.baeldung.com/java-connection-pooling */
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

    AreaRepo areaRepo;

    @Autowired
    public TemplateRepo(AreaRepo areaRepo)
    {
        this.areaRepo = areaRepo;
    }

    private List<Template> extractResultSet(ResultSet resultSet) throws SQLException
    {
        List<Template> templates = new ArrayList<>();
        while (resultSet.next())
        {
            Template template = new TemplateImpl();

            template.setId(resultSet.getInt("template_id"));
            template.setName(resultSet.getString("template_name"));
            template.setDescription(resultSet.getString("template_description"));
            template.setPrice(resultSet.getDouble("template_price"));
            template.setArea(areaRepo.getArea(resultSet.getInt("area_id")));

            templates.add(template);
        }
        return templates;
    }

    public List<Template> sortTemplatesByID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM template ORDER BY template_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Template> sortTemplatesByName(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM template ORDER BY template_name ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Template> sortTemplatesByDescription(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM template ORDER BY template_description ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Template> sortTemplatesByPrice(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM template ORDER BY template_price ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Template> sortTemplatesByAreaID(boolean reversed) throws SQLException
    {
        String query = "SELECT * FROM template ORDER BY area_id ";
        query += reversed ? "DESC" : "ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Template searchTemplateByName(String name) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM template WHERE template_name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().filter(i -> i.getName().equals(name)).findAny().orElse(null);
    }

    public List<Template> searchTemplatesByDescription(String description) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM template WHERE template_description=?");
        preparedStatement.setString(1, description);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Template> searchTemplatesByPrice(double price) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM template WHERE template_price=?");
        preparedStatement.setDouble(1, price);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public List<Template> searchTemplatesByArea(int areaID) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM template WHERE area_id=?");
        preparedStatement.setInt(1, areaID);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public Template getTemplate(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM template WHERE template_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet).stream().findFirst().orElse(null);
    }

    public List<Template> getTemplateValues() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM template");
        ResultSet resultSet = preparedStatement.executeQuery();
        return this.extractResultSet(resultSet);
    }

    public void updateTemplate(int id, Template template) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE template SET template_name=?, template_description=?, template_price=?, area_id=? WHERE template_id=?");
        preparedStatement.setString(1, template.getName());
        preparedStatement.setString(2, template.getDescription());
        preparedStatement.setDouble(3, template.getPrice());
        preparedStatement.setInt(4, template.getArea().getId());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    }

    public void deleteTemplate(int id) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM template WHERE template_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void createTemplate(Template template) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO template VALUES((SELECT max(template_id)+1 from template), ?, ?, ?, ?)");
        preparedStatement.setString(1, template.getName());
        preparedStatement.setString(2, template.getDescription());
        preparedStatement.setDouble(3, template.getPrice());
        preparedStatement.setInt(4, template.getArea().getId());
        preparedStatement.executeUpdate();
    }
}
