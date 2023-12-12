package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {

    static Logger logger = Logger.getLogger(Config.class);

    public String getPathFile() {

        try {
            String nomeSO = System.getProperty("os.name");
            File file1Windows = new File(getProperty("URL_DB_WINDOWS"));
            File file1Linux = new File(getProperty("URL_DB_LINUX"));

            if (nomeSO.contains("Windows") && !file1Windows.exists()) {
                return getProperty("URL_DB_WINDOWS");
            } else if (nomeSO.contains("Linux") && !file1Linux.exists()) {
                return getProperty("URL_DB_LINUX");
            }
        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
        }
        return "nao foi encontrado o arquivo db";
    }

    /*
	 * Pegar o caminho do arquivo no config.properties
	 * 
	 * @retorn String
	 * 
	 * */
    public String getProperty(String value) {
        Properties properties = null;
        String path = System.getProperty("user.dir");

        try (InputStream inputStream = new FileInputStream(path + "/config.properties")) {

            properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(value).trim();

        } catch (Exception e) {
            logger.info(e.getClass().getName() + " : " + e.getMessage());
        }

        return null;
    }
}
