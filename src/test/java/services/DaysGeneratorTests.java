package services;

import org.fis.cinemaregistrationapplication.services.DaysGenerator;
import org.fis.cinemaregistrationapplication.services.SeatGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DaysGeneratorTests {
    @Test
    public void generateTest(){
        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
        Assertions.assertEquals(list, DaysGenerator.getDays());
    }

    @Test
    public void seatGeneratorTest(){
        List<Integer>  list = new ArrayList<>();
        for(int i = 1; i <= 50; i++){
            list.add(i);
        }

        Assertions.assertTrue(list.equals(SeatGenerator.getList()));
    }
}
