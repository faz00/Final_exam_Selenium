package us.piit.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class Utility {

    public static String currentDir = System.getProperty("user.dir");

    public static Properties loadProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\My Pc\\eclipse-workspace\\Final_exam_Selenium-main\\config.properties");
            properties.load(fis);
        }catch (IOException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return properties;
    }

    public static String decode(String key){
      byte[] decodedBytes = Base64.getDecoder().decode(key);
       return new String(decodedBytes);
    }

    public static void main(String[] args) {
 // String originalInput = "rezikaBareche";
//String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
 //System.out.println(encodedString);



        byte[] decodedBytes = Base64.getDecoder().decode("eWVsbG93");
       String decodedString = new String(decodedBytes);
     System.out.println(decodedString);

        }
    }

