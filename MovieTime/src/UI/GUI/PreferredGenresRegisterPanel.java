/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.util.Observable;
import java.util.Observer;
import movietime.ObservableApp;

public class PreferredGenresRegisterPanel extends javax.swing.JPanel implements Observer {

    private ObservableApp observable;

    /**
     * Creates new form PreferredMoviesRegisterPanel
     */
    public PreferredGenresRegisterPanel(ObservableApp obs) {
        observable = obs;
        observable.addObserver(this);
        initComponents();
        this.setOpaque(false);
        jcbAction.setOpaque(false);
        jcbAdventure.setOpaque(false);
        jcbAnimation.setOpaque(false);
        jcbComedy.setOpaque(false);
        jcbCrime.setOpaque(false);
        jcbDocumentary.setOpaque(false);
        jcbDrama.setOpaque(false);
        jcbFamily.setOpaque(false);
        jcbFantasy.setOpaque(false);
        jcbHistory.setOpaque(false);
        jcbHorror.setOpaque(false);
        jcbMusic.setOpaque(false);
        jcbMystery.setOpaque(false);
        jcbRomance.setOpaque(false);
        jcbScienceFiction.setOpaque(false);
        jcbTVMovie.setOpaque(false);
        jcbThriller.setOpaque(false);
        jcbWar.setOpaque(false);
        jcbWestern.setOpaque(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prefGenresSelectionLabel = new javax.swing.JLabel();
        confirmPrefGenresButton = new javax.swing.JButton();
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

        setBackground(null);

        prefGenresSelectionLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        prefGenresSelectionLabel.setForeground(new java.awt.Color(255, 215, 0));
        prefGenresSelectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prefGenresSelectionLabel.setText("Select your favourite movie genres");
        prefGenresSelectionLabel.setToolTipText("");
        prefGenresSelectionLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        prefGenresSelectionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        confirmPrefGenresButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confirmPrefGenresButton.setText("Confirm");

        jcbAction.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbAction.setForeground(new java.awt.Color(255, 255, 240));
        jcbAction.setText("Action");
        jcbAction.setIconTextGap(15);
        jcbAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbActionActionPerformed(evt);
            }
        });

        jcbAdventure.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbAdventure.setForeground(new java.awt.Color(255, 255, 240));
        jcbAdventure.setText("Adventure");
        jcbAdventure.setIconTextGap(15);

        jcbAnimation.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbAnimation.setForeground(new java.awt.Color(255, 255, 240));
        jcbAnimation.setText("Animation");
        jcbAnimation.setIconTextGap(15);

        jcbComedy.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbComedy.setForeground(new java.awt.Color(255, 255, 240));
        jcbComedy.setText("Comedy");
        jcbComedy.setIconTextGap(15);

        jcbCrime.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbCrime.setForeground(new java.awt.Color(255, 255, 240));
        jcbCrime.setText("Crime");
        jcbCrime.setIconTextGap(15);

        jcbDocumentary.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbDocumentary.setForeground(new java.awt.Color(255, 255, 240));
        jcbDocumentary.setText("Documentary");
        jcbDocumentary.setIconTextGap(15);

        jcbDrama.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbDrama.setForeground(new java.awt.Color(255, 255, 240));
        jcbDrama.setText("Drama");
        jcbDrama.setIconTextGap(15);

        jcbFamily.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbFamily.setForeground(new java.awt.Color(255, 255, 240));
        jcbFamily.setText("Family");
        jcbFamily.setIconTextGap(15);

        jcbFantasy.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbFantasy.setForeground(new java.awt.Color(255, 255, 240));
        jcbFantasy.setText("Fantasy");
        jcbFantasy.setIconTextGap(15);

        jcbWar.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbWar.setForeground(new java.awt.Color(255, 255, 240));
        jcbWar.setText("War");
        jcbWar.setIconTextGap(15);

        jcbHistory.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbHistory.setForeground(new java.awt.Color(255, 255, 240));
        jcbHistory.setText("History");
        jcbHistory.setIconTextGap(15);

        jcbHorror.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbHorror.setForeground(new java.awt.Color(255, 255, 240));
        jcbHorror.setText("Horror");
        jcbHorror.setIconTextGap(15);

        jcbMusic.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbMusic.setForeground(new java.awt.Color(255, 255, 240));
        jcbMusic.setText("Music");
        jcbMusic.setIconTextGap(15);

        jcbMystery.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbMystery.setForeground(new java.awt.Color(255, 255, 240));
        jcbMystery.setText("Mystery");
        jcbMystery.setIconTextGap(15);

        jcbRomance.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbRomance.setForeground(new java.awt.Color(255, 255, 240));
        jcbRomance.setText("Romance");
        jcbRomance.setIconTextGap(15);

        jcbScienceFiction.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbScienceFiction.setForeground(new java.awt.Color(255, 255, 240));
        jcbScienceFiction.setText("Science Fiction");
        jcbScienceFiction.setIconTextGap(15);

        jcbTVMovie.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbTVMovie.setForeground(new java.awt.Color(255, 255, 240));
        jcbTVMovie.setText("TV Movie");
        jcbTVMovie.setIconTextGap(15);

        jcbThriller.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbThriller.setForeground(new java.awt.Color(255, 255, 240));
        jcbThriller.setText("Thriller");
        jcbThriller.setIconTextGap(15);

        jcbWestern.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        jcbWestern.setForeground(new java.awt.Color(255, 255, 240));
        jcbWestern.setText("Western");
        jcbWestern.setIconTextGap(15);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(prefGenresSelectionLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(confirmPrefGenresButton)
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbComedy)
                            .addComponent(jcbAnimation)
                            .addComponent(jcbAdventure)
                            .addComponent(jcbAction)
                            .addComponent(jcbCrime))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbDocumentary)
                            .addComponent(jcbDrama)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jcbFantasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcbFamily, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcbHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbScienceFiction)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbMusic)
                                    .addComponent(jcbHorror)
                                    .addComponent(jcbMystery)
                                    .addComponent(jcbRomance))
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbTVMovie)
                                    .addComponent(jcbWestern)
                                    .addComponent(jcbWar)
                                    .addComponent(jcbThriller))))
                        .addGap(183, 183, 183))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(prefGenresSelectionLabel)
                .addGap(143, 143, 143)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jcbWar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jcbMystery)
                                    .addGap(85, 85, 85)
                                    .addComponent(jcbScienceFiction))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jcbRomance)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbFamily)
                                    .addComponent(jcbAnimation)
                                    .addComponent(jcbWestern))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbFantasy)
                                    .addComponent(jcbComedy))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbCrime)
                                    .addComponent(jcbHistory))))))
                .addGap(148, 148, 148)
                .addComponent(confirmPrefGenresButton)
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbActionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbActionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton confirmPrefGenresButton;
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
    private javax.swing.JLabel prefGenresSelectionLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        //
    }
}
