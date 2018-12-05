package UI.GUI;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import movietime.ObservableApp;
import movietime.database.Movie;

public class DisplayMoviesPanel extends JPanel implements Observer{
    
    private List<MovieInfoPanel> pMovies;
    private ObservableApp observable;
    private JPanel main;
    private JScrollPane scroller;
    private Box movieBox;

    public DisplayMoviesPanel(ObservableApp obs) {
        observable = obs;
        observable.addObserver(this);
        
        pMovies = new ArrayList<>();
        
        movieBox = Box.createVerticalBox();
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setPreferredSize(new Dimension(780, 5000));
        
        scroller = new JScrollPane(main);
        scroller.setPreferredSize(new Dimension(800, 400));
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scroller);
    }

    @Override
    public void update(Observable o, Object arg) {
        pMovies.clear();
        
        //if()
        ArrayList<ArrayList<Movie>> movies = observable.getUpcomingMovies();
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.get(i).size(); j++) {
                Movie m = movies.get(i).get(j);
                pMovies.add(new MovieInfoPanel(observable, m));
            }
        }
       
        for (MovieInfoPanel movy : pMovies) {
            movieBox.add(movy);
            movieBox.add(Box.createVerticalGlue());
        }

        main.add(movieBox);
        
    }
    
}
