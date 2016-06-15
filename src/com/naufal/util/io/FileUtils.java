package com.naufal.util.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @author ahmadluky
 */
public class FileUtils {

    public static void writefile(String data, String f) throws IOException {
    	String NEW_LINE_SEPARATOR = "\n";
        File file = new File(f);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.append(data);
            fileWriter.append(NEW_LINE_SEPARATOR);
        } catch (Exception e) {
          System.out.println("Error in FIle !!!");
          e.printStackTrace();
        } finally {
              try {
                  fileWriter.flush();
                  fileWriter.close();
              } catch (IOException e) {
                  System.out.println("Error while flushing/closing fileWriter !!!");
                  e.printStackTrace();
              }
        }
    }

}
