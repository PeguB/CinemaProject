package org.fis.cinemaregistrationapplication.services;

public class SingletonUsername {

    private static String USSERNAME;

    private SingletonUsername(){}

    public static String getUSSERNAME(){
        return USSERNAME;
    }
    public static void setUSSERNAME(String ussername){
        USSERNAME = ussername;
    }
}
