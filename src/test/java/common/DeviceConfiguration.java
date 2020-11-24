package common;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to load configuration params and use at other places
 */
public class DeviceConfiguration {

    private String baseURI;
    private String deviceURL;
    private String connectURL;
    private String brightnessURL;
    private String colorURL;
    private String nameURL;
    private String stateURL;

    private static DeviceConfiguration deviceConfigObj;

    private DeviceConfiguration() {
    }

    /**
     * @return DeviceConfiguration singleton object
     */
    public static DeviceConfiguration getInstance() {
        if(deviceConfigObj == null){
            synchronized (DeviceConfiguration.class){
                if(deviceConfigObj == null){
                    deviceConfigObj = new DeviceConfiguration();
                    //Load configuration values from config file
                    deviceConfigObj.loadConfiguration();
                }
            }
        }
        return deviceConfigObj;
    }


    /**
     * Method to load configuration values from config.properties file
     */
    private void loadConfiguration(){
        Properties configProperties = new Properties();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(Constants.TEST_PROPERTIES_FILENAME);
            configProperties.load(is);
            baseURI = configProperties.getProperty(Constants.BASE_API_URL);
            deviceURL = configProperties.getProperty(Constants.DEVICE_API_URL);
            connectURL = configProperties.getProperty(Constants.CONNECT_API_URL);
            brightnessURL = configProperties.getProperty(Constants.BRIGHTNESS_API_URL);
            colorURL = configProperties.getProperty(Constants.COLOR_API_URL);
            nameURL = configProperties.getProperty(Constants.NAME_API_URL);
            stateURL = configProperties.getProperty(Constants.STATE_API_URL);
        } catch (IOException e) {
            //Can create logger and log the error
            e.printStackTrace();
        }
    }

    public String getBaseURI() {
        return baseURI;
    }

    public String getDeviceURL() {
        return deviceURL;
    }

    public String getStateUrl() {
        return stateURL;
    }

    public String getConnectURL() {
        return connectURL;
    }

    public String getBrightnessURL() {
        return brightnessURL;
    }

    public String getColorURL() {
        return colorURL;
    }

    public String getNameURL() {
        return nameURL;
    }

}
