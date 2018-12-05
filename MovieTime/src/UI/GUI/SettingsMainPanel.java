package UI.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import movietime.ObservableApp;

public class SettingsMainPanel extends JPanel {

    private JButton jbNotifications;
    private JButton jbPreferredGenres;

    protected NotificationSettingsPanel pNotificationSettings;
    protected PreferredGenresPanel pPreferredGenres;

    public SettingsMainPanel(ObservableApp obs) {
        jbNotifications = new JButton("Notifications");
        jbPreferredGenres = new JButton("Preferred Genres");
        pNotificationSettings = new NotificationSettingsPanel(obs);
        pPreferredGenres = new PreferredGenresPanel(obs);
        
        JPanel pButtons = new JPanel();
        pButtons.add(jbNotifications);
        pButtons.add(jbPreferredGenres);
        
        JPanel pSettings = new JPanel();
        pSettings.add(pNotificationSettings);
        pSettings.add(pPreferredGenres);

        setLayout(new BorderLayout());
        //add(jbNotifications, BorderLayout.NORTH);
        //add(jbPreferredGenres, BorderLayout.NORTH);
        add(pButtons, BorderLayout.NORTH);
        //add(pNotificationSettings, BorderLayout.CENTER);
        //add(pPreferredGenres, BorderLayout.CENTER);
        add(pSettings, BorderLayout.CENTER);
        pNotificationSettings.setVisible(false);
        pPreferredGenres.setVisible(false);

        jbNotifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pNotificationSettings.isVisible()) {
                    pPreferredGenres.setVisible(false);
                    pNotificationSettings.setVisible(true);
                }
            }
        });
        
        jbPreferredGenres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pPreferredGenres.isVisible()) {
                    pNotificationSettings.setVisible(false);
                    pPreferredGenres.setVisible(true);
                }
            }
        });

    }

}
