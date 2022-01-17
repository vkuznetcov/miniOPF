package com.netcracker.miniOPF.customer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.netcracker.miniOPF.service.Service;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = CustomerImpl.class, name = "customer")
)
public interface Customer
{
    String getLogin();

    void setLogin(String login);

    int getID();

    void setID(int id);

    String getPassword();

    void setPassword(String password);

    double getBalance();

    void setBalance(double balance);

    String getName();

    void setName(String name);

    List<Service> getServices();

    void setServices(List<Service> services);

    void addService(Service service);

    void removeService(int id);
}
