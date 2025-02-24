package com.wipro.vm.main;

import com.wipro.vm.entity.Inventory;
import com.wipro.vm.entity.Order;
import com.wipro.vm.service.VendingMachineService;

public class Main {
    public static void main(String[] args) {
        Inventory[] stock=new Inventory[5];
        stock[0]=new Inventory("C101","Lays Potato Chips",10,30.00);
        stock[1]=new Inventory("C102","Water",12,20.00);
        stock[2]=new Inventory("C103","Pepsi",25,15.00);
        stock[3]=new Inventory("C104","Coke",0,15.00);
        stock[4]=new Inventory("C105","DairyMilk Silk",30,45.00);
        Order.setStock(stock);

        //item to place order
        String item="Pepsi";
        int quantity=2;
        VendingMachineService vm=new VendingMachineService();
        System.out.println(vm.processOrder("OR101",item,quantity));
    }
}