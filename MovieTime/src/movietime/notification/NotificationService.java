/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.notification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import movietime.ObservableApp;
import movietime.database.DatabaseManager;
import movietime.database.Movie;

public class NotificationService extends Thread {
    private static final int TIMEOUT = 1000 * 60 * 60 * 24;
    
    private boolean CONTINUE;
    private ObservableApp observable;
    
    public NotificationService(ObservableApp observable){
        this.observable = observable;
        CONTINUE = true;
    }
    
    public void exit(){
        CONTINUE = false;
        this.interrupt();
    }

    @Override
    public void run() {
        Set<Integer> ids;
        Movie movie;
        
        int days = 0;
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",
                Locale.UK);
        
        while(CONTINUE){
            try{
                ids = observable.getLoggedUser().getFavoriteMovieIDs();
                days = observable.getLoggedUser().getPreferences().getDaysToAlert();
                
                Iterator<Integer> it = ids.iterator();                
                while(it.hasNext()){
                    movie = DatabaseManager.getMovieByID(it.next());
                    
                    date =  LocalDate.parse(movie.getRelease_date(), formatter);
                    date = date.minusDays(days);
                    
                    if(date.isBefore(LocalDate.now()) 
                            || date.isEqual(LocalDate.now()))
                    {
                        observable.displayNotification(movie);
                    }
                }
                
                Thread.sleep(TIMEOUT);
                
            }catch(InterruptedException e){
            }
        }
    }
}
