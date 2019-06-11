package tlvpack;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;


/**
 *
 * @author eelem
 */
public class TLVconverter {

    private static String inputFile="e://test//1"; // файла для записи
    private static String outputFile="e://test//1.json";
    private static String strClean;



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedEncodingException, ParseException {

        //
        if (args.length !=0) {
            inputFile = args[0];
            outputFile = args[1];
        }


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
