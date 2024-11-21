package uitesting.apiurls;

import java.io.FileInputStream;
import java.util.Properties;

// Config1.java
public class Config1 {
    public static Properties loadConfig() throws Exception {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("src/test/resources/uitestingfeaturefiles/config1.properties");
        props.load(in);
        in.close();
        return props;

    }
}
