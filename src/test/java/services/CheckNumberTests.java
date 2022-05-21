package services;

import org.fis.cinemaregistrationapplication.services.CheckNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckNumberTests {
    @Test
    public void checkNumberTest(){
        Assertions.assertTrue(CheckNumber.isNumeric("3"));
        Assertions.assertTrue(CheckNumber.isNumeric("3.2222"));
        Assertions.assertFalse(CheckNumber.isNumeric("aaaaaa"));
    }
}
