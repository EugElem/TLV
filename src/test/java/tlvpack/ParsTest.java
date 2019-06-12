package tlvpack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ParsTest {

    @Test
    public void parser() throws IOException {

        String actual = "e://test//1";
        String expected="01000400A83292560200030004710203000B0" +
                "08E8E8E2090AEACA0E8AAA004003A000B00070084EBE0AEAAAE" +
                "AB0C000200204E0D00020000020E000200409C0B00070084E" +
                "BE0AEAAAEAB0C000200204E0D00020000020E000200409C";
        assertEquals("Содержимое файла преобразовано в НЕПРАВИЛЬНУЮ строку для парсинга" +
                "", expected, Pars.parser(actual));
    }


    @Test
    public void decoderCP() throws IOException {

            String actual = "8E8E8E2090AEACA0E8AAA0";
            String expected = "ООО РомаШка";

        assertEquals("Входящая строка не кодируется в \"cp866\" ", expected, Pars.decoderCP(actual));


    }

    }
