package com.miage.spacelib.utiles;

import java.util.Calendar;

public class DateComparaison {
    
    public static final String estDate1 = "estDate1";
    public static final String estDate2 = "estDate2";
    public static final String estAucun = "estAucun";
    
    public static Calendar DateLaPlusTard(Calendar date1, Calendar date2) {
        Calendar datePlusTard = null;
        if (date1.compareTo(date2) > 0) {
            datePlusTard = date1;
        } else if (date1.compareTo(date2) < 0) {
            datePlusTard = date2;
        } else if (date1.compareTo(date2) == 0) {
            datePlusTard = date1;
        }
        return datePlusTard;
    }
    
    public static Calendar DateLaPlusTot(Calendar date1, Calendar date2) {
        Calendar datePlusTot = null;
        if (date1.compareTo(date2) > 0) {
            datePlusTot = date2;
        } else if (date1.compareTo(date2) < 0) {
            datePlusTot = date1;
        } else if (date1.compareTo(date2) == 0) {
            datePlusTot = date1;
        }
        return datePlusTot;
    }
    
    
    public static String ReservationLaPlusTot(Calendar date1, Calendar date2) {
        String datePlusTot = estAucun;
        if (date1.compareTo(date2) > 0) {
            datePlusTot = estDate2;
        } else if (date1.compareTo(date2) < 0) {
            datePlusTot = estDate1;
        } else if (date1.compareTo(date2) == 0) {
            datePlusTot = estDate1;
        }
        return datePlusTot;
    }
    
    public static String ReservationLaPlusTard(Calendar date1, Calendar date2) {
        String datePlusTard = estAucun;
        if (date1.compareTo(date2) > 0) {
            datePlusTard = estDate1;
        } else if (date1.compareTo(date2) < 0) {
            datePlusTard = estDate2;
        } else if (date1.compareTo(date2) == 0) {
            datePlusTard = estDate1;
        }
        return datePlusTard;
    }
    
    
   
    
    
}
