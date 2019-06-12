package tlvpack;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author eelem
 */
public class DateOrder {
    private static String strDate;

    public static String Dat(String tagDat) throws ParseException{
        // перевод из 16-ричого в 10-ричный
        //String strDate16 = "569232A8";
        String strDate16 = tagDat;

        BigInteger bigIntDate10 = new BigInteger(strDate16, 16);
//    System.out.println("дата заказа в bigInt: "+bigIntDate10); // BigInteger 1452403800000

        String strDate10 = String.valueOf(bigIntDate10);
//    System.out.println("дата заказа в strDate10: "+strDate10); // String 1452403800000


        long orderDate =Long.valueOf(strDate10);

        //long orderDate =1452403800000L;

        Date date = new Date(orderDate);   // date Sun Jan 10 10:30:00 YEKT 2016
        strDate = date.toString();

        return strDate;
    }
}

