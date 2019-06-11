package tlvpack;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author eelem
 */
public class Saver {
    private JSONObject firstInFile;
    private String inputFile;


    public String fSaver(JSONObject inputStr, String _inputFile){
        this.firstInFile = inputStr;
        this.inputFile = _inputFile;
        //System.out.println(this.firstInFile);

        try(FileWriter writer = new FileWriter(inputFile, false))
        {
            // запись всей строки
            writer.write(String.valueOf(firstInFile));
            writer.flush();
        }

        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return "Record complite";
    }
}

