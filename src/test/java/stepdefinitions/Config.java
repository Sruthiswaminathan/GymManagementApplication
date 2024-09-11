package stepdefinitions;

import java.io.FileInputStream;
import java.util.Properties;

// Config.java
public class Config {
    public static final String BASE_URL = "https://h3dhbcgbqh.execute-api.ap-southeast-1.amazonaws.com/dev";
    public static Properties loadConfig() throws Exception {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("C:\\Users\\hemavathi_v\\IdeaProjects\\GYMManagementProject\\src\\test\\resources\\config.properties");
        props.load(in);
        in.close();
        return props;
    }
}
