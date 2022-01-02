package com.netcracker.miniOPF.jsonHanlder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.admin.impl.AdminImpl;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.area.impl.AreaImpl;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.customer.impl.CustomerImpl;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.impl.OrderImpl;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.impl.ServiceImpl;
import com.netcracker.miniOPF.template.Template;
import com.netcracker.miniOPF.template.impl.TemplateImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonHandler {

    public static <T> void serializeJson(T value) throws IOException {
        try {
            String path = null;
            if (value instanceof Admin)
                path = "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/AdminDB.txt";
            else if (value instanceof Area)
                path = "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/AreaDB.txt";
            else if (value instanceof Customer)
                path = "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/CustomerDB.txt";
            else if (value instanceof Order)
                path = "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/OrderDB.txt";
            else if (value instanceof Service)
                path = "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/ServiceDB.txt";
            else if (value instanceof Template)
                path = "src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/TemplateDB.txt";

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(value);

            assert path != null;
            Writer out = new FileWriter(path, true);
            out.write(json);
            out.write("\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Admin> deserializeAdmin() throws IOException {
        List<Admin> forReturn = new ArrayList<>();
        try (Reader in = new FileReader("src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/AdminDB.txt");
             Scanner scanner = new Scanner(in)){

            while(scanner.hasNext()) {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, AdminImpl.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return forReturn;
        }
    }

    public static List<Area> deserializeArea() {
        List<Area> forReturn = new ArrayList<>();
        try (Reader in = new FileReader("src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/AreaDB.txt");
             Scanner scanner = new Scanner(in)){

            while(scanner.hasNext()) {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, AreaImpl.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return forReturn;
        }
    }

    public static List<Customer> deserializeCustomer() {
        List<Customer> forReturn = new ArrayList<>();
        try (Reader in = new FileReader("src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/CustomerDB.txt");
             Scanner scanner = new Scanner(in)){

            while(scanner.hasNext()) {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, CustomerImpl.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return forReturn;
        }
    }

    public static List<Order> deserializeOrder() {
        List<Order> forReturn = new ArrayList<>();
        try (Reader in = new FileReader("src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/OrderDB.txt");
             Scanner scanner = new Scanner(in)){

            while(scanner.hasNext()) {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, OrderImpl.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return forReturn;
        }
    }

    public static List<Service> deserializeService() {
        List<Service> forReturn = new ArrayList<>();
        try (Reader in = new FileReader("src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/ServiceDB.txt");
             Scanner scanner = new Scanner(in)){

            while(scanner.hasNext()) {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, ServiceImpl.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return forReturn;
        }
    }

    public static List<Template> deserializeTemplate() {
        List<Template> forReturn = new ArrayList<>();
        try (Reader in = new FileReader("src/main/java/com/netcracker/miniOPF/jsonHanlder/dataBase/TemplateDB.txt");
             Scanner scanner = new Scanner(in)){

            while(scanner.hasNext()) {
                String json = scanner.nextLine();

                forReturn.add(new ObjectMapper().readValue(json, TemplateImpl.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return forReturn;
        }
    }
}
