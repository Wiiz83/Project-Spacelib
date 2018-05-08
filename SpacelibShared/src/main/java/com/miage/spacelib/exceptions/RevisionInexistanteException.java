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
public class RevisionInexistanteException extends Exception {
    
    public RevisionInexistanteException(){
        
    }
    
    public RevisionInexistanteException(String msg) {
        super(msg);
    }
}
