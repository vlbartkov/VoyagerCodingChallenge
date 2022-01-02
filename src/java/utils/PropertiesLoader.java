package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class PropertiesLoader {

    private String path = "./src/config.properties";

    public Properties getProps(){

        Properties prop = new Properties();

        InputStream input;
        try{
            input = new FileInputStream(path);
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
