package com.netcracker.miniOPF.order;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.service.ServiceInt;

public class OrderImpl implements Order{
   private int id;
   private Admin admin;
   private ServiceInt serviceInt;
   private OrderStatus status;
   private OrderAction action;

    @Override
    public int getID() {return id;}

    @Override
    public void setID(int id) {this.id = id;}

    @Override
    public Admin getAdmin() {return admin;}

    @Override
    public void setAdmin(Admin admin) {this.admin = admin;}

    @Override
    public ServiceInt getService() {return serviceInt;}

    @Override
    public void setService(ServiceInt serviceInt) {this.serviceInt = serviceInt;}

    @Override
    public OrderAction getAction() {return action;}

    @Override
    public void setAction(int key) {
        OrderAction[] action = OrderAction.values();
        this.action = action[key];}

    @Override
    public OrderStatus getStatus() {return status;}

    @Override
    public void setStatus(int key) {
        OrderStatus[] statuses = OrderStatus.values();
        this.status = statuses[key];}
}
