/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import javax.swing.JPanel;
import movietime.ObservableApp;

/**
 *
 * @author sarah
 */
public class AppPanel extends JPanel {

    private UserInfoPanel pUser;
    private SideMenuPanel pSideMenu;
    private SearchPanel pSearch;
    private SettingsMainPanel pSettingsMain;
    private DisplayMoviesPanel pDisplayMovies;

    public AppPanel(ObservableApp obs) {
        pUser = new UserInfoPanel();
        pSideMenu = new SideMenuPanel();
        pSearch = new SearchPanel();
        pSettingsMain = new SettingsMainPanel(obs);
        pDisplayMovies = new DisplayMoviesPanel(obs);
        
        //TODO finish..
    }

}
