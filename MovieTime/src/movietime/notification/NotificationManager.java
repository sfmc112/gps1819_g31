package movietime.notification;

import UI.GUI.Resources;
import java.awt.Image;
import java.awt.TrayIcon;
import movietime.accounts.NotificationPreferences;
import movietime.database.Movie;

public class NotificationManager {
    static final String NAME = "Movie Time";
    
    TrayIcon trayIcon = null;
    
    public NotificationManager(){
        Image image = Resources.getAppIcon().getScaledInstance(32, 32,
                Image.SCALE_SMOOTH);
        
        trayIcon = new TrayIcon(image,NAME);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(NAME);
    }
    
    public void displayPopup(Movie movie, NotificationPreferences preferences){
        StringBuilder builder = new StringBuilder();
        builder.append("Release Date: ").append(movie.getReleaseDate());
                
        if(preferences.isIncludeGenre())
            builder.append("\nGenre: ").append(movie.getPrimaryGenre());
        
        if(preferences.isIncludeMainActors()){
            builder.append("\nCast: ");
            
            for(String actor : movie.getCast())
                builder.append(actor).append(",");
        
            builder.setLength(builder.length()-1);
        }
        
        if(preferences.isIncludeDirector())
            builder.append("Director: ").append(movie.getDirector());
        
        trayIcon.displayMessage("Movie Time - Upcoming release\n•"
                + movie.getTitle(), builder.toString(),
                TrayIcon.MessageType.NONE);
    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }
}