package tlvpack;

import java.io.IOException;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author eelem
 */
public class Order {

    // уменьшаемая строка для парсинга
    private String strClean;

    private int tagInt=0;
    String tag = "";
    int l_tag = 4;

    public Order(){}

    public void setStrClean(String strClean){
        this.strClean = strClean;
    }
    JSONObject obj=new JSONObject();
    JSONObject obj_2;
    JSONArray ar=new JSONArray();
    JSONObject objArr;


    public JSONObject printTag(){


        int j=0;

        //цикл - пока не кончится сторока разбора
        while (this.strClean.length()>0) {

            // получение номера тега
            l_tag =4;
            this.strClean = tagCut(this.strClean);

            int tag_number = Integer.parseInt(tag,16);

            // получение длины тега
            tagLength(j);
            //System.out.println("длина: "+l_tag);



            // получение значения
            try {
                String a = tagDev(tag_number, j);

            } catch (ParseException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            j++;
        }

        obj.put("items",ar);

        System.out.println("\n"+obj);
        return obj;
    }



    // функция выделения из строки тегов и длины и значений в 16-ричном виде
    protected String tagCut(String strForCut) {
        StringBuffer strBuffer = new StringBuffer(strForCut);
        String temp = "";
        tag = "";
        int i = 0;

        for (i = 0; i < l_tag; i += 2) {
            temp = strBuffer.substring(i, i + 2);
            temp += tag;
            tag = temp;
        }
        // убрать из строки обработанные данные
        strBuffer.delete(0, i);
        strForCut = strBuffer.toString();

        return strForCut;
    }



    // функция получения длины в 10-ном виде
    protected int tagLength (int j){

        this.strClean = tagCut(this.strClean);
        int tagInt = Integer.parseInt(tag,16);
        tag = String.valueOf(tagInt);
        l_tag = Integer.parseInt(tag) * 2;
        //System.out.println("длина: "+l_tag);
        return  l_tag;
    }

    protected String getNunber(){
        // получение строки с номером заказа в 16-ричном формате
        this.strClean = tagCut(this.strClean);
        int tagInt = Integer.parseInt(tag,16);
        String tagVal = String.valueOf(tagInt);
        return tagVal;
    }


    //  функция выбора типа значения но тегу
    private String tagDev(int tag_number, int j) throws ParseException, IOException {
        tagInt = tag_number;
        String tagVal="";

        switch (tagInt){
            case 1:{
                // получение строки с датой в 16-ричном формате
                this.strClean = tagCut(this.strClean);
                // получение даты
                tagVal = DateOrder.Dat(tag);
                // добавление в json
                obj.put("dateTime",tagVal);
                break;
            }
            case 2:{
                // получение строки с номером заказа в 16-ричном формате
                tagVal = getNunber();
                int tagVal_1 = Integer.parseInt(tagVal);
                obj.put("orderNumber",tagVal_1);
                break;
            }

            case 3:{
                // получение строки с именем заказчика в 16-ричном формате
                this.strClean = tagCut(this.strClean);
                // переворачивает строку обратно
                tagCut(tag);
                tagVal = Pars.decoderCP(tag);
                obj.put("customerName",tagVal);
                break;

            }
            case 4:{
                // получение строки с именем заказчика в 16-ричном формате
                l_tag=4;
                tagCut(this.strClean);

                // проверка на наличие тега 11
                if (!tag.equals("000B") ){
                    tagVal = "Вложенных TLV нет";
                }

                else {
                    tagVal = "Вложенные структуры";

                }
                break;

            }
            case 11: {
                // получение name
                this.strClean = tagCut(this.strClean);
                // переворачивает строку обратно
                tagCut(tag);
                tagVal = Pars.decoderCP(tag);

                // создание нового JSON объекта
                obj_2=new JSONObject();
                // добавление пары ключ:значение
                obj_2.put("name",tagVal);
                break;
            }

            case 12: {
                // получение price
                tagVal = getNunber();
                int tagVal_1 = Integer.parseInt(tagVal);

                // добавление пары ключ:значение
                obj_2.put("price",new  Integer(tagVal_1));
                break;
            }

            case 13: {
                // получение quantity
                this.strClean = tagCut(this.strClean);
                tagCut(tag);
                int tagInt = Integer.parseInt(tag,16);
                tagVal = String.valueOf(tagInt);
                float tagVal_1 = Float.parseFloat(tag);

                // добавление пары ключ:значение
                obj_2.put("quantity",new  Float(tagVal_1));
                break;
            }

            case 14: {
                // получение sum
                tagVal = getNunber();
                int tagVal_1 = Integer.parseInt(tagVal);

                // добавление пары ключ:значение
                obj_2.put("sum",new  Integer(tagVal_1));

                // запись в массив объектов
                objArr=new JSONObject();
                ar.add(obj_2);
                break;
            }

            default:
                System.out.println("default");
        }
        return tagVal;
    }
}