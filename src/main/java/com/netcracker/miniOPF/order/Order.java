package com.netcracker.miniOPF.order;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.service.Service;

public interface Order {
 int getID();
 void setID(int id);

 Admin getAdmin();
 void setAdmin(Admin admin);

 Service getService();
 void  setService(Service service);

 OrderAction getAction();
 void  setAction(int key);

 OrderStatus getStatus();
 void setStatus(int key);
}