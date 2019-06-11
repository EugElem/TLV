package tlvpack;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author eelem
 */
public class Saver {
    private String firstInFile="";
    private String inputFile ="e://test//8.txt";


    public String fSaver(String inputStr, String inputFile){
        this.firstInFile = inputStr;
        this.inputFile = inputFile;
        System.out.println(this.firstInFile);

        try(FileWriter writer = new FileWriter(inputFile, false))
        {
            // запись всей строки
            writer.write(firstInFile);
            writer.flush();
        }

        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return "Record complite";
    }
}

