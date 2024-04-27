package com.wipro.vm.util;

public class InvalidOrderException extends Exception{
    @Override
    public String toString(){
        return "Invalid Order";
    }
}
