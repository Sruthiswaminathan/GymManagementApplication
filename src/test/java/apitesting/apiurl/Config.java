package apitesting.apiurl;

import java.io.FileInputStream;
import java.util.Properties;

// Config.java
public class Config {
    public static final String BASE_URL = "https://6yu6qqmef6.execute-api.ap-southeast-1.amazonaws.com/dev";
    public static Properties loadConfig() throws Exception {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("src\\test\\resources\\apitesting\\config.properties");
        props.load(in);
        in.close();
        return props;

    }
}
