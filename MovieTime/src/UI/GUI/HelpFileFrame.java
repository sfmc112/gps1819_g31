
package UI.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class HelpFileFrame extends JFrame implements ActionListener {

    private final int width = 600;
    private final int height = 400;
    private JEditorPane editorpane;
    private URL helpURL;

    public HelpFileFrame(String title, URL hlpURL, int x, int y) {
        super(title);
        helpURL = hlpURL;
        editorpane = new JEditorPane();
        editorpane.setEditable(false);

        try {
            editorpane.setPage(helpURL);
        } catch (Exception ex) {
        }

        editorpane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent ev) {
                try {
                    if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        editorpane.setPage(ev.getURL());
                    }
                } catch (IOException ex) {
                }
            }
        });

        getContentPane().add(new JScrollPane(editorpane));            
        setLocation(x, y);
        setSize(width,height);
        setMinimumSize(new Dimension(width,height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        return;
    }

}
