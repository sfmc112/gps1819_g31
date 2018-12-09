package UI.GUI;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import movietime.ObservableApp;
import movietime.authentication.UserAlreadyExistsException;
import movietime.authentication.UserDoesNotExistException;
import movietime.authentication.ValidationException;
import movietime.database.Movie;
import movietime.storage.OpeningFileException;
import movietime.storage.ReadWriteObjectException;

public class AppFrame extends JFrame implements Observer {

    private ObservableApp observable;
    private SystemTray tray;

    protected RegisterLoginPanel pRegisterLogin;
    protected PreferredGenresRegisterPanel pPreferredGenresRegister;
    protected AppPanel appPanel;

    public AppFrame(ObservableApp j) {
        this(j, 200, 100, FrameConstants.DIM_FRAME_X, FrameConstants.DIM_FRAME_Y);
    }

    public AppFrame(ObservableApp j, int x, int y) {
        this(j, x, y, 1050, 600);
    }

    public void changeToPreferredGenresRegisterPanel() {
        appPanel.setVisible(false);
        pPreferredGenresRegister.setVisible(true);
        pRegisterLogin.setVisible(false);
    }

    public void changeToAppPanel() {
        appPanel.setVisible(true);
        appPanel.setToMainWindow();
        pPreferredGenresRegister.setVisible(false);
        pRegisterLogin.setVisible(false);
    }

    public void changeToRegisterLoginPanel() {
        pRegisterLogin.pRegister.jtfUsername.setText("");
        pRegisterLogin.pRegister.jtfFirstName.setText("");
        pRegisterLogin.pRegister.jtfLastName.setText("");
        pRegisterLogin.pLogin.jtfUsername.setText("");

        appPanel.setVisible(false);
        pPreferredGenresRegister.setVisible(false);
        pRegisterLogin.setVisible(true);
    }

    public AppFrame(ObservableApp obs, int x, int y, int width, int height) {
        super("Movie Time");

        observable = obs;
        observable.addObserver(this);

        Container cp = getContentPane();
        JPanel pMain = new JPanel();

        //createBarMenus();
        pRegisterLogin = new RegisterLoginPanel(observable);
        pPreferredGenresRegister = new PreferredGenresRegisterPanel(observable);
        appPanel = new AppPanel(observable);

        pMain.add(appPanel);
        pMain.add(pPreferredGenresRegister);
        pMain.add(pRegisterLogin);

        cp.add(pMain, BorderLayout.CENTER);

        appPanel.setVisible(false);
        pPreferredGenresRegister.setVisible(false);
        pRegisterLogin.setVisible(true);

        setActionForRegisterButtonFromRegisterPanel();
        setActionForLoginButtonFromLoginPanel();
        setActionForConfirmButtonFromPreferredGenresRegisterPanel();
        setActionForLogoutButtonFromAppPanel();

        configureTrayIcon();
        setAppearance();
        addClosingProcedure(this);

        setLocation(x, y);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        update(observable, null);
    }

