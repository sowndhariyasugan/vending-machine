package com.wipro.vm.service;

import com.wipro.vm.entity.Inventory;
import com.wipro.vm.entity.Order;
import com.wipro.vm.util.InvalidOrderException;
import com.wipro.vm.util.ItemOutOfStockException;

import static com.wipro.vm.entity.Order.stock;

public class VendingMachineService {
    public String checkInventoryStatus(String item,int orderQuantity)
    {
        //System.out.println(item+orderQuantity+"check stat inp");
        for(Inventory i:stock)
        {
            if (i.getItemName().equals(item))
            {
                //System.out.println(i.getItemName()+i.getQuantity()+"inventory");
                if(i.getQuantity()<orderQuantity)
                {
                    try
                    {
                        throw new ItemOutOfStockException();
                    }
                    catch (ItemOutOfStockException e)
                    {
                        return e.toString();
                    }
                }
                else
                {
                    return  "Item in Stock";
                }
            }
        }
        return "Item Out of Stock";
    }
    public String validateData(String item,String orderId,int orderQuantity){
        boolean idValid=true;
        boolean quantValid=true;
        String s= "";
        if(orderId.isBlank()||(orderId.length()!=5)||!(orderId.startsWith("OR"))||!(orderId.matches(".*\\d{3}$"))){
            idValid=false;
        }
        if (orderQuantity<0||orderQuantity>10){
            quantValid=false;
        }
        if(!(idValid||quantValid)){
            try {
                throw new InvalidOrderException();
            } catch (InvalidOrderException e) {
                e.toString();
            }
        }
        else {
            if(checkInventoryStatus(item,orderQuantity).equals("Item in Stock")){
                return "Valid";
            }
            else {
                return checkInventoryStatus(item,orderQuantity);
            }
        }
        return "Invalid Order";
    }
    public String generateBill(Order order){
        //System.out.println("generating...");
        order.calculateBill();
        return "Order id:"+order.getOrderId()+"\n"+"Amount:Rs."+
                String.valueOf(order.getTotalAmount());
    }
    public String processOrder(String orderId,String item,int orderQuantity){
        System.out.println("Bill:");
        if(validateData(item,orderId,orderQuantity).equals("Valid")){
            Order order=new Order(orderId,item,orderQuantity);
            //System.out.println("Order Created");
            return (generateBill(order));
        }
        else {
           return validateData(item,orderId,orderQuantity);
        }
    }
}
