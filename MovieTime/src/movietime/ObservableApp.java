package movietime;

import java.util.Observable;
import movietime.accounts.User;
import movietime.database.Movie;
import movietime.notification.NotificationManager;
import movietime.notification.NotificationService;

public class ObservableApp extends Observable {
    private User user;
    private final NotificationManager notificationManager;
    private NotificationService service;
    

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
    
    public void login(User user){
        
        //TODO: complete this code
        this.user = user;
        service = new NotificationService(this);
        service.start();
    }
    
    public void logout(){
        //TODO: complete this code
        //user = null;
        service.exit();
    }
}
