package UI.GUI;

import java.net.URL;

public class Resources {
    
    private static final String DEFAULT_PATH = "/Resources/default.png";
    
    public static final URL getResourceFile(String name){
            // opens a file with path relative to location of the Resources class
            URL url=Resources.class.getResource(name);
            return url;
    }
}