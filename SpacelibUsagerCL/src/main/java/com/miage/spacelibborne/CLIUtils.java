/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibborne;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mahdi
 */
public class CLIUtils {
    public static Long saisirEntier(Scanner sc, String label, ArrayList<Long> range){
        do{
            try{
                System.out.print(label);
                Long d = Long.parseLong(sc.next());
                if(!range.contains(d)){
                    throw new NumberFormatException("nombre incorrect");
                }
                return d;
            }catch(NumberFormatException ex){
                System.out.println("Erreur de saisie : " + ex.getMessage());
            }
        }while(true);
    }
    
    public static String saisirChaine(Scanner sc, String label){
        do{
            System.out.print(label);
            String c = sc.next();
            c = c == null ? null : c.trim();
            if(c == null || c.isEmpty()){
                System.out.println("Erreur de sasisie : veuillez saisir au moins un caractère.");
            }else{
                return c;
            }
        }while(true);
    }
    
        public static boolean yesNoQuestion(Scanner sc, String label){
        do{
            String s = CLIUtils.saisirChaine(sc, label).toLowerCase();
            switch(s){
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Erreur de saisie : veuillez saisir 'y' ou 'n'.");
            }
        }while(true);
    }
    
}
