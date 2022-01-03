package com.netcracker.miniOPF.storage.impl;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.area.Area;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.order.Order;
import com.netcracker.miniOPF.order.enums.OrderAction;
import com.netcracker.miniOPF.order.enums.OrderStatus;
import com.netcracker.miniOPF.service.Service;
import com.netcracker.miniOPF.service.impl.ServiceImpl;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.storage.Storage;
import com.netcracker.miniOPF.template.Template;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("storage")
public class StorageImpl implements Storage {
    private final Map<Integer, Customer> customerMap;
    private final Map<Integer, Order> orderMap;
    private final Map<Integer, Service> serviceMap;
    private final Map<Integer, Admin> adminMap;
    private final Map<Integer, Area> areaMap;
    private final Map<Integer, Template> templateMap;
    private int id;

    public CustomerSorter customerSorter;
    public CustomerSearcher customerSearcher;

    public OrderSorter orderSorter;
    public OrderSearcher orderSearcher;

    public ServiceSorter serviceSorter;
    public ServiceSearcher serviceSearcher;

    public AdminSorter adminSorter;
    public AdminSearcher adminSearcher;

    public AreaSorter areaSorter;
    public AreaSearcher areaSearcher;

    public TemplateSorter templateSorter;
    public TemplateSearcher templateSearcher;

    public StorageImpl() {
        customerMap = new HashMap<>();
        orderMap = new HashMap<>();
        serviceMap = new HashMap<>();
        adminMap = new HashMap<>();
        areaMap = new HashMap<>();
        templateMap = new HashMap<>();

        customerSorter = new CustomerSorter();
        customerSearcher = new CustomerSearcher();

        orderSorter = new OrderSorter();
        orderSearcher = new OrderSearcher();

        serviceSorter = new ServiceSorter();
        serviceSearcher = new ServiceSearcher();

        adminSorter = new AdminSorter();
        adminSearcher = new AdminSearcher();

        areaSorter = new AreaSorter();
        areaSearcher = new AreaSearcher();

        templateSorter = new TemplateSorter();
        templateSearcher = new TemplateSearcher();
    }

    private int getNextKey(int id) {
        return ++id;
    }

    @Override
    public void createCustomer(Customer customer) {
        id = getNextKey(id);
        customer.setID(id);
        customerMap.put(customer.getID(), customer);
    }

    @Override
    public Customer getCustomer(int id) {
        return customerMap.get(id);
    }

    @Override
    public void deleteCustomer(int id) {
        customerMap.remove(id);
    }

    @Override
    public void createOrder(Order order) {
        id = getNextKey(id);
        order.setID(id);
        orderMap.put(order.getID(), order);
    }

    @Override
    public Order getOrder(int id) {
        return orderMap.get(id);
    }

    @Override
    public void deleteOrder(int id) {
        orderMap.remove(id);
    }

    @Override
    public void createService(Service service) {
        id = getNextKey(id);
        service.setID(id);
        serviceMap.put(service.getID(), service);
    }

    @Override
    public Service getService(int id) {
        return serviceMap.get(id);
    }

    @Override
    public void deleteService(int id) {
        serviceMap.remove(id);
    }

    @Override
    public void createAdmin(Admin admin) {
        id = getNextKey(id);
        admin.setID(id);
        adminMap.put(admin.getID(), admin);
    }

    @Override
    public Admin getAdmin(int id) {
        return adminMap.get(id);
    }

    @Override
    public void deleteAdmin(int id) {
        adminMap.remove(id);
    }

    @Override
    public void createArea(Area area) {
        id = getNextKey(id);
        area.setID(id);
        areaMap.put(area.getID(), area);
    }

    @Override
    public Area getArea(int id) {
        return areaMap.get(id);
    }

    @Override
    public void deleteArea(int id) {
        areaMap.remove(id);
    }

    @Override
    public void createTemplate(Template template) {
        id = getNextKey(id);
        template.setID(id);
        templateMap.put(template.getID(), template);
    }

    @Override
    public Template getTemplate(int id) {
        return templateMap.get(id);
    }

    @Override
    public void deleteTemplate(int id) {
        templateMap.remove(id);
    }

    public Service createService(int templateID, int customerID) {
        Service newService = new ServiceImpl();
        newService.setCustomer(customerMap.get(customerID));
        newService.setTemplate(templateMap.get(templateID));
        return newService;
    }

    public class CustomerSorter {

        public List<Customer> sortCustomersByLogin() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getLogin().compareTo(o2.getLogin());
                }
            }).toList();
        }

        public List<Customer> sortCustomersByLoginReversed() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getLogin().compareTo(o1.getLogin());
                }
            }).toList();
        }

        public List<Customer> sortCustomersByPassword() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getPassword().compareTo(o2.getPassword());
                }
            }).toList();
        }

        public List<Customer> sortCustomersByPasswordReversed() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getPassword().compareTo(o1.getPassword());
                }
            }).toList();
        }

        public List<Customer> sortCustomersByBalance() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1.getBalance() - o2.getBalance() > 0)
                        return 1;
                    else if (o1.getBalance() == o2.getBalance())
                        return 0;
                    else return -1;
                }
            }).toList();
        }

        public List<Customer> sortCustomersByBalanceReversed() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o2.getBalance() - o1.getBalance() > 0)
                        return 1;
                    else if (o1.getBalance() == o2.getBalance())
                        return 0;
                    else return -1;
                }
            }).toList();
        }

        public List<Customer> sortCustomersByName() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).toList();
        }

        public List<Customer> sortCustomersByNameReversed() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            }).toList();
        }

        public List<Customer> sortCustomersByID() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getID() - o2.getID();
                }
            }).toList();
        }

        public List<Customer> sortCustomersByIDReversed() {
            return customerMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getID() - o1.getID();
                }
            }).toList();
        }
    }

    public class CustomerSearcher {

        public List<Customer> searchCustomerByLogin(String login) {
            List<Customer> list = new ArrayList<>();
            for (Customer cur : customerMap.values()) {
                if (cur.getLogin().equals(login))
                    list.add(cur);
            }
            return list;
        }

        public List<Customer> searchCustomerByPassword(String password) {
            List<Customer> list = new ArrayList<>();
            for (Customer cur : customerMap.values()) {
                if (cur.getPassword().equals(password))
                    list.add(cur);
            }
            return list;
        }

        public List<Customer> searchCustomerByBalance(double balance) {
            List<Customer> list = new ArrayList<>();
            for (Customer cur : customerMap.values()) {
                if (cur.getBalance() == balance)
                    list.add(cur);
            }
            return list;
        }

        public List<Customer> searchCustomerByName(String name) {
            List<Customer> list = new ArrayList<>();
            for (Customer cur : customerMap.values()) {
                if (cur.getName().equals(name))
                    list.add(cur);
            }
            return list;
        }

        public List<Customer> searchCustomerByID(int id) {
            List<Customer> list = new ArrayList<>();
            for (Customer cur : customerMap.values()) {
                if (cur.getID() == id)
                    list.add(cur);
            }
            return list;
        }
    }

    public class OrderSorter {

        public List<Order> sortOrdersByID() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getID() - o2.getID();
                }
            }).toList();
        }

        public List<Order> sortOrdersByIDReversed() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getID() - o1.getID();
                }
            }).toList();
        }

        public List<Order> sortOrdersByAdminLogin() {
            return orderMap.values().stream().sorted(new Comparator<>() {

                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getAdmin().getLogin().compareTo(o2.getAdmin().getLogin());
                }
            }).toList();
        }

        public List<Order> sortOrdersByAdminLoginReversed() {
            return orderMap.values().stream().sorted(new Comparator<>() {

                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getAdmin().getLogin().compareTo(o1.getAdmin().getLogin());
                }
            }).toList();
        }

        public List<Order> sortOrdersByServiceName() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getService().getName().compareTo(o2.getService().getName());
                }
            }).toList();
        }

        public List<Order> sortOrdersByServiceNameReversed() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getService().getName().compareTo(o1.getService().getName());
                }
            }).toList();
        }

        public List<Order> sortOrdersByStatus() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getStatus().compareTo(o2.getStatus());
                }
            }).toList();
        }

        public List<Order> sortOrdersByStatusReversed() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getStatus().compareTo(o1.getStatus());
                }
            }).toList();
        }

        public List<Order> sortOrdersByAction() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getAction().compareTo(o2.getAction());
                }
            }).toList();
        }

        public List<Order> sortOrdersByActionReversed() {
            return orderMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getAction().compareTo(o1.getAction());
                }
            }).toList();
        }
    }

    public class OrderSearcher {

        public List<Order> searchOrderByID(int id) {
            List<Order> list = new ArrayList<>();
            for (Order cur : orderMap.values()) {
                if (cur.getID() == id) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Order> searchOrderByAdmin(Admin admin) {
            List<Order> list = new ArrayList<>();
            for (Order cur : orderMap.values()) {
                if (cur.getAdmin().equals(admin)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Order> searchOrderByService(Service service) {
            List<Order> list = new ArrayList<>();
            for (Order cur : orderMap.values()) {
                if (cur.getService().equals(service)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Order> searchOrderByStatus(OrderStatus status) {
            List<Order> list = new ArrayList<>();
            for (Order cur : orderMap.values()) {
                if (cur.getStatus().equals(status)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Order> searchOrderByAction(OrderAction action) {
            List<Order> list = new ArrayList<>();
            for (Order cur : orderMap.values()) {
                if (cur.getAction().equals(action)) {
                    list.add(cur);
                }
            }
            return list;
        }
    }

    public class ServiceSorter {

        public List<Service> sortServicesByID() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o1.getID() - o2.getID();
                }
            }).toList();
        }

        public List<Service> sortServicesByIDReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o2.getID() - o1.getID();
                }
            }).toList();
        }

        public List<Service> sortServicesByName() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).toList();
        }

        public List<Service> sortServicesByNameReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            }).toList();
        }

        public List<Service> sortServicesByDescription() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o1.getDescription().compareTo(o2.getDescription());
                }
            }).toList();
        }

        public List<Service> sortServicesByDescriptionReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o2.getDescription().compareTo(o1.getDescription());
                }
            }).toList();
        }

        public List<Service> sortServicesByPrice() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    if (o1.getPrice() - o2.getPrice() > 0)
                        return 1;
                    else if (o1.getPrice() == o2.getPrice())
                        return 0;
                    else return -1;
                }
            }).toList();
        }

        public List<Service> sortServicesByPriceReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    if (o2.getPrice() - o1.getPrice() > 0)
                        return 1;
                    else if (o1.getPrice() == o2.getPrice())
                        return 0;
                    else return -1;
                }
            }).toList();
        }

        public List<Service> sortServicesByTemplateName() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o1.getTemplate().getName().compareTo(o2.getTemplate().getName());
                }
            }).toList();
        }

        public List<Service> sortServicesByTemplateNameReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o2.getTemplate().getName().compareTo(o1.getTemplate().getName());
                }
            }).toList();
        }

        public List<Service> sortServicesByCustomerLogin() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o1.getCustomer().getLogin().compareTo(o2.getCustomer().getLogin());
                }
            }).toList();
        }

        public List<Service> sortServicesByCustomerLoginReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o2.getCustomer().getLogin().compareTo(o1.getCustomer().getLogin());
                }
            }).toList();
        }

        public List<Service> sortServicesByStatus() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o1.getStatus().compareTo(o2.getStatus());
                }
            }).toList();
        }

        public List<Service> sortServicesByStatusReversed() {
            return serviceMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Service o1, Service o2) {
                    return o2.getStatus().compareTo(o1.getStatus());
                }
            }).toList();
        }
    }

    public class ServiceSearcher {

        public List<Service> searchServiceByID(int id) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getID() == id) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Service> searchServiceByName(String name) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getName().equals(name)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Service> searchServiceByDescription(String description) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getDescription().equals(description)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Service> searchServiceByPrice(double price) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getPrice() == price) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Service> searchServiceByTemplate(Template template) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getTemplate().equals(template)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Service> searchServiceByCustomer(Customer customer) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getCustomer().equals(customer)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Service> searchServiceByStatus(ServiceStatus status) {
            List<Service> list = new ArrayList<>();
            for (Service cur : serviceMap.values()) {
                if (cur.getStatus().equals(status)) {
                    list.add(cur);
                }
            }
            return list;
        }
    }

    public class AdminSorter {

        public List<Admin> sortAdminsByLogin() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o1.getLogin().compareTo(o2.getLogin());
                }
            }).toList();
        }

        public List<Admin> sortAdminsByLoginReversed() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o2.getLogin().compareTo(o1.getLogin());
                }
            }).toList();
        }

        public List<Admin> sortAdminsByPassword() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o1.getPassword().compareTo(o2.getPassword());
                }
            }).toList();
        }

        public List<Admin> sortAdminsByPasswordReversed() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o2.getPassword().compareTo(o1.getPassword());
                }
            }).toList();
        }

        public List<Admin> sortAdminsByID() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o1.getID() - o2.getID();
                }
            }).toList();
        }

        public List<Admin> sortAdminsByIDReversed() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o2.getID() - o1.getID();
                }
            }).toList();
        }

        public List<Admin> sortAdminsByName() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).toList();
        }

        public List<Admin> sortAdminsByNameReversed() {
            return adminMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Admin o1, Admin o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            }).toList();
        }
    }

    public class AdminSearcher {

        public List<Admin> searchAdminByLogin(String login) {
            List<Admin> list = new ArrayList<>();
            for (Admin cur : adminMap.values()) {
                if (cur.getLogin().equals(login)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Admin> searchAdminByPassword(String password) {
            List<Admin> list = new ArrayList<>();
            for (Admin cur : adminMap.values()) {
                if (cur.getPassword().equals(password)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Admin> searchAdminByID(int id) {
            List<Admin> list = new ArrayList<>();
            for (Admin cur : adminMap.values()) {
                if (cur.getID() == id) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Admin> searchAdminByName(String name) {
            List<Admin> list = new ArrayList<>();
            for (Admin cur : adminMap.values()) {
                if (cur.getName().equals(name)) {
                    list.add(cur);
                }
            }
            return list;
        }
    }

    public class AreaSorter {

        public List<Area> sortAreasByID() {
            return areaMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Area o1, Area o2) {
                    return o1.getID() - o2.getID();
                }
            }).toList();
        }

        public List<Area> sortAreasByIDReversed() {
            return areaMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Area o1, Area o2) {
                    return o2.getID() - o1.getID();
                }
            }).toList();
        }

        public List<Area> sortAreasByName() {
            return areaMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Area o1, Area o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).toList();
        }

        public List<Area> sortAreasByNameReversed() {
            return areaMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Area o1, Area o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            }).toList();
        }

        public List<Area> sortAreasByDescription() {
            return areaMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Area o1, Area o2) {
                    return o1.getDescription().compareTo(o2.getDescription());
                }
            }).toList();
        }

        public List<Area> sortAreasByDescriptionReversed() {
            return areaMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Area o1, Area o2) {
                    return o2.getDescription().compareTo(o1.getDescription());
                }
            }).toList();
        }
    }

    public class AreaSearcher {

        public List<Area> searchAreaByID(int id) {
            List<Area> list = new ArrayList<>();
            for (Area cur : areaMap.values()) {
                if (cur.getID() == id) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Area> searchAreaByName(String name) {
            List<Area> list = new ArrayList<>();
            for (Area cur : areaMap.values()) {
                if (cur.getName().equals(name)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Area> searchAreaByDescription(String description) {
            List<Area> list = new ArrayList<>();
            for (Area cur : areaMap.values()) {
                if (cur.getDescription().equals(description)) {
                    list.add(cur);
                }
            }
            return list;
        }
    }

    public class TemplateSorter {

        public List<Template> sortTemplatesByID() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o1.getID() - o2.getID();
                }
            }).toList();
        }

        public List<Template> sortTemplatesByIDReversed() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o2.getID() - o1.getID();
                }
            }).toList();
        }

        public List<Template> sortTemplatesByName() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            }).toList();
        }

        public List<Template> sortTemplatesByNameReversed() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            }).toList();
        }

        public List<Template> sortTemplatesByDescription() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o1.getDescription().compareTo(o2.getDescription());
                }
            }).toList();
        }

        public List<Template> sortTemplatesByDescriptionReversed() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o2.getDescription().compareTo(o1.getDescription());
                }
            }).toList();
        }

        public List<Template> sortTemplatesByPrice() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    if (o1.getPrice() - o2.getPrice() > 0)
                        return 1;
                    else if (o1.getPrice() == o2.getPrice())
                        return 0;
                    else return -1;
                }
            }).toList();
        }

        public List<Template> sortTemplatesByPriceReversed() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    if (o2.getPrice() - o1.getPrice() > 0)
                        return 1;
                    else if (o1.getPrice() == o2.getPrice())
                        return 0;
                    else return -1;
                }
            }).toList();
        }

        public List<Template> sortTemplatesByAreaName() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o1.getArea().getName().compareTo(o2.getArea().getName());
                }
            }).toList();
        }

        public List<Template> sortTemplatesByAreaNameReversed() {
            return templateMap.values().stream().sorted(new Comparator<>() {
                @Override
                public int compare(Template o1, Template o2) {
                    return o2.getArea().getName().compareTo(o1.getArea().getName());
                }
            }).toList();
        }
    }

    public class TemplateSearcher {

        public List<Template> searchTemplateByID(int id) {
            List<Template> list = new ArrayList<>();
            for (Template cur : templateMap.values()) {
                if (cur.getID() == id) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Template> searchTemplateByName(String name) {
            List<Template> list = new ArrayList<>();
            for (Template cur : templateMap.values()) {
                if (cur.getName().equals(name)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Template> searchTemplateByDescription(String description) {
            List<Template> list = new ArrayList<>();
            for (Template cur : templateMap.values()) {
                if (cur.getDescription().equals(description)) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Template> searchTemplateByPrice(double price) {
            List<Template> list = new ArrayList<>();
            for (Template cur : templateMap.values()) {
                if (cur.getPrice() == price) {
                    list.add(cur);
                }
            }
            return list;
        }

        public List<Template> searchTemplateByArea(Area area) {
            List<Template> list = new ArrayList<>();
            for (Template cur : templateMap.values()) {
                if (cur.getArea().equals(area)) {
                    list.add(cur);
                }
            }
            return list;
        }
    }
}
