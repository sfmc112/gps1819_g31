package movietime.accounts;

import java.io.Serializable;

public class NotificationPreferences implements Serializable{
    boolean includeGenre;
    boolean includeDirector;
    boolean includeMainActors;
    int daysToAlert;
    
    public NotificationPreferences(){
        includeDirector = false;
        includeMainActors = true;
        includeGenre = true;
        daysToAlert = 7;
    }

    public boolean isIncludeGenre() {
        return includeGenre;
    }

    public void setIncludeGenre(boolean includeGenre) {
        this.includeGenre = includeGenre;
    }

    public boolean isIncludeDirector() {
        return includeDirector;
    }

    public void setIncludeDirector(boolean includeDirector) {
        this.includeDirector = includeDirector;
    }

    public boolean isIncludeMainActors() {
        return includeMainActors;
    }

    public void setIncludeMainActors(boolean includeMainActors) {
        this.includeMainActors = includeMainActors;
    }

    public int getDaysToAlert() {
        return daysToAlert;
    }

    public void setDaysToAlert(int daysToAlert) {
        if(daysToAlert < 1)
            daysToAlert = 7;
        
        this.daysToAlert = daysToAlert;
    }
    
    
}
