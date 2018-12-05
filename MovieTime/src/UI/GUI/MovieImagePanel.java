package UI.GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import movietime.database.Movie;

public class MovieImagePanel extends JPanel {
    private Image image;

    public MovieImagePanel() {
    }
    
    public void addImage(Image img){
        this.image = image;
        repaint();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        // Draw Movie
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
}