    private void addClosingProcedure(JFrame frame) {
        frame.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tray.remove(tray.getTrayIcons()[0]);
            }
        });
    }

    private void configureTrayIcon() {
        if (!SystemTray.isSupported()) {
            return;
        }

        tray = SystemTray.getSystemTray();
        try {
            tray.add(observable.getNotificationManager().getTrayIcon());
        } catch (AWTException e) {

        }
    }

    private void setAppearance() {
        setIconImage(Resources.getAppIcon());
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException
//                | IllegalAccessException | InstantiationException e) {
//        }
    }

    private void createBarMenus() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //TODO: Finde better way to display the top menus
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        //About Drop down
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        //Building top bar
        menuBar.add(helpItem);
        menuBar.add(aboutItem);

        //Assigning listeners to help menu
        helpItem.addActionListener(new InstructionsListener());
        aboutItem.addActionListener(new AboutListener());
    }

    private void setActionForRegisterButtonFromRegisterPanel() {
        JButton registerButton = pRegisterLogin.pRegister.registerButton;
        registerButton.addActionListener((ActionEvent e) -> {
            String username = pRegisterLogin.pRegister.jtfUsername.getText();
            String firstName = pRegisterLogin.pRegister.jtfFirstName.getText();
            String lastName = pRegisterLogin.pRegister.jtfLastName.getText();
            String error = "";

            try {
                observable.createUser(username, firstName, lastName);
                observable.login(username);
                changeToPreferredGenresRegisterPanel();
            } catch (UserAlreadyExistsException ex){
                error += ex.getMessage();
                JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Register error", JOptionPane.ERROR_MESSAGE);
            } catch (ValidationException ex) {
                error += ex.getMessage();
                JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Register error", JOptionPane.ERROR_MESSAGE);
            } catch (ReadWriteObjectException ex) {
                error += ex.getMessage();
                JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Register error", JOptionPane.ERROR_MESSAGE);
            } catch (OpeningFileException ex) {
                error += ex.getMessage();
                JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Register error", JOptionPane.ERROR_MESSAGE);
            } catch (UserDoesNotExistException ex) {
                JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Register error", JOptionPane.ERROR_MESSAGE);
            } finally {

            }
        });
    }

    private void setActionForConfirmButtonFromPreferredGenresRegisterPanel() {
        pPreferredGenresRegister.confirmPrefGenresButton.addActionListener((ActionEvent e) -> {
            HashSet<Integer> genreSet = new HashSet<>();

            if (pPreferredGenresRegister.jcbAction.isSelected()) {
                genreSet.add(Movie.ACTION);
            }
            if (pPreferredGenresRegister.jcbAdventure.isSelected()) {
                genreSet.add(Movie.ADVENTURE);
            }
            if (pPreferredGenresRegister.jcbAnimation.isSelected()) {
                genreSet.add(Movie.ANIMATION);
            }
            if (pPreferredGenresRegister.jcbComedy.isSelected()) {
                genreSet.add(Movie.COMEDY);
            }
            if (pPreferredGenresRegister.jcbCrime.isSelected()) {
                genreSet.add(Movie.CRIME);
            }
            if (pPreferredGenresRegister.jcbDocumentary.isSelected()) {
                genreSet.add(Movie.DOCUMENTARY);
            }
            if (pPreferredGenresRegister.jcbDrama.isSelected()) {
                genreSet.add(Movie.DRAMA);
            }
            if (pPreferredGenresRegister.jcbFamily.isSelected()) {
                genreSet.add(Movie.FAMILY);
            }
            if (pPreferredGenresRegister.jcbFantasy.isSelected()) {
                genreSet.add(Movie.FANTASY);
            }
            if (pPreferredGenresRegister.jcbHistory.isSelected()) {
                genreSet.add(Movie.HISTORY);
            }
            if (pPreferredGenresRegister.jcbHorror.isSelected()) {
                genreSet.add(Movie.HORROR);
            }
            if (pPreferredGenresRegister.jcbMusic.isSelected()) {
                genreSet.add(Movie.MUSIC);
            }
            if (pPreferredGenresRegister.jcbMystery.isSelected()) {
                genreSet.add(Movie.MYSTERY);
            }
            if (pPreferredGenresRegister.jcbRomance.isSelected()) {
                genreSet.add(Movie.ROMANCE);
            }
            if (pPreferredGenresRegister.jcbScienceFiction.isSelected()) {
                genreSet.add(Movie.SCIENCE_FICTION);
            }
            if (pPreferredGenresRegister.jcbTVMovie.isSelected()) {
                genreSet.add(Movie.TV_MOVIE);
            }
            if (pPreferredGenresRegister.jcbThriller.isSelected()) {
                genreSet.add(Movie.THRILLER);
            }
            if (pPreferredGenresRegister.jcbWar.isSelected()) {
                genreSet.add(Movie.WAR);
            }
            if (pPreferredGenresRegister.jcbWestern.isSelected()) {
                genreSet.add(Movie.WESTERN);
            }
            observable.updatePreferredGenres(genreSet);
            changeToAppPanel();
        });
    }

    private void setActionForLogoutButtonFromAppPanel() {

        appPanel.pSideMenu.bLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String error = "";
                try {
                    observable.logout();
                } catch (OpeningFileException ex) {
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Logout error", JOptionPane.ERROR_MESSAGE);
                } catch (ReadWriteObjectException ex) {
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(pRegisterLogin.pRegister, error, "Logout error", JOptionPane.ERROR_MESSAGE);
                }
                changeToRegisterLoginPanel();
            }
        });
    }

    private void setActionForLoginButtonFromLoginPanel() {
        pRegisterLogin.pLogin.jbLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = pRegisterLogin.pLogin.jtfUsername.getText();
                String error = "";
                try {
                    observable.login(username);
                    changeToAppPanel();
                } catch (ValidationException ex){
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(pRegisterLogin, error, "Login error", JOptionPane.ERROR_MESSAGE);
                }catch (UserDoesNotExistException ex) {
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(pRegisterLogin, error, "Login error", JOptionPane.ERROR_MESSAGE);
                } catch (OpeningFileException ex) {
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(pRegisterLogin, error, "Login error", JOptionPane.ERROR_MESSAGE);
                } catch (ReadWriteObjectException ex) {
                    error += ex.getMessage();
                    JOptionPane.showMessageDialog(pRegisterLogin, error, "Login error", JOptionPane.ERROR_MESSAGE);
                } finally {

                }
            }
        });
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(AppFrame.this, "Do you want to exit Movie Time? you won't receice any notification while the is not running!", "Exit?", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        }
    }

    class InstructionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Point x = getLocationOnScreen();
            new HelpFileFrame("Help", Resources.getResourceFile("Resources/help.html"), x.x, x.y);
        }
    }

    class AboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(AppFrame.this,
                    "Movie Time v1.0"
                    + "For more info, don't check our website..."
                    + "Because we are poor and don't have one :(",
                    "About", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
    }
}
