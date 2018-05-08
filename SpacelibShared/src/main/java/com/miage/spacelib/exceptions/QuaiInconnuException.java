/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.exceptions;

/**
 *
 * @author uzanl
 */
public class QuaiInconnuException extends Exception {
    
    public QuaiInconnuException(){
        
    }
    
    public QuaiInconnuException(String msg) {
        super(msg);
    }
}
