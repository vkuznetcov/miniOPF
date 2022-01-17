package com.netcracker.miniOPF.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netcracker.miniOPF.customer.Customer;
import com.netcracker.miniOPF.service.enums.ServiceStatus;
import com.netcracker.miniOPF.template.Template;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = ServiceImpl.class, name = "service")
)
public interface Service
{
    int getID();

    void setID(int id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    double getPrice();

    void setPrice(double price);

    Template getTemplate();

    void setTemplate(Template template);

    Customer getCustomer();

    void setCustomer(Customer customer);

    ServiceStatus getStatus();

    void setStatus(ServiceStatus status);
}
