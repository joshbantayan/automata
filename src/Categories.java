import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Categories {
    public JPanel Categories;
    public JButton healthAndBeutyButton;
    public JButton homeAndLifestyleButton;
    public JButton electronicDevicesButton;
    public JButton electronicAccessoriesButton;
    public JButton TVAndHomeAppliencesButton;
    public JButton babiesAndToysButton;
    public JButton groceriesAndPetsButton;
    public JButton clothesButton;
    public JButton shoesButton;
    public JButton fashionAccessoriesButton;
    public JButton sportsAndTravelButton;
    public JButton outdoorButton;

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
