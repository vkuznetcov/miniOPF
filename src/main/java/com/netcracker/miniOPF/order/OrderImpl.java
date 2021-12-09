package com.netcracker.miniOPF.order;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.service.Service;

public class OrderImpl implements Order{
   private int id;
   private Admin admin;
   private Service service;
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
    public Service getService() {return service;}

    @Override
    public void setService(Service service) {this.service = service;}

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
