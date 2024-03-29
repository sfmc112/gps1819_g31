package UI.GUI;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Resources {
    
    private static final String DEFAULT_PATH = "Resources/default.png";
    private static final String MOVIE_TIME_ICON = "Resources/popcorn.png";
    private static final String BACKGROUND_IMAGE = "Resources/background.png";
    private static final String FOLLOWED_ICON = "Resources/heart.png";
    private static final String UNFOLLOWED_ICON = "Resources/emptyHeart.png";
    
    private static Image appIcon;
    private static Image defaultMoviePoster;
    private static ImageIcon followedIcon;
    private static ImageIcon unfollowedIcon;
    private static Image backgroundImage;
    
    public static final URL getResourceFile(String name){
            // opens a file with path relative to location of the Resources class
            URL url=Resources.class.getResource(name);
            return url;
    }
    
    
    static
    {
        try{
            appIcon = ImageIO.read(getResourceFile(MOVIE_TIME_ICON));
            defaultMoviePoster = ImageIO.read(getResourceFile(DEFAULT_PATH));
            backgroundImage = ImageIO.read(getResourceFile(BACKGROUND_IMAGE));
            followedIcon = new ImageIcon(ImageIO.read(getResourceFile(FOLLOWED_ICON)));
            unfollowedIcon = new ImageIcon(ImageIO.read(getResourceFile(UNFOLLOWED_ICON)));
            
        }catch(IOException e){
            System.out.println("Could not load icon!");
        }
    }

    public static Image getAppIcon() {
        return appIcon;
    }
    
    public static Image getDefaultMoviePoster(){
        return defaultMoviePoster;
    }

    public static ImageIcon getFollowedIcon() {
        return followedIcon;
    }

    public static ImageIcon getUnfollowedIcon() {
        return unfollowedIcon;
    }

    public static Image getBackground() {
        return backgroundImage;
    }
}