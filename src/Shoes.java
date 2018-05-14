import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shoes {
    public JPanel panel1;
    private JComboBox typeBox;
    private JComboBox brandBox;
    private JComboBox colorBox;
    private JComboBox sizeBox;
    private JButton generateProductCodeButton;
    private JTextField productCodeField;

    private String code = "S-";

    private String typeSelected = "";
    private String brandSelected = "";
    private String colorSelected = "";
    private String sizeSelected = "";

    private String index = "0";
    private int state = 7;

    private String typeChar = "";
    private String brandChar = "";
    private String colorChar = "";
    private String sizeChar = "";

    private String sNewIndex = "";
    private int iNewIndex = 0;

    private boolean accepting = false;
    private boolean nextState = false;

    public Shoes() {

        sNewIndex = index;
        iNewIndex = Integer.parseInt(sNewIndex);

        generateProductCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generateProductCode(sNewIndex);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });
    }
    private void generateProductCode(String currentIndex){
        try{
            String temp = "";
            int currentState = state;
            sNewIndex = currentIndex;
            iNewIndex = Integer.parseInt(sNewIndex);

            nextState = checkInput(typeBox);
            if(nextState){
                currentState++;
                typeSelected = checkInputToString(typeBox);
                nextState = checkInput(brandBox);
                if(nextState){
                    currentState++;
                    brandSelected = checkInputToString(brandBox);
                    nextState = checkInput(colorBox);
                    if (nextState){
                        currentState++;
                        colorSelected = checkInputToString(colorBox);
                        nextState = checkInput(sizeBox);
                        if(nextState){
                            currentState++;
                            sizeSelected = checkInputToString(sizeBox);
                            accepting = isAccepting(currentState);
                            if(accepting){
                                temp = generateCode(typeSelected, brandSelected, colorSelected, sizeSelected);
                                if(iNewIndex == 0){
                                    sNewIndex = String.format("%04d", iNewIndex);
                                    code = temp +"-"+ sNewIndex;
                                    iNewIndex = Integer.parseInt(sNewIndex) + 1;
                                    sNewIndex = String.valueOf(iNewIndex);
                                } else {
                                    sNewIndex = String.format("%04d", iNewIndex);
                                    code = temp +"-"+ sNewIndex;
                                    iNewIndex = Integer.parseInt(sNewIndex) + 1;
                                    sNewIndex = String.valueOf(iNewIndex);
                                }
                                productCodeField.setText(code);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error");
                                System.exit(0);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a size.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a color.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a brand.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a type.");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private boolean isAccepting(int currState){
        boolean temp = false;
        if(currState == 11){
            temp = true;
        }
        return temp;
    }

    private boolean checkInput(JComboBox input){
        boolean valid = false;
        try {
            String temp = input.getSelectedItem().toString();

            if(!temp.isEmpty()){
                valid = true;
            }
        }catch(Exception e) {
            valid = false;
        }
        return valid;
    }

    private String checkInputToString(JComboBox input){
        String temp = "";
        try {
            temp = input.getSelectedItem().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp;
    }

    private String generateCode(String type, String brand, String color, String size){
        String sTemp = "";

        typeChar = "" + type.charAt(0);
        brandChar = "" + brand.charAt(0);
        colorChar = "" + color.charAt(0);
        sizeChar = "s" + Integer.parseInt(size);

        sTemp = typeChar + brandChar + colorChar + sizeChar;

        return sTemp;
    }

    //main method to run the program
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sample");
        frame.setContentPane(new form().panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}