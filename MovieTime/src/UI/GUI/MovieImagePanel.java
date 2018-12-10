package UI.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MovieImagePanel extends JPanel {
    private Image image;

    public MovieImagePanel() {
        this.setBorder(new LineBorder(Color.white, 4, true));
        setMaximumSize(new Dimension(getWidth(), getHeight()));
    }
    
    public void addImage(Image img){
        this.image = img;
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //TODO: remover fundo
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
}
