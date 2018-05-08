import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductCodeGenerator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sample");
        frame.setContentPane(new Categories().Categories);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
