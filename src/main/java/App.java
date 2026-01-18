import javax.swing.JFrame;

import java.awt.EventQueue;
import game.Start;

public class App {
    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Start();
            ex.setVisible(true);
        });
    }
}
