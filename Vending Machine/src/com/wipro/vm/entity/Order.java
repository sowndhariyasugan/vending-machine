package com.wipro.vm.entity;
import com.wipro.vm.util.*;
public class Order {

    public static Inventory[] stock;
    String orderId;
    String item;
    int orderQuantity;
    double totalAmount;

    public Order(String orderId, String item, int orderQuantity) {
        this.orderId = orderId;
        this.item = item;
        this.orderQuantity = orderQuantity;
    }

    public static Inventory[] getStock() {
        return stock;
    }

    public static void setStock(Inventory[] stock) {
        Order.stock = stock;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void calculateBill(){
        //System.out.println("Calculating...");
        for(Inventory i:stock){
            if (i.itemName.equals(item)){
                this.totalAmount=i.price*orderQuantity;
                try {
                    updateStock(i.itemId,orderQuantity);
                } catch (ItemOutOfStockException e) {
                    e.toString();
                }
            }
        }

    }

    public void updateStock(String itemId,int quantity)throws ItemOutOfStockException{
        for(Inventory i:stock){
            if (i.itemId.equals(itemId)){
                 if(i.quantity<quantity){
                     throw new ItemOutOfStockException();
                 }
                 else{
                     i.quantity-=quantity;
                 }
            }
        }
    }

}
