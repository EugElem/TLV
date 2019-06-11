package tlvpack;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.*;

/**
 *
 * @author eelem
 */
public class Pars {

    private static String strTlv1;

    // Функция парсинга
    public static String parser(String file) throws IOException {


        try (InputStream fileIn = new FileInputStream(file)) {
            // байтовая переменная для хранения массива байтов
            byte[] buffer = new byte[fileIn.available()];
            // считаем файл в буфер
            fileIn.read(buffer, 0, fileIn.available());

            String strTlv = "";

            for (int i = 0; i < buffer.length; i += 3) {
                strTlv = new String(buffer);
            }

            System.out.println("тлв (строки):\n" + strTlv);

            String pat_1 = "\\W";
            String pat_2 = "";
            strTlv1 = strTlv.replaceAll(pat_1, pat_2);
            //System.out.println("тлв без мусора = " + strTlv1)


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------");

        return strTlv1;


    }


    public static String decoderCP(String name) throws UnsupportedEncodingException, IOException {

        if (name == null) {
            throw new RuntimeException("Invalid tlv, null or odd length");
        }

        String cp866 = "";
        String UTF8_STR="";
        String key16_str ="";
        char keyChar = ' ';
        String keyStr2 ="";

        for (int i=0; i<name.length();i+=2) {
            // переменная key получает значение каждой пары
            String key = name.substring(i, i+2);
            //System.out.print("\n!!!: "+key +"   i =" +i);

            // перевод из 16-ричного формата в 10-чный
            int key10 = Integer.parseInt(key,16);

            // Символы с номерами > 191 не меняютя, замена на аналогичные заглавные
            if (key10>191){
                key10-=80;
            }


//            System.out.println("key10 10-разр = "+key10);
            // перевод в символы  ASCII
            if (key10==00){ // замена пустого cимвола пробелом
                keyChar = ' ';
            }

            else{
                keyChar = (char)key10;
                String keyStr1 = ""+keyChar;
                String keyStr = new String(keyStr1.getBytes(), "cp866");

                // замена пробелов в конце на "-" (минус - разделитель слов)
                String pat_3 = "\\s$";
                String pat_4 = "-";
                keyStr2 = keyStr.replaceAll(pat_3,pat_4);
                //System.out.println("keyStr = "+keyStr2);

                // убрать все символы кроме "-" и цифро-букв
                String pat_5 = "^(?!-)^\\W{1}";
                String pat_6 = "";
                keyStr2 = keyStr2.replaceAll(pat_5, pat_6);

                // замена минуса на пробел
                String pat_7 = "-";
                String pat_8 = " ";
                keyStr2 = keyStr2.replaceAll(pat_7, pat_8);
            }

//            System.out.print(key+"("+key10+")"+"="+keyStr2+"\n");

            key16_str += key +" ";

            //Добавление символов в строку
            UTF8_STR+= keyStr2 +"";
//            System.out.print("\nUTF8_STR: "+UTF8_STR);

            // Перекодировка строки в Cp866
            cp866 = new String(UTF8_STR.getBytes(), "cp866");


        }
        //System.out.println("\n из цикла:_________ "+key16_str);
//        System.out.println("\n символ:___________ "+UTF8_STR);

        return UTF8_STR;
    }
}
