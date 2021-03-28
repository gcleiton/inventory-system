package inventory_system.main.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Environment {

    private Properties config = null;

    public Environment (Properties config) {
        this.config = config;
        init();
    }

    private void init () {
        try {
            String filename = System.getProperty("user.dir") + "\\" + "application.properties";
            InputStream stream = new FileInputStream(filename);
            if (stream != null) {
                this.config.load(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get (String key){
        return this.config.getProperty(key);
    }

    public boolean contains (String key){
        return this.config.containsKey(key);
    }

    private static class EnvironmentHolder {
        private static final Environment INSTANCE = new Environment(new Properties());
    }

    public static Environment getInstance() {
        return EnvironmentHolder.INSTANCE;
    }
}
