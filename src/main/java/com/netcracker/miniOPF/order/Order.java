package com.netcracker.miniOPF.order;

import com.netcracker.miniOPF.admin.Admin;
import com.netcracker.miniOPF.service.ServiceInt;

public interface Order {
 int getID();
 void setID(int id);

 Admin getAdmin();
 void setAdmin(Admin admin);

 ServiceInt getService();
 void  setService(ServiceInt serviceInt);

 OrderAction getAction();
 void  setAction(int key);

 OrderStatus getStatus();
 void setStatus(int key);
}
