/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import movietime.ObservableApp;

/**
 *
 * @author sarah
 */
public class RegisterLoginPanel extends JPanel {

    private static final Font titleFont = new Font("Tahoma", Font.BOLD, 32);

    private JLabel jlTitle;
    protected LoginPanel pLogin;
    protected RegisterPanel pRegister;

    public RegisterLoginPanel(ObservableApp obs) {
        pLogin = new LoginPanel(obs);
        pRegister = new RegisterPanel(obs);
        jlTitle = new JLabel("Welcome to Movie Time");
        jlTitle.setFont(titleFont);

        Box left = Box.createVerticalBox();
        left.add(pRegister);
        left.add(Box.createVerticalGlue());

        Box right = Box.createVerticalBox();
        right.add(pLogin);
        right.add(Box.createVerticalGlue());

        Box main = Box.createHorizontalBox();
        main.add(Box.createHorizontalGlue());
        main.add(left);
        main.add(Box.createHorizontalGlue());
        main.add(right);
        main.add(Box.createHorizontalGlue());

        Box top = Box.createHorizontalBox();
        top.add(Box.createHorizontalGlue());
        top.add(jlTitle);
        top.add(Box.createHorizontalGlue());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(top);
        add(main);
    }

}
