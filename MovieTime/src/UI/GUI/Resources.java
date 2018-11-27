package UI.GUI;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Resources {
    
    private static final String DEFAULT_PATH = "Resources/default.png";
    private static final String MOVIE_TIME_ICON = "Resources/popcorn.png";
    
    private static Image appIcon;
    
    public static final URL getResourceFile(String name){
            // opens a file with path relative to location of the Resources class
            URL url=Resources.class.getResource(name);
            return url;
    }
    
    
    static
    {
        try{
            appIcon = ImageIO.read(getResourceFile(MOVIE_TIME_ICON));
        
        }catch(IOException e){
            System.out.println("Could not load icon!");
        }
    }

    public static Image getAppIcon() {
        return appIcon;
    }
}