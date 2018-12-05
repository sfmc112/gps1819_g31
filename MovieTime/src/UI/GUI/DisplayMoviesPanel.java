package UI.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import movietime.ObservableApp;
import movietime.database.Movie;

public class DisplayMoviesPanel extends JPanel implements Observer{
    
    private List<MovieInfoPanel> pMovies;
    private ObservableApp observable;

    //TODO how to do this???
    public DisplayMoviesPanel(ObservableApp obs) {
        observable = obs;
        observable.addObserver(this);
        
        pMovies = new ArrayList<>();
        
        //setPreferredSize(new Dimension(..., ...)); // Insert Here your size for the editor
        JScrollPane scroller = new JScrollPane(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ArrayList<Movie>> movies = observable.getUpcomingMovies();
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.get(i).size(); j++) {
                Movie m = movies.get(i).get(j);
                pMovies.add(new MovieInfoPanel(observable, m));
            }
        }
        
        Box movieBox = Box.createVerticalBox();
        for (MovieInfoPanel movy : pMovies) {
            movieBox.add(movy);
            movieBox.add(Box.createVerticalGlue());
        }
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(movieBox);
    }
    
}
