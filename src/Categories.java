import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Categories {
    public JPanel Categories;
    public JButton healthAndBeutyButton;
    public JButton electronicsButton;
    public JButton clothesButton;
    public JButton shoesButton;
    public JButton fashionAccessoriesButton;

    public Categories() {
        clothesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sample");
                frame.setContentPane(new form().panel1);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
