package UI.GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import movietime.ObservableApp;
import movietime.database.Movie;

public class PreferredGenresPanel extends javax.swing.JPanel implements Observer{

    private ObservableApp observable;

    /**
     * Creates new form PreferredGenresPanel
     */
    public PreferredGenresPanel(ObservableApp obs) {
        observable = obs;
        observable.addObserver(this);
        initComponents();
        update(obs, null);
        createListeners();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbAction = new javax.swing.JCheckBox();
        jcbAdventure = new javax.swing.JCheckBox();
        jcbAnimation = new javax.swing.JCheckBox();
        jcbComedy = new javax.swing.JCheckBox();
        jcbCrime = new javax.swing.JCheckBox();
        jcbDocumentary = new javax.swing.JCheckBox();
        jcbDrama = new javax.swing.JCheckBox();
        jcbFamily = new javax.swing.JCheckBox();
        jcbFantasy = new javax.swing.JCheckBox();
        jcbWar = new javax.swing.JCheckBox();
        jcbHistory = new javax.swing.JCheckBox();
        jcbHorror = new javax.swing.JCheckBox();
        jcbMusic = new javax.swing.JCheckBox();
        jcbMystery = new javax.swing.JCheckBox();
        jcbRomance = new javax.swing.JCheckBox();
        jcbScienceFiction = new javax.swing.JCheckBox();
        jcbTVMovie = new javax.swing.JCheckBox();
        jcbThriller = new javax.swing.JCheckBox();
        jcbWestern = new javax.swing.JCheckBox();
        bCancel = new javax.swing.JButton();
        bConfirm = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(850, 447));
        setPreferredSize(new java.awt.Dimension(850, 447));

        jcbAction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbAction.setText("Action");

        jcbAdventure.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbAdventure.setText("Adventure");

        jcbAnimation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbAnimation.setText("Animation");

        jcbComedy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbComedy.setText("Comedy");

        jcbCrime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbCrime.setText("Crime");

        jcbDocumentary.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbDocumentary.setText("Documentary");

        jcbDrama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbDrama.setText("Drama");

        jcbFamily.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbFamily.setText("Family");

        jcbFantasy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbFantasy.setText("Fantasy");

        jcbWar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbWar.setText("War");

        jcbHistory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbHistory.setText("History");

        jcbHorror.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbHorror.setText("Horror");

        jcbMusic.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbMusic.setText("Music");

        jcbMystery.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbMystery.setText("Mystery");

        jcbRomance.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbRomance.setText("Romance");

        jcbScienceFiction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbScienceFiction.setText("Science Fiction");

        jcbTVMovie.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbTVMovie.setText("TV Movie");

        jcbThriller.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbThriller.setText("Thriller");

        jcbWestern.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbWestern.setText("Western");

        bCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        bConfirm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bConfirm.setText("Confirm");
        bConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbComedy)
                            .addComponent(jcbAnimation)
                            .addComponent(jcbAdventure)
                            .addComponent(jcbAction)
                            .addComponent(jcbCrime))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbDocumentary)
                                    .addComponent(jcbDrama))
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbMusic)
                                    .addComponent(jcbHorror)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcbFantasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbFamily, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbMystery)
                                    .addComponent(jcbRomance)
                                    .addComponent(jcbScienceFiction))))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bCancel)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jcbThriller)
                            .addGap(20, 20, 20))
                        .addComponent(jcbTVMovie)
                        .addComponent(jcbWestern))
                    .addComponent(jcbWar)
                    .addComponent(bConfirm))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAction)
                    .addComponent(jcbDocumentary, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbHorror)
                    .addComponent(jcbThriller))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAdventure)
                    .addComponent(jcbDrama)
                    .addComponent(jcbMusic)
                    .addComponent(jcbTVMovie))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbFamily)
                            .addComponent(jcbAnimation)
                            .addComponent(jcbMystery)
                            .addComponent(jcbWestern))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbFantasy)
                            .addComponent(jcbComedy))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCrime)
                            .addComponent(jcbHistory)
                            .addComponent(jcbScienceFiction)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbRomance)
                            .addComponent(jcbWar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancel)
                    .addComponent(bConfirm))
                .addGap(102, 102, 102))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        JOptionPane.showConfirmDialog(PreferredGenresPanel.this,
                "Your changes will not be saved. Continue?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_bCancelActionPerformed

    private void bConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancel;
    protected javax.swing.JButton bConfirm;
    protected javax.swing.JCheckBox jcbAction;
    protected javax.swing.JCheckBox jcbAdventure;
    protected javax.swing.JCheckBox jcbAnimation;
    protected javax.swing.JCheckBox jcbComedy;
    protected javax.swing.JCheckBox jcbCrime;
    protected javax.swing.JCheckBox jcbDocumentary;
    protected javax.swing.JCheckBox jcbDrama;
    protected javax.swing.JCheckBox jcbFamily;
    protected javax.swing.JCheckBox jcbFantasy;
    protected javax.swing.JCheckBox jcbHistory;
    protected javax.swing.JCheckBox jcbHorror;
    protected javax.swing.JCheckBox jcbMusic;
    protected javax.swing.JCheckBox jcbMystery;
    protected javax.swing.JCheckBox jcbRomance;
    protected javax.swing.JCheckBox jcbScienceFiction;
    protected javax.swing.JCheckBox jcbTVMovie;
    protected javax.swing.JCheckBox jcbThriller;
    protected javax.swing.JCheckBox jcbWar;
    protected javax.swing.JCheckBox jcbWestern;
    // End of variables declaration//GEN-END:variables

    public void createListeners(){
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(observable.getLoggedUser().getPreferredGenres());
        jcbAction.setSelected(observable.checkPreferredGenre(Movie.ACTION));
        jcbAdventure.setSelected(observable.checkPreferredGenre(Movie.ADVENTURE));
        jcbAnimation.setSelected(observable.checkPreferredGenre(Movie.ANIMATION));
        jcbComedy.setSelected(observable.checkPreferredGenre(Movie.COMEDY));
        jcbCrime.setSelected(observable.checkPreferredGenre(Movie.CRIME));
        jcbDocumentary.setSelected(observable.checkPreferredGenre(Movie.DOCUMENTARY));
        jcbDrama.setSelected(observable.checkPreferredGenre(Movie.DRAMA));
        jcbFamily.setSelected(observable.checkPreferredGenre(Movie.FAMILY));
        jcbFantasy.setSelected(observable.checkPreferredGenre(Movie.FANTASY));
        jcbHistory.setSelected(observable.checkPreferredGenre(Movie.HISTORY));
        jcbHorror.setSelected(observable.checkPreferredGenre(Movie.HORROR));
        jcbMusic.setSelected(observable.checkPreferredGenre(Movie.MUSIC));
        jcbMystery.setSelected(observable.checkPreferredGenre(Movie.MYSTERY));
        jcbRomance.setSelected(observable.checkPreferredGenre(Movie.ROMANCE));
        jcbScienceFiction.setSelected(observable.checkPreferredGenre(Movie.SCIENCE_FICTION));
        jcbThriller.setSelected(observable.checkPreferredGenre(Movie.THRILLER));
        jcbTVMovie.setSelected(observable.checkPreferredGenre(Movie.TV_MOVIE));
        jcbWar.setSelected(observable.checkPreferredGenre(Movie.WAR));
        jcbWestern.setSelected(observable.checkPreferredGenre(Movie.WESTERN));
    }
}
