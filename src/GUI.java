import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;

public class GUI {
    private JPanel panel;
    private JButton start;
    private JButton next;
    private JTextPane textpane;
    private JTextField typingArea;
    private static DefaultStyledDocument doc;

    public GUI() {
        //Form file does all the work for us
    }

    public JPanel getJPanel(){
        return panel;
    }

    public JButton getStart(){
        return start;
    }

    public JButton getNext(){
        return next;
    }

    public JTextField getTypingArea(){
        return typingArea;
    }

    public JTextPane getTextPane(){
        return textpane;
    }
}
