package UI.GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import movietime.ObservableApp;


public class NotificationSettingsPanel extends javax.swing.JPanel implements Observer {
    
    private ObservableApp observable;

    /**
     * Creates new form NotificationSettingsPanel
     */
    public NotificationSettingsPanel(ObservableApp obs) {
        observable = obs;
        observable.addObserver(this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alarmFreqLabel = new javax.swing.JLabel();
        shownInfoLabel = new javax.swing.JLabel();
        jcbGenre = new javax.swing.JCheckBox();
        jcbDirector = new javax.swing.JCheckBox();
        jcbMainActors = new javax.swing.JCheckBox();
        jsDays = new javax.swing.JSpinner();
        bCancel = new javax.swing.JButton();
        bConfirm = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(850, 447));

        alarmFreqLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        alarmFreqLabel.setText("Alarm frequency (days):");

        shownInfoLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        shownInfoLabel.setText("Info to be displayed:");

        jcbGenre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbGenre.setText("Genre");

        jcbDirector.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbDirector.setText("Director");

        jcbMainActors.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbMainActors.setText("Main actors");

        jsDays.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        bCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bCancel.setText("Cancel");

        bConfirm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bConfirm.setText("Confirm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alarmFreqLabel)
                            .addComponent(shownInfoLabel))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbMainActors)
                            .addComponent(jcbDirector)
                            .addComponent(jcbGenre)
                            .addComponent(jsDays, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bCancel)
                        .addGap(18, 18, 18)
                        .addComponent(bConfirm)))
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alarmFreqLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jsDays, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shownInfoLabel)
                    .addComponent(jcbGenre))
                .addGap(18, 18, 18)
                .addComponent(jcbDirector)
                .addGap(18, 18, 18)
                .addComponent(jcbMainActors)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancel)
                    .addComponent(bConfirm))
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alarmFreqLabel;
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bConfirm;
    private javax.swing.JCheckBox jcbDirector;
    private javax.swing.JCheckBox jcbGenre;
    private javax.swing.JCheckBox jcbMainActors;
    private javax.swing.JSpinner jsDays;
    private javax.swing.JLabel shownInfoLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        jsDays.setValue(observable.getDaysToAlert());
        jcbGenre.setSelected(observable.isGenreIncluded());
        jcbDirector.setSelected(observable.isDirectorIncluded());
        jcbMainActors.setSelected(observable.isCastIncluded());
    }
}
