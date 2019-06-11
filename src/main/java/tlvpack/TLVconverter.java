package tlvpack;

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

    private static String inputStr="";  // строка для записи в файл
    private static String inputFile="e://test//1.txt"; // файла для записи
    private static  String outputStr="";
    private  static  String outputFile="";
    private static String valueTag;
    private static String strDate;
    private static String strClean;
    //static ArrayList<TagDecoder> list_TD = new ArrayList<>();


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedEncodingException, ParseException {


        // удаление всего лишнего
        strClean =Pars.parser(inputFile);
        //System.out.println("получено из парсера " + strClean+"\n");

        // разбор на отдельные теги
        Order order = new Order();
        order.setStrClean(strClean);
        order.printTag();


        //вызов класса парсинга
        valueTag = Pars.decoderCP(strClean);
//        System.out.println("получено из декодераCP :" + valueTag+"\n");


//        TabCP866.Cl3();


        /* Строка из файла TLV, разбирается на теги
         ** Каждый тег раздирается на: Имя тега, длину и значение
         ** Далее каждый набор помешается в список для хранения тегов
         */

        // Создание списка для хранения объектов
//        static ArrayList<TagDecoder> list_TD = new ArrayList<>();
/*
        // Создание элементов списка и создание в каждом экземпляра объекта
        for (int i = 0; i < a.length; i++) {

            list_TD.add(new TagDecoder(a[i]));
        }

        // цикл проверки заполнения
        for (int i = 0; i < a.length; i++) {

            System.out.println("получение из списка " + list_TD.get(i).printStrIn());
        }
*/



        /*
         ** Запись в JSON
         */





        /*
         ** Вызов функции записи в файл
         **
         */
        //Saver s = new Saver();
        // ввести строку для записи в файл и название файла для записи
        //s.fSaver(outputStr, outputFile);
    }

}
