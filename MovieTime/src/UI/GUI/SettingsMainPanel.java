package UI.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
    private static final Font buttonFont = new Font("Tahoma", Font.PLAIN,16);
    
    protected NotificationSettingsPanel pNotificationSettings;
    protected PreferredGenresPanel pPreferredGenres;

    public SettingsMainPanel(ObservableApp obs) {
        jbNotifications = new JButton("Notifications");
        jbNotifications.setFont(buttonFont);
        jbPreferredGenres = new JButton("Preferred Genres");
        jbPreferredGenres.setFont(buttonFont);
        pNotificationSettings = new NotificationSettingsPanel(obs);
        pPreferredGenres = new PreferredGenresPanel(obs);
        
        setOpaque(false);
        JPanel pButtons = new JPanel();
        pButtons.add(jbNotifications);
        pButtons.add(jbPreferredGenres);
        pButtons.setOpaque(false);
        
        JPanel pSettings = new JPanel();
        pSettings.add(pNotificationSettings);
        pSettings.add(pPreferredGenres);
        pSettings.setOpaque(false);

        setLayout(new BorderLayout());
        add(pButtons, BorderLayout.NORTH);
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

        this.setOpaque(false);
    }

}
