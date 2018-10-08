import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.KeyStroke.getKeyStroke;

public class Frame extends JFrame
        implements KeyListener
{
    JFrame frame;
    JTextField typingArea;
    JButton start;
    JButton next;
    DefaultStyledDocument document;
    Style correct;
    Style incorrect;
    int count = 0;
    String text = "<html>warum Sonntag machen Landlust schwimmen Löffel</html>";
    String adjText = text.replace("<html>", "").replace("</html>", "");
    String[] words = adjText.split("\\s+");
    int totalWords = words.length;


    public Frame() {
        frame = new JFrame("FAFS"); //Fast as fuck schreiben
        addPanelComponents();
//        System.out.println(totalWords);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addPanelComponents(){
        frame.setSize(1400, 1200);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        JLabel intro = new JLabel("Tippe den folgenden Text!");
        intro.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(intro);


        panel.add(Box.createRigidArea(new Dimension(5,30)));


        document = new DefaultStyledDocument();
        StyleContext context = new StyleContext();
        // build a style
        correct = context.addStyle("correct", null);
        incorrect = context.addStyle("wrong", null);
        // set some style properties
        StyleConstants.setBackground(correct, new Color(83,229,4,70));
        StyleConstants.setBackground(incorrect, new Color(230,50,50,60));
        // add some data to the document
        try{
            document.insertString(0, adjText, null);
        } catch(BadLocationException e){}

        JTextPane abschreibText = new JTextPane(document);
        abschreibText.setFont(new Font("Arial", Font.PLAIN, 40));
        abschreibText.setMaximumSize(new Dimension(frame.getWidth(),500));
        abschreibText.setFocusable(false);
        panel.add(abschreibText);

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
        start.addActionListener(e -> startTypeTest());
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

// i dont know why or how and i cant delete them ¯\_(ツ)_/¯
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    private void startTypeTest() {
        typingArea.setText("");
        typingArea.setFocusable(true);
        typingArea.requestFocusInWindow();
        count = 0;

        typingArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    compare();
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
            public void removeUpdate(DocumentEvent e) {
                try {
                    compare();
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    compare();
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private int wordPos = 0;
    /*
    public void compare() {
        String typed = typingArea.getText();
        String[] splitWord = splitCurrWord(typed);
        if (words[count].equals(typed)){
            count++;
            next.requestFocusInWindow();
            System.out.println("yup");
            String[] splitText = splitText(count);
            wordPos = splitText[0].length();
            try{
                document.remove(0, document.getLength());
                document.insertString(0, splitText[1], null);
                document.insertString(0, splitText[0], correct);
            }
            catch(BadLocationException e){
                System.out.println("uh oh!");
            }
            if(count == totalWords){
                System.out.println("yuuuup!");
                count = 0;
                typingArea.setFocusable(false);
            }
        }
        else {
            try {
                document.remove(wordPos, words[count].length());
                document.insertString(wordPos, splitWord[2], null);
                document.insertString(wordPos, splitWord[1], incorrect);
                document.insertString(wordPos, splitWord[0], correct);
            } catch (BadLocationException e) {}
        }
        System.out.println(wordPos);
    }*/

    public void compare() throws BadLocationException {
        String typed = typingArea.getText();
        String[] splitWord = splitCurrWord(typed);
        String[] splitText = splitText(count);
        wordPos = splitText[0].length();
        document.remove(0, document.getLength());
        document.insertString(0, splitText[1], null);
        document.insertString(0, splitText[0], correct);

        document.remove(wordPos, words[count].length()); //i remove the entire word but dont display it at all times
        document.insertString(wordPos, splitWord[2], null);
        document.insertString(wordPos, splitWord[1], incorrect);
        document.insertString(wordPos, splitWord[0], correct);
        if (words[count].equals(typed)){
            count++;
            next.requestFocusInWindow();
            System.out.println("yup");
            if(count == totalWords){
                System.out.println("yuuuup!");
                count = 0;
                typingArea.setFocusable(false);
            }
        }
//        System.out.println(wordPos);
    }

    public String[] splitText(int count){
        String[] splitText = new String[2];
        String corrText = "";
        String remainingText = "";
        StringBuilder stbu = new StringBuilder();
        for(int i=0; i<count; i++) {
            stbu.append(words[i] +" ");
            corrText = stbu.toString();
        }
        stbu.delete(0,corrText.length());
        for(int i=count; i<words.length; i++) {
            stbu.append(words[i] +" ");
            remainingText = stbu.toString();
        }
        splitText[0] = corrText;
        splitText[1] = remainingText;
        return splitText;
    }

    public String[] splitCurrWord(String word){
        String currentWord = words[count];
        int wordlength = word.length();
        int difference;
        String[] splitWord = new String[3];
            char[] currCA = currentWord.toCharArray();  //this should only be updated when the word changes
            char[] typedCA = word.toCharArray();
            for(int i =0; i<currentWord.length() && i<wordlength; i++){
                if(currCA[i] != typedCA[i]){
                    difference = i + Math.abs(i-wordlength); //i to difference is red, rest is normal
                    if(difference > currentWord.length())difference = currentWord.length();
                    splitWord[0] = currentWord.substring(0,i);
                    splitWord[1] = currentWord.substring(i,difference);
                    splitWord[2] = currentWord.substring((difference));
//                    System.out.println(".");
//                    for(String s: splitWord){
//                        System.out.println(s);
//                    }
                    return splitWord;
                }
            }
        if(wordlength<=currentWord.length()) {
            splitWord[0] = currentWord.substring(0, wordlength);
            splitWord[2] = currentWord.substring(wordlength);
        }
//        System.out.println(".");
//        for(String s: splitWord){
//            System.out.println(s);
//        }
        return splitWord;
    }
}
