package uitesting.apiurls;

import java.io.FileInputStream;
import java.util.Properties;

public class Config3 {
    public static Properties loadConfig() throws Exception {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("src/test/resources/uitestingfeaturefiles/config3.properties");
        props.load(in);
        in.close();
        return props;

    }
}
