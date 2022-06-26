package com.naushad.file;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    static Logger logger =  Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String inputFilePath = "src/doc.txt";
        String outputFilePath = "src/result.txt";
        /* Printing doc file */
        String data  = FileOperations.readFile(inputFilePath);
        if(data != null){
            System.out.println(data);
        }

        /* Reading file line by line. Every line having an integer value.
           Write the output into another file where every integer is incremented by 5 and if any value is 0, skip that value
        */
        data = FileOperations.read(inputFilePath);
        System.out.println("Printing data after processing each line");
        System.out.println(data);

        /* Writing data into result file from doc file */
        try{
            FileOperations.writeIntoFile(outputFilePath,data);
        }catch (IOException ex){
            logger.severe("Exception occurred while writing data into result file " + ex.getMessage());
        }
        /* Reading output file */
        data = FileOperations.readFile(outputFilePath);
        if(data != null){
            System.out.println(data);
        }
    }
}
