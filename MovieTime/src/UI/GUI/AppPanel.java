/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import movietime.ObservableApp;

/**
 *
 * @author sarah
 */
public class AppPanel extends JPanel {

    private UserInfoPanel pUser;
    protected SideMenuPanel pSideMenu;
    private SearchPanel pSearch;
    private SettingsMainPanel pSettingsMain;
    private DisplayMoviesPanel pDisplayMovies;

    public AppPanel(ObservableApp obs) {
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
        
        //TODO finish..
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
}
