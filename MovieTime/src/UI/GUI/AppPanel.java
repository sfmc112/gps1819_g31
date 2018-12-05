/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.awt.BorderLayout;
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
        pSearch = new SearchPanel();
        pSettingsMain = new SettingsMainPanel(obs);
        pDisplayMovies = new DisplayMoviesPanel(obs);
        
        JPanel pSettingsOrDisplayMovies = new JPanel();
        pSettingsOrDisplayMovies.add(pSettingsMain);
        pSettingsOrDisplayMovies.add(pDisplayMovies);
        
        pSettingsMain.setVisible(false);
        pDisplayMovies.setVisible(false);
        
        // Inside Layout
        Box left = Box.createVerticalBox();
        left.add(Box.createVerticalGlue());
        left.add(pSideMenu);
        left.add(Box.createVerticalGlue());
        
        Box center = Box.createVerticalBox();
        center.add(Box.createVerticalGlue());
        center.add(pSearch);
        center.add(Box.createVerticalGlue());
        center.add(pSettingsOrDisplayMovies);
        center.add(Box.createVerticalGlue());
        
        Box total = Box.createHorizontalBox();
        total.add(Box.createHorizontalGlue());
        total.add(left);
        total.add(Box.createHorizontalGlue());
        total.add(center);
        total.add(Box.createHorizontalGlue());
        
        // Main Layout
        setLayout(new BorderLayout());
        
        add(pSideMenu, BorderLayout.NORTH);
        add(total, BorderLayout.CENTER);
        
        //TODO finish..
    }

}
