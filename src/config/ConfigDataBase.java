package config;

import org.apache.log4j.Logger;


public class ConfigDataBase {

    static Logger logger = Logger.getLogger(ConfigDataBase.class);
    private static boolean CONNETION = false;

    public Boolean connectionDataBaseLocation(String connectioDB) {

        try {
            Boolean dbConnection = Boolean.parseBoolean(connectioDB);
            if (!dbConnection) {
                return dbConnection;
            } else {
                return CONNETION;
            }
        } catch (Exception e) {
            logger.info( e.getClass().getName() + " : " + e.getMessage() );
        }
        return null;
    }

}
