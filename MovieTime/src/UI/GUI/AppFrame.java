package UI.GUI;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import movietime.ObservableApp;
import movietime.database.DatabaseManager;

public class AppFrame extends JFrame implements Observer{

    private ObservableApp observable;
    private SystemTray tray;
    //private AppPanel appPanel;
    
    public AppFrame( ObservableApp j) {
        this(j, 200,100, FrameConstants.DIM_FRAME_X,FrameConstants.DIM_FRAME_Y);
    }

    public AppFrame( ObservableApp j, int x, int y ) {
        this(j, x,y, 1050, 600);
    }

    public AppFrame(ObservableApp obs, int x, int y, int width, int height) {
        super("Movie Time");

        observable = obs;
        this.observable.addObserver(this);
        
        Container cp = getContentPane();
        createBarMenus();
        //TODO: create app panel 
        //appPanel = new AppPanel(observable);
        //cp.add(gamePanel,BorderLayout.CENTER);
        
        configureTrayIcon();
        setAppearance();
        
        setLocation(x, y);
        setSize(width,height);
        setMinimumSize(new Dimension(width,height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        update(observable, null);
    }
    
    private void configureTrayIcon(){
        tray = SystemTray.getSystemTray();
        try{
            tray.add(observable.getNotificationManager().getTrayIcon());
        }catch(AWTException e){
            
        }
    }
    
    private void setAppearance(){
        setIconImage(Resources.getAppIcon());
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(UnsupportedLookAndFeelException | ClassNotFoundException | 
                IllegalAccessException | InstantiationException e){
        }
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
    
    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if( JOptionPane.showConfirmDialog(AppFrame.this, "Do you want to exit Movie Time? you won't receice any notification while the is not running!", "Exit?", JOptionPane.YES_NO_OPTION) == 0)
            {
                System.exit(0);
            }
        }
    }

    class InstructionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point x = getLocationOnScreen();
            
            //TODO: remove this line from here once tests are complete
            observable.displayNotification(DatabaseManager.getMovieByID(100));
            //new HelpFileFrame("Help", Resources.getResourceFile("Resources/help.html"),x.x,x.y);
        }
    }

    class AboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(AppFrame.this,
                    "Movie Time v1.0" +
                    "For more info, don't check our website..." +
                    "Because we are poor and don't have one :(",
                    "About", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public void update(Observable o, Object o1) {
    }
}