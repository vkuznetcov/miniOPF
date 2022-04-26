package com.netcracker.miniOPF.model.order;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.order.enums.OrderAction;
import com.netcracker.miniOPF.model.order.enums.OrderStatus;
import com.netcracker.miniOPF.model.service.Service;

public interface Order
{
    int getId();

    void setId(int id);

    Admin getAdmin();

    void setAdmin(Admin admin);

    Service getService();

    void setService(Service service);

    OrderAction getAction();

    void setAction(OrderAction action);

    OrderStatus getStatus();

    void setStatus(OrderStatus status);
}
