import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form {
    private JPanel panel1;
    private JComboBox genderBox;
    private JComboBox typeBpx;
    private JComboBox colorBox;
    private JComboBox sizeBox;
    private JTextField productCodeField;
    private JButton generateProductCodeButton;

    public form() {
        generateProductCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender;
                String type;
                String color;
                String size;

                gender = genderBox.getSelectedItem();
                type = typeBpx.getSelectedItem();
                color = colorBox.getSelectedItem();
                size = sizeBox.getSelectedItem();

                // insert more here
            }
        });
    }
}
