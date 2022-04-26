package com.netcracker.miniOPF.utils.repos;

import com.netcracker.miniOPF.model.storage.Storage;
import com.netcracker.miniOPF.model.template.Template;
import com.netcracker.miniOPF.model.template.TemplateImpl;
import com.netcracker.miniOPF.utils.storageUtils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TemplateRepo
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
    private final TemplateUtils templateUtils;
    AreaRepo areaRepo;

    @Autowired
    public TemplateRepo(Storage storage,
                        TemplateUtils templateUtils,
                        AreaRepo areaRepo)
    {
        this.areaRepo = areaRepo;
        this.storage = storage;
        this.templateUtils = templateUtils;
    }

    public List<Template> sortTemplatesByID()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByIDReversed()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByName()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_name ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByNameReversed()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_name DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByDescription()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_description ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByDescriptionReversed()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_description DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByPrice()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_price ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByPriceReversed()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY template_price DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByAreaID()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY area_id ASC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> sortTemplatesByAreaIDReversed()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template ORDER BY area_id DESC");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public Template searchTemplateByName(
            String name)
    {
        Template template = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template WHERE template_name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                template = new TemplateImpl();

                template.setId(resultSet.getInt("template_id"));
                template.setName(resultSet.getString("template_name"));
                template.setDescription(resultSet.getString("template_description"));
                template.setPrice(resultSet.getDouble("template_price"));
                template.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return template;
    }

    public List<Template> searchTemplatesByDescription(
            String description)
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template WHERE template_description=?");
            preparedStatement.setString(1, description);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> searchTemplatesByPrice(
            double price)
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template WHERE template_price=?");
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public List<Template> searchTemplatesByArea(
            int areaID)
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM template WHERE area_id=?");
            preparedStatement.setInt(1, areaID);
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public Template getTemplate(int id)
    {
        Template template = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM template WHERE template_id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                template = new TemplateImpl();

                template.setId(resultSet.getInt("template_id"));
                template.setName(resultSet.getString("template_name"));
                template.setDescription(resultSet.getString("template_description"));
                template.setPrice(resultSet.getDouble("template_price"));
                template.setArea(areaRepo.getArea(resultSet.getInt("area_id")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return template;
    }

    public List<Template> getTemplateValues()
    {
        List<Template> templates = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM template");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return templates;
    }

    public void updateTemplate(int id, Template template)
    {
        try
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteTemplate(int id)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM template WHERE template_id=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void createTemplate(Template template){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO template VALUES((SELECT max(template_id)+1 from template), ?, ?, ?, ?)");
            preparedStatement.setString(1, template.getName());
            preparedStatement.setString(2, template.getDescription());
            preparedStatement.setDouble(3, template.getPrice());
            preparedStatement.setInt(4, template.getArea().getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
