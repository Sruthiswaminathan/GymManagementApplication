package apitesting.apiurl;

import java.io.FileInputStream;
import java.util.Properties;

// Config1.java
public class Config {
    public static final String BASE_URL = "https://muxq6k6cs8.execute-api.ap-southeast-1.amazonaws.com/dev/workout/coach";
    public static Properties loadConfig() throws Exception {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("src\\test\\resources\\apitesting\\config.properties");
        props.load(in);
        in.close();
        return props;

    }
}
