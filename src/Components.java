import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Components extends JFrame {
    JFrame frame;
    JTextField typingArea;
    JButton start;
    JButton next;
    int count = 0;
    String text = "<html>warum Sonntag machen Landlust schwimmen Löffel</html>";

    public Components(){
        String adjText = text.replace("<html>", "").replace("</html>", "");
        String[] words = adjText.split("\\s+");
        int totalWords = words.length;
        addFrame();
        addPanel();

//        addStartButton(panel);
        addElements();
        frame = new JFrame("FAFS"); //Fast as fuck schreiben
        System.out.println(totalWords);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addFrame() {
        frame = new JFrame("FAFS"); //Fast as fuck schreiben
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1400, 1200);
        frame.setLocationRelativeTo(null);
    }

    private void addPanel() {
        JPanel panel = new JPanel();
        frame.add(panel);
    }

    private void addStartButton(JPanel panel){
        start = new JButton("Start");
//        start.addActionListener(e -> {
//            startTypeTest();
//        });
        start.setFont(new Font("Arial", Font.PLAIN, 40));
        panel.add(start);
    }

    private void addElements() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        JLabel intro = new JLabel("Tippe den folgenden Text!");
        intro.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(intro);


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        DefaultStyledDocument document = new DefaultStyledDocument();
        StyleContext context = new StyleContext();
        // build a style
        Style correct = context.addStyle("correct", null);
        Style incorrect = context.addStyle("wrong", null);
        // set some style properties
        StyleConstants.setBackground(correct, new Color(83,229,4,70));
        StyleConstants.setBackground(incorrect, new Color(230,50,50,60));
        // add some data to the document
//        try{
//            document.insertString(0, adjText, correct);
//            int offset = adjText.length()+1;
//            document.insertString(offset, adjText, null);
//            document.insertString(0, adjText, incorrect);
////            document.insertString(0, "aye bruh", null);
//        } catch(BadLocationException e){}
        JTextPane abschreibText = new JTextPane(document);
        abschreibText.setFont(new Font("Arial", Font.PLAIN, 40));
        abschreibText.setMaximumSize(new Dimension(frame.getWidth(),500));
        panel.add(abschreibText);

//        DefaultStyledDocument document = new DefaultStyledDocument();
//        JTextPane textpane = new JTextPane(document);
//        StyleContext context = new StyleContext();
//        // build a style
//        Style style = context.addStyle("test", null);
//        // set some style properties
//        StyleConstants.setBackground(style, new Color(83,229,4,70));
//        // add some data to the document
//        try{document.insertString(0, "ey bruh where you from?", style);
//        } catch(BadLocationException e){}
//        panel.add(textpane);


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        typingArea = new JTextField(30);
//        typingArea.addKeyListener(this);
        typingArea.setFont(new Font("Serif", Font.PLAIN, 40));
        typingArea.setForeground(new Color(50,40,40));
        typingArea.setMaximumSize(new Dimension(1200,60));
        typingArea.setFocusable(false);
        panel.add(typingArea);


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        start = new JButton("Start");
        start.addActionListener(e -> {
//            startTypeTest();
        });
        start.setFont(new Font("Arial", Font.PLAIN, 40));
        panel.add(start);


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        next = new JButton("Next");
        next.addActionListener(e -> {
            typingArea.setText("");
            typingArea.requestFocusInWindow();
        });
        next.setFont(new Font("Arial", Font.PLAIN, 40));
        panel.add(next);

        frame.add(panel);
        frame.setSize(1400, 1200);
        frame.setLocationRelativeTo(null);
    }
}
