
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JTextArea textArea = new JTextArea("");
        textArea.setEditable(false);
        frame.add(textArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);
        frame.setVisible(true);
        Server obj = new Server(textArea);
        
    }
}