package UI.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import movietime.ObservableApp;
import movietime.database.Movie;

public class MovieInfoPanel extends javax.swing.JPanel implements Observer {

    private ObservableApp observable;
    private Movie movie;

    /**
     * Creates new form movieInfoPanel
     */
    public MovieInfoPanel(ObservableApp obs, Movie m) {
        observable = obs;
        observable.addObserver(this);
        movie = m;
        initComponents();
        
        jpMovieImage.addImage(m.getPoster(Movie.SIZE_RECOMMENDED));
        
        jlMovieTitle.setText(m.getTitle());
        jlReleaseDate.setText(m.getReleaseDate());
        jlGenre.setText(m.getGenresToString());
        jlDirector.setText(m.getDirector());
        jlMainActors.setText(m.getCast().get(0));
        jpMovieImage.repaint();
        
        createListeners();
        
        update(observable,null);
    }
    
    
    public void createListeners(){
        jbFollow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(observable.isMovieBeingFollowed(movie.getId())){
                    observable.removeFavouriteMovie(movie.getId());
                } else {
                    observable.addPreferredMovie(movie.getId());
                }
            }
        });
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//        //TODO
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//        
//        g.drawImage(movie.getPoster(Movie.SIZE_RECOMMENDED), 0, 0, getWidth(), getHeight(), this);
//    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        releaseDateLabel = new javax.swing.JLabel();
        jlReleaseDate = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        directorLabel = new javax.swing.JLabel();
        mainActorsLabel = new javax.swing.JLabel();
        jlGenre = new javax.swing.JLabel();
        jlDirector = new javax.swing.JLabel();
        jlMainActors = new javax.swing.JLabel();
        jlMovieTitle = new javax.swing.JLabel();
        jbFollow = new javax.swing.JButton();
        jpMovieImage = new UI.GUI.MovieImagePanel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setMinimumSize(new java.awt.Dimension(750, 225));
        setPreferredSize(new java.awt.Dimension(750, 225));
        setRequestFocusEnabled(false);

        releaseDateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        releaseDateLabel.setText("Release date:");

        jlReleaseDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlReleaseDate.setText("[insert release date]");

        genreLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        genreLabel.setText("Genre(s):");

        directorLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        directorLabel.setText("Directed by:");

        mainActorsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mainActorsLabel.setText("Main actors:");

        jlGenre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlGenre.setText("[insert genre(s)]");

        jlDirector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlDirector.setText("[insert director]");

        jlMainActors.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlMainActors.setText("[insert main actors]");

        jlMovieTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlMovieTitle.setText("Movie title");

        jbFollow.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbFollow.setText("Follow");
        jbFollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFollowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMovieImageLayout = new javax.swing.GroupLayout(jpMovieImage);
        jpMovieImage.setLayout(jpMovieImageLayout);
        jpMovieImageLayout.setHorizontalGroup(
            jpMovieImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );
        jpMovieImageLayout.setVerticalGroup(
            jpMovieImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jpMovieImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genreLabel)
                            .addComponent(releaseDateLabel)
                            .addComponent(directorLabel)
                            .addComponent(mainActorsLabel))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDirector)
                            .addComponent(jlReleaseDate)
                            .addComponent(jlMainActors)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlGenre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                                .addComponent(jbFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jlMovieTitle)))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlMovieTitle)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(releaseDateLabel)
                                    .addGap(26, 26, 26)
                                    .addComponent(genreLabel))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(87, 87, 87)
                                    .addComponent(directorLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(mainActorsLabel)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlReleaseDate)
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jlGenre)
                                            .addComponent(jbFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(jlDirector)))
                                .addGap(18, 18, 18)
                                .addComponent(jlMainActors)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jpMovieImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFollowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbFollowActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel directorLabel;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JButton jbFollow;
    private javax.swing.JLabel jlDirector;
    private javax.swing.JLabel jlGenre;
    private javax.swing.JLabel jlMainActors;
    private javax.swing.JLabel jlMovieTitle;
    private javax.swing.JLabel jlReleaseDate;
    private UI.GUI.MovieImagePanel jpMovieImage;
    private javax.swing.JLabel mainActorsLabel;
    private javax.swing.JLabel releaseDateLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if(observable.isMovieBeingFollowed(movie.getId())){
            jbFollow.setText("Unfollow");
        } else {
            jbFollow.setText("Follow");
        }
        
        jpMovieImage.repaint();
    }
}
