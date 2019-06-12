package tlvpack;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {


    @Test
    public void printTag() {
        String strJSON= "{\"dateTime\":\"Sun Jan 18 00:27:01 YEKT 1970\",\"" +
                "orderNumber\":160004,\"items\":[{\"quantity\":2.0,\"price\":20000," +
                "\"name\":\"ДЫРокол\",\"sum\":40000},{\"quantity\":2.0,\"price\":20000," +
                "\"name\":\"ДЫРокол\",\"sum\":40000}],\"customerName\":\"ООО РомаШка\"}";

        Order o = new Order();
        assertEquals("НЕ работает функция ",strJSON, String.valueOf(o.printTag()));
    }

    @Test
    public void tagCut() {
    }

    @Test
    public void tagLength() {
    }

    @Test
    public void getNunber() {
    }
}