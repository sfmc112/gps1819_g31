package movietime;

import java.util.Observable;
import movietime.accounts.User;
import movietime.database.Movie;
import movietime.notification.NotificationManager;

public class ObservableApp extends Observable {
    private User user;
    private final NotificationManager notificationManager;
    

    public ObservableApp() {
        user = new User("username", "first", "last");
        notificationManager = new NotificationManager();
    }
    
    public void displayNotification(Movie movie){
        notificationManager.displayPopup(movie, user.getPreferences());
    }
    
    public User getUser(){
        return user;
    }
    
    public NotificationManager getNotificationManager(){
        return notificationManager;
    }
}
