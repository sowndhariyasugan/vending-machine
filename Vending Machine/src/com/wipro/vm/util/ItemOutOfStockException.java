package com.wipro.vm.util;

public class ItemOutOfStockException extends Exception{
    @Override
    public String toString(){
        return "Item Out Of Stock";
    }
}
