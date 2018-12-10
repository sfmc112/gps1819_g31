package UI.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import movietime.ObservableApp;
import movietime.database.Movie;

public class AppPanel extends JPanel {

    private ObservableApp observable;
    private UserInfoPanel pUser;
    protected SideMenuPanel pSideMenu;
    //private SearchPanel pSearch;
    private SettingsMainPanel pSettingsMain;
    private DisplayMoviesPanel pDisplayMovies;
    private Image bgImage;
    
    public AppPanel(ObservableApp obs) {
        observable = obs;
        
        pUser = new UserInfoPanel(obs);
        pSideMenu = new SideMenuPanel();
        //pSearch = new SearchPanel(obs);
        pSettingsMain = new SettingsMainPanel(obs);
        pDisplayMovies = new DisplayMoviesPanel(obs);
        JPanel pSettingsOrDisplayMovies = new JPanel();
        pSettingsOrDisplayMovies.add(pSettingsMain);
        pSettingsOrDisplayMovies.add(pDisplayMovies);
        
        pSettingsMain.setVisible(false);
        pDisplayMovies.setVisible(false);
        
        //leave all pannels without background
        pUser.setOpaque(false);
        pSideMenu.setOpaque(false);
        pSettingsMain.setOpaque(false);
        pDisplayMovies.setOpaque(false);
        pSettingsOrDisplayMovies.setOpaque(false);
        this.setOpaque(false);
        
        // Inside Layout
        Box left = Box.createVerticalBox();
        left.add(pSideMenu);
        left.add(Box.createVerticalGlue());
        
        Box center = Box.createVerticalBox();
        //center.add(pSearch);
        center.add(Box.createVerticalGlue());
        center.add(pSettingsOrDisplayMovies);
        center.add(Box.createVerticalGlue());
        
        Box total = Box.createHorizontalBox();
        total.add(left);
        total.add(Box.createHorizontalGlue());
        total.add(center);
        total.add(Box.createHorizontalGlue());
        
        // Main Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(pUser);
        add(total);
        
        setActionForSettingsButtonFromSideMenuPanel();
        setActionForUpcomingMoviesButtonFromSideMenuPanel();
        setActionForFollowedMoviesButtonFromSideMenuPanel();
        setActionForPreferredMoviesButtonFromSideMenuPanel();
        setActionForNotificationSettingsConfirmationFromNotificationPanel();
        setActionForPreferredGenresSettingsConfirmationFromPreferredGenresPanel();
        
        //TODO finish..
    }
    
    public void setToMainWindow(){
        //pSearch.setVisible(false);
        pSettingsMain.setVisible(false);
        pDisplayMovies.setVisible(true);
    }
    
