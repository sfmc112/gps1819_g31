/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import movietime.ObservableApp;
import movietime.database.Movie;

/**
 *
 * @author sarah
 */
public class AppPanel extends JPanel implements Observer{

    private ObservableApp observable;
    private UserInfoPanel pUser;
    protected SideMenuPanel pSideMenu;
    private SearchPanel pSearch;
    private SettingsMainPanel pSettingsMain;
    private DisplayMoviesPanel pDisplayMovies;

    public AppPanel(ObservableApp obs) {
        observable = obs;
        observable.addObserver(this);
        pUser = new UserInfoPanel(obs);
        pSideMenu = new SideMenuPanel();
        pSearch = new SearchPanel(obs);
        pSettingsMain = new SettingsMainPanel(obs);
        pDisplayMovies = new DisplayMoviesPanel(obs);
        
        JPanel pSettingsOrDisplayMovies = new JPanel();
        pSettingsOrDisplayMovies.add(pSettingsMain);
        pSettingsOrDisplayMovies.add(pDisplayMovies);
        
        pSettingsMain.setVisible(false);
        pDisplayMovies.setVisible(false);
        
        // Inside Layout
        Box left = Box.createVerticalBox();
        left.add(pSideMenu);
        left.add(Box.createVerticalGlue());
        
        Box center = Box.createVerticalBox();
        center.add(pSearch);
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
        setActionForNotificationSettingsConfirmationFromNotificationPanel();
        setActionForPreferredGenresSettingsConfirmationFromPreferredGenresPanel();
        
        //TODO finish..
    }
    
    public void setToMainWindow(){
        pSearch.setVisible(false);
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
                }
                /*else{
                    observable.setDaysToAlert(observable.getDaysToAlert());
                    observable.setIncludeDirector(observable.isDirectorIncluded());
                    observable.setIncludeGenre(observable.isGenreIncluded());
                    observable.setIncludeMainActors(observable.isCastIncluded());
                }*/
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
                    HashSet<Integer> removeGenreSet = new HashSet<>();
                    if(pSettingsMain.pPreferredGenres.jcbAction.isSelected())
                        genreSet.add(Movie.ACTION);
                    else{
                        removeGenreSet.add(Movie.ACTION);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbAdventure.isSelected())
                        genreSet.add(Movie.ADVENTURE);
                    else{
                        removeGenreSet.add(Movie.ADVENTURE);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbAnimation.isSelected())
                        genreSet.add(Movie.ANIMATION);
                    else{
                        removeGenreSet.add(Movie.ANIMATION);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbComedy.isSelected())
                        genreSet.add(Movie.COMEDY);
                    else{
                        removeGenreSet.add(Movie.COMEDY);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbCrime.isSelected())
                        genreSet.add(Movie.CRIME);
                    else{
                        removeGenreSet.add(Movie.CRIME);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbDocumentary.isSelected())
                        genreSet.add(Movie.DOCUMENTARY);
                    else{
                        removeGenreSet.add(Movie.DOCUMENTARY);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbDrama.isSelected())
                        genreSet.add(Movie.DRAMA);
                    else{
                        removeGenreSet.add(Movie.DRAMA);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbFamily.isSelected())
                        genreSet.add(Movie.FAMILY);
                    else{
                        removeGenreSet.add(Movie.FAMILY);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbFantasy.isSelected())
                        genreSet.add(Movie.FANTASY);
                    else{
                        removeGenreSet.add(Movie.FANTASY);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbHistory.isSelected())
                        genreSet.add(Movie.HISTORY);
                    else{
                        removeGenreSet.add(Movie.HISTORY);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbHorror.isSelected())
                        genreSet.add(Movie.HORROR);
                    else{
                        removeGenreSet.add(Movie.HORROR);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbMusic.isSelected())
                        genreSet.add(Movie.MUSIC);
                    else{
                        removeGenreSet.add(Movie.MUSIC);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbMystery.isSelected())
                        genreSet.add(Movie.MYSTERY);
                    else{
                        removeGenreSet.add(Movie.MYSTERY);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbRomance.isSelected())
                        genreSet.add(Movie.ROMANCE);
                    else{
                        removeGenreSet.add(Movie.ROMANCE);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbScienceFiction.isSelected())
                        genreSet.add(Movie.SCIENCE_FICTION);
                    else{
                        removeGenreSet.add(Movie.SCIENCE_FICTION);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbThriller.isSelected())
                        genreSet.add(Movie.THRILLER);
                    else{
                        removeGenreSet.add(Movie.THRILLER);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbTVMovie.isSelected())
                        genreSet.add(Movie.TV_MOVIE);
                    else{
                        removeGenreSet.add(Movie.TV_MOVIE);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbWar.isSelected())
                        genreSet.add(Movie.WAR);
                    else{
                        removeGenreSet.add(Movie.WAR);
                    }
                    if(pSettingsMain.pPreferredGenres.jcbWestern.isSelected())
                        genreSet.add(Movie.WESTERN);
                    else{
                        removeGenreSet.add(Movie.WESTERN);
                    }
                    System.out.println(genreSet);
                    //System.out.println(removeGenreSet);
                    observable.addSetPreferredGenres(new HashSet<>());
                    observable.addSetPreferredGenres(genreSet);
                    //observable.removeSetPreferredGenres(removeGenreSet);
                }
            }
        
        });
    }

    private void changeToSettings(){
        pSearch.setVisible(false);
        pSettingsMain.setVisible(true);
        pDisplayMovies.setVisible(false);
    }
    
    private void changeToDisplayMovies(){
        pSearch.setVisible(true);
        pSettingsMain.setVisible(false);
        pDisplayMovies.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
