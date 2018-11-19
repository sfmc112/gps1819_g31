package UI.GUI;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Resources {
    
    private static final String DEFAULT_PATH = "/Resources/default.png";
    private static Image defaultImage = null;
    
    //Load default image
    static {
        try {
            defaultImage = ImageIO.read(Resources.getResourceFile(DEFAULT_PATH));
        } catch (IOException e) {
            System.out.println("Error loading background");
        }
    }
    
    
    
    public static Image getDefaultImage() {
        return defaultImage;
    }
    
    public static final URL getResourceFile(String name){
            // opens a file with path relative to location of the Resources class
            URL url=Resources.class.getResource(name);
            return url;
    }
}