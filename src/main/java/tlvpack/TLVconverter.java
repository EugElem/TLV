package tlvpack;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;


import java.io.*;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;



/**
 *
 * @author eelem
 */
public class TLVconverter {

    private static String inputFile="e://test//1.txt"; // файла для записи
    private static String outputFile="";
    private static String valueTag;
    private static String strDate;
    private static String strClean;
    //static ArrayList<TagDecoder> list_TD = new ArrayList<>();



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedEncodingException, ParseException {


        inputFile = args[0];
        outputFile = args[1];

        // удаление всего лишнего
        strClean = Pars.parser(inputFile);
        //System.out.println("получено из парсера " + strClean+"\n");

        // разбор на отдельные теги
        Order order = new Order();
        order.setStrClean(strClean);
        JSONObject obj = order.printTag();

        /*
         ** Запись JSON в файл
         */
        Saver s = new Saver();
        s.fSaver(obj, outputFile);
    }
}
