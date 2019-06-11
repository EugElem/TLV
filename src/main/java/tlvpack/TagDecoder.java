package tlvpack;

/**
 *
 * @author eelem
 */
public class TagDecoder {


    private int tagNum;
    private int tagLenght;
    private String tagValue;

    public TagDecoder(){ }

    public void setTagNum(int _tagNum){
        this.tagNum = _tagNum;
    }

    public void setTagLenght(String _tagLenght){
        this.tagLenght = Integer.parseInt(_tagLenght);
    }

    public void setTagValue(String _tagValue){
        this.tagValue = _tagValue;
    }

    public String getTagNum(){
        return  String.valueOf(this.tagNum);
    }

    public String getTagLength(){
        return String.valueOf(this.tagLenght);
    }

    public String getTagValue(){
        return tagValue;
    }
}

