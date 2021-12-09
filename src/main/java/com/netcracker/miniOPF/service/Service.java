package com.netcracker.miniOPF.service;

import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.template.Template;

public interface Service {
     int getID();
     void setID(int id);

     String getName();
     void setName(String name);

     String getDiscription();
     void setDiscription(String discription);

     double getPrice();
     void setPrice(double price);

     Template getTemplate();
     void setTemplate(Template template);

     Customer getCustomer();
     void setCustomer(Customer customer);

     ServiceStatus getStatus();
     void setStatus(ServiceStatus status);
}
