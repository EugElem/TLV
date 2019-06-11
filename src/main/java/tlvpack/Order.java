package tlvpack;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author eelem
 */
public class Order {
    private ArrayList<TagDecoder> list_TD = new ArrayList<>();

    private String   tag_1; // UNIX time
    private String tag_2;   // max 8
    private String tag_3;   // max 1000
    private float  tag_4;   // TLV, м.б. больше 1 вложенного TLV

    private String tag_11;  // max 200
    private int tag_12;     // max 6 (в копейках)
    private long tag_13;    // max 8
    private int tag_14;     // max 6 (в копейках)

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


    public void printTag(){


        int j=0;
        int jj=0;
        // Создание элементов списка и создание в каждом экземпляра объектаfor (int i = 0; i < a.length; i++) {

        //цикл - пока не кончится сторока разбора
        while (this.strClean.length()>0) {
//            System.out.println("\nj="+j);

            // получение номера тега
            l_tag =4;
            this.strClean = tagCut(this.strClean);

            int tag_number = Integer.parseInt(tag,16);

            // создание объекта с дальнейшим занесением значений. (здесь - номер тега)
            list_TD.add(j, new TagDecoder());
            list_TD.get(j).setTagNum(tag_number);
//            System.out.println(list_TD.get(j).getTagNum());



            // получение длины тега
            // обращение к функции экземпляра класса из списка таких экземпляров
            tagLength(j);
            list_TD.get(j).setTagLenght(String.valueOf(l_tag));
//            System.out.println("длина: "+l_tag);



            // получение значения
            try {
                String a = tagDev(tag_number, j);
                list_TD.get(j).setTagValue(a);

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            j++;


            // Ниже проверочный код, потом можно закомментировать или удалить
            int numObj = (list_TD.size()-1);
            String n = list_TD.get(numObj).getTagNum();
            String l = list_TD.get(numObj).getTagLength();
            String v = list_TD.get(numObj).getTagValue();
            System.out.println(" содержимое тега (из объекта №"+numObj + "  тег№:"+n+ "  длина тега:"+l+"  значение:"+v);

        }
        System.out.println("JSON:");
        System.out.println("dateTime: "+obj.get("dateTime"));
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
//        System.out.println("tag: "+tag + " ");

//        System.out.println("проверка строки  из tagCut: "+strForCut);
        return strForCut;
    }



    // функция получения длины в 10-ном виде
    protected int tagLength (int j){

        this.strClean = tagCut(this.strClean);
        int tagInt = Integer.parseInt(tag,16);
        tag = String.valueOf(tagInt);
        l_tag = Integer.parseInt(tag) * 2;
//        System.out.println("длина: "+l_tag);
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
                int s = (list_TD.size()-1);
                // получение строки с именем заказчика в 16-ричном формате
                l_tag=4;
                tagCut(this.strClean);

                // проверка на наличие тега 11
                if (!tag.equals("000B") ){
                    tagVal = "Вложенных TLV нет";
                    list_TD.get(s).setTagValue(tagVal);
                    //                   System.out.println(tagVal);
                }

                else {
                    //System.out.println("Вложенные TLV !!! объект № " + s + "   строка для анализа в объекте: "+ this.strClean);
                    tagVal = "Вложенные структуры";
                    list_TD.get(s).setTagValue(tagVal);

                    // создание массива объектов, в котором  будут объекты item
                    JSONArray ar=new JSONArray();
                    JSONObject objArr;

                    //obj.put("items",tagVal_1); // поместить после цикла и  добавить в него массив ar в который добавить список
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

                break;
            }

            default:
                System.out.println("default");

        }
        return tagVal;
    }
}


// а862тк159
// а862тк159