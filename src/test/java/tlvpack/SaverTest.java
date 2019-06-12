package tlvpack;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class SaverTest {

    @Test
    public void fSaver() {
        JSONObject obj = new JSONObject();
        obj.put("price",20000);
        Saver s = new Saver();
        assertEquals("JSON не записан в файл", "Record complite", s.fSaver(obj,"e://test//1.json"));
    }
}