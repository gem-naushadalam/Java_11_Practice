package com.naushad.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.logging.Logger;

public class FileOperations {
    static Logger logger =  Logger.getLogger(FileOperations.class.getName());

    public static String readFile(String path){
        System.out.println("Reading data from " + path + " file");
        try{
            return Files.readString(Path.of(path));
        }catch (IOException e){
            logger.severe("Exception occurred while reading " + path + " file " + e.getMessage());
        }
        return null;
    }


    public static String read(String path){
        Predicate<String> includeZero = s -> getIntValueFromString(s.trim()) == null || getIntValueFromString(s.trim()) == 0;
        Predicate<String> notIncludeZero = Predicate.not(includeZero);

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream
                     = Files.lines(Paths.get(path), StandardCharsets.UTF_8))
        {
            //Read the content with Stream and appending to string builder
            stream.filter(notIncludeZero)
                    .map(s -> getIntValueFromString(s.trim()))
                    .forEach(value -> contentBuilder.append(value+5).append("\n"));
        }
        catch (IOException e)
        {
            logger.severe("Exception occurred while reading file - " + e.getMessage());
        }

        return contentBuilder.toString();
    }

    public static Integer getIntValueFromString(String s){
        try{
            return Integer.parseInt(s);
        }catch (Exception ex){
            logger.severe("Exception occurred while parsing string value to integer - " + ex.getMessage());
        }
        return null;
    }

    public static void writeIntoFile(String path, String data)throws IOException{
        System.out.println("Writing data into  - " + path + " file");
        Files.writeString(Path.of(path),data, StandardOpenOption.APPEND);
    }
}
