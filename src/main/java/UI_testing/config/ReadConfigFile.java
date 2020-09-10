package UI_testing.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {
    String propertyValue = "";
    InputStream inputStream;

    public String getPropertyValue(String propertyName) {
        try {
            Properties properties = new Properties();
            String propertyFileName = "my_config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
            }
            propertyValue= properties.getProperty(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return propertyValue;
    }
}
