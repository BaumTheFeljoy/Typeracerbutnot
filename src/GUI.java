import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;

public class GUI {
    /*private JLabel label;*/
    private JPanel panel;
    private JButton start;
    private JButton next;
    private JTextPane textpane;
    private JTextField typingArea;
    private JLabel wpm;
    private static DefaultStyledDocument doc;

    GUI() {
        //Form file does all the work for us
    }
    JLabel getWPMLabel(){
        return wpm;
    }

    JPanel getJPanel(){
        return panel;
    }

    JButton getStart(){
        //returns the start button
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
