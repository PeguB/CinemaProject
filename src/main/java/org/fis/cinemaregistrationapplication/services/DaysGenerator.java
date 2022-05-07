package org.fis.cinemaregistrationapplication.services;

import java.util.ArrayList;
import java.util.List;

public class DaysGenerator {

    public static List<String> getDays(){
        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
        return list;
    }

}
