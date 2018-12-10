/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import java.awt.Color;
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
        JLabel jlSpace = new JLabel("\n");
        jlSpace.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jlTitle = new JLabel("Welcome to Movie Time");
        jlTitle.setForeground(new Color(255,223,0));
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
        Box topV = Box.createVerticalBox();
        topV.add(jlSpace);
        topV.add(Box.createVerticalGlue());
        topV.add(jlTitle);
        top.add(topV);
        top.add(Box.createHorizontalGlue());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(top);
        add(main);
        
        pLogin.setOpaque(false);

    }

}
