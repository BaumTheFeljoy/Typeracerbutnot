import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;

public class GUI {
    private JPanel panel;
    private JButton start;
    private JButton next;
    private JTextPane textpane;
    private JTextField typingArea;
    private static DefaultStyledDocument doc;

    GUI() {
        //Form file does all the work for us
    }

    JPanel getJPanel(){
        return panel;
    }

    JButton getStart(){
        return start;
    }

    JButton getNext(){
        return next;
    }

    JTextField getTypingArea(){
        return typingArea;
    }

    JTextPane getTextPane(){
        return textpane;
    }
}
