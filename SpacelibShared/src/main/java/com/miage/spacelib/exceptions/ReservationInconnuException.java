package com.miage.spacelib.exceptions;

public class ReservationInconnuException extends Exception {
    
    public ReservationInconnuException(){
        
    }
    
    public ReservationInconnuException(String msg) {
        super(msg);
    }
}