    private void setActionForSettingsButtonFromSideMenuPanel() {
        pSideMenu.bSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToSettings();
            }
        });
    }

    private void setActionForUpcomingMoviesButtonFromSideMenuPanel() {
        pSideMenu.bUpcomingMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setDisplay(DisplayList.UPCOMINGMOVIES);
                observable.update();
                changeToDisplayMovies();
            }
        });
    }
    private void setActionForFollowedMoviesButtonFromSideMenuPanel() {
        pSideMenu.bFollowedMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setDisplay(DisplayList.FOLLOWEDMOVIES);
                observable.update();
                changeToDisplayMovies();
            }
        });
    }    
    private void setActionForPreferredMoviesButtonFromSideMenuPanel() {
        pSideMenu.bPreferredMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                observable.setDisplay(DisplayList.PREFERREDMOVIES);
                observable.update();
                changeToDisplayMovies();
            }
        });
    }       
    private void setActionForNotificationSettingsConfirmationFromNotificationPanel() {
        pSettingsMain.pNotificationSettings.bConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(pSettingsMain.pNotificationSettings, "Confirm changes?", "Attention",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0){
                    int days = Integer.parseInt(pSettingsMain.pNotificationSettings.jsDays.getValue().toString());
                    observable.setDaysToAlert(days);
                    observable.setIncludeDirector(pSettingsMain.pNotificationSettings.jcbDirector.isSelected());
                    observable.setIncludeGenre(pSettingsMain.pNotificationSettings.jcbGenre.isSelected());
                    observable.setIncludeMainActors(pSettingsMain.pNotificationSettings.jcbMainActors.isSelected());
                    observable.saveUserDataToFile();
                }
            }
        });
    }
    
    private void setActionForPreferredGenresSettingsConfirmationFromPreferredGenresPanel(){
        pSettingsMain.pPreferredGenres.bConfirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(pSettingsMain.pPreferredGenres, "Confirm changes?", "Attention",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0){
                    // To change the genre preferation there are going to be 2 HashSets
                    // One is going to contain the preferred genres then to be added
                    // with the observable.addSetPreferredGenres method
                    // The other is going to contain the not-preferred genres then
                    // to be remove with the observable.removeSetPreferredGenre method
                    HashSet<Integer> genreSet = new HashSet<>();
                    if(pSettingsMain.pPreferredGenres.jcbAction.isSelected())
                        genreSet.add(Movie.ACTION);
                    
                    if(pSettingsMain.pPreferredGenres.jcbAdventure.isSelected())
                        genreSet.add(Movie.ADVENTURE);
                    
                    if(pSettingsMain.pPreferredGenres.jcbAnimation.isSelected())
                        genreSet.add(Movie.ANIMATION);
                    
                    if(pSettingsMain.pPreferredGenres.jcbComedy.isSelected())
                        genreSet.add(Movie.COMEDY);
                    
                    if(pSettingsMain.pPreferredGenres.jcbCrime.isSelected())
                        genreSet.add(Movie.CRIME);
                    
                    if(pSettingsMain.pPreferredGenres.jcbDocumentary.isSelected())
                        genreSet.add(Movie.DOCUMENTARY);
                    
                    if(pSettingsMain.pPreferredGenres.jcbDrama.isSelected())
                        genreSet.add(Movie.DRAMA);
                    
                    if(pSettingsMain.pPreferredGenres.jcbFamily.isSelected())
                        genreSet.add(Movie.FAMILY);
                    
                    if(pSettingsMain.pPreferredGenres.jcbFantasy.isSelected())
                        genreSet.add(Movie.FANTASY);
                    
                    if(pSettingsMain.pPreferredGenres.jcbHistory.isSelected())
                        genreSet.add(Movie.HISTORY);
                    
                    if(pSettingsMain.pPreferredGenres.jcbHorror.isSelected())
                        genreSet.add(Movie.HORROR);
                    
                    if(pSettingsMain.pPreferredGenres.jcbMusic.isSelected())
                        genreSet.add(Movie.MUSIC);
                    
                    if(pSettingsMain.pPreferredGenres.jcbMystery.isSelected())
                        genreSet.add(Movie.MYSTERY);
                    
                    if(pSettingsMain.pPreferredGenres.jcbRomance.isSelected())
                        genreSet.add(Movie.ROMANCE);
                    
                    if(pSettingsMain.pPreferredGenres.jcbScienceFiction.isSelected())
                        genreSet.add(Movie.SCIENCE_FICTION);
                    
                    if(pSettingsMain.pPreferredGenres.jcbThriller.isSelected())
                        genreSet.add(Movie.THRILLER);
                    
                    if(pSettingsMain.pPreferredGenres.jcbTVMovie.isSelected())
                        genreSet.add(Movie.TV_MOVIE);
                    
                    if(pSettingsMain.pPreferredGenres.jcbWar.isSelected())
                        genreSet.add(Movie.WAR);
                    
                    if(pSettingsMain.pPreferredGenres.jcbWestern.isSelected())
                        genreSet.add(Movie.WESTERN);
                    
                    
                    observable.updatePreferredGenres(genreSet);
                    observable.saveUserDataToFile();
                    setToMainWindow();
                }
            }
        
        });
    }

    private void changeToSettings(){
        //pSearch.setVisible(false);
        pSettingsMain.setVisible(true);
        pDisplayMovies.setVisible(false);
    }
    
    private void changeToDisplayMovies(){
        //pSearch.setVisible(true);
        pSettingsMain.setVisible(false);
        pDisplayMovies.setVisible(true);
    }
}
