package tlvpack;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class DateOrderTest {

    @Test
    public void dat() throws ParseException {
        String strDate16 = "569232A8";
        assertEquals("Функция даты работаеn некорректно",
                "Sun Jan 18 00:27:01 YEKT 1970",DateOrder.Dat("569232A8"));

    }
}