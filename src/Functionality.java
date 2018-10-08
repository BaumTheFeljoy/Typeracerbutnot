import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;

public class Functionality {
    private GUI gui;
    private JTextField typingArea;
    private JTextPane textpane;
    private JButton start;
    private JButton next;
    private StyledDocument document;
    private Style correct;
    private Style incorrect;
    private String text;
    private String adjText;
    private String[] words;
    private int totalWords;
    private int wordPos = 0;
    private int count = 0;


    public Functionality(){
        gui = new GUI();
        initializeFields();
        setupFrame();
        setupListeners();
    }

    private void initializeFields() {
        typingArea = gui.getTypingArea();
        textpane = gui.getTextPane();
        next = gui.getNext();
        start = gui.getStart();
        text = "<html>Milch Baum Katze wechseln Taschentuch</html>";
        adjText = text.replace("<html>", "").replace("</html>", "");
        words = adjText.split("\\s+");
        totalWords = words.length;
        document = textpane.getStyledDocument();
        StyleContext context = new StyleContext();
        correct = context.addStyle("correct", null);
        incorrect = context.addStyle("wrong", null);
        StyleConstants.setBackground(correct, new Color(83,229,4,70));
        StyleConstants.setBackground(incorrect, new Color(230,50,50,60));
        try{
            document.insertString(0, adjText, null);
        } catch(BadLocationException e){}
    }

    private void setupFrame(){
        JFrame frame = new JFrame("FAFS");
        frame.setContentPane(gui.getJPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void setupListeners() {
        next.addActionListener(e -> {
            typingArea.setText("");
            typingArea.requestFocusInWindow();
        });
        start.addActionListener(e -> startTypeTest());
    }

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
//            System.out.println("yup");
            if(count == totalWords){
//                System.out.println("yuuuup!");
                count = 0;
                typingArea.setFocusable(false);
            }
        }
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
                difference = i + Math.abs(i-wordlength);
                if(difference > currentWord.length())difference = currentWord.length();
                splitWord[0] = currentWord.substring(0,i);
                splitWord[1] = currentWord.substring(i,difference);  //this is the incorrect part
                splitWord[2] = currentWord.substring((difference));
                return splitWord;
            }
        }
        if(wordlength<=currentWord.length()) {
            splitWord[0] = currentWord.substring(0, wordlength);
            splitWord[2] = currentWord.substring(wordlength);
        }
        return splitWord;
    }
}
