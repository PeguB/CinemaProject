package org.fis.cinemaregistrationapplication.services;

import java.util.ArrayList;
import java.util.List;

public class SeatGenerator {
    private static  List<Integer> list;

    public static List<Integer> getList(){
        list = new ArrayList<>();
        for(int i = 1; i <= 50; i++){
            list.add(Integer.valueOf(i));
        }
        return list;
    }
}
