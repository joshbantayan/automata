import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Electronics {
    public JPanel panel1;
    private JComboBox typeBox;
    private JComboBox brandBox;
    private JComboBox performanceBox;
    private JComboBox colorBox;
    private JTextField productCodeField;
    private JButton generateProductCodeButton;

    private String code = "E-";

    private String typeSelected = "";
    private String brandSelected = "";
    private String performanceSelected = "";
    private String colorSelected = "";

    private String index = "0";
    private int state = 22;

    private String typeChar = "";
    private String brandChar = "";
    private String performanceChar = "";
    private String colorChar = "";

    private String sNewIndex = "";
    private int iNewIndex = 0;

    private boolean accepting = false;
    private boolean nextState = false;


    public Electronics() {

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
                    brandSelected= checkInputToString(brandBox);
                    nextState = checkInput(performanceBox);
                    if (nextState){
                        currentState++;
                        performanceSelected = checkInputToString(performanceBox);
                        nextState = checkInput(colorBox);
                        if(nextState){
                            currentState++;
                            colorSelected = checkInputToString(colorBox);
                            accepting = isAccepting(currentState);
                            if(accepting){
                                temp = generateCode(typeSelected, brandSelected, performanceSelected, colorSelected);
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
                            JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a color.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a performance.");
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
        if(currState == 26){
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

    private String checkBrandSelected(String brand){
        String temp = "";
        if(brand.contains(" ")){
            String aTemp[] = brand.split(" ");
            temp += aTemp[0].substring(0, Math.min(aTemp[0].length(), 1));
            temp += aTemp[1].substring(0, Math.min(aTemp[1].length(), 1)).toLowerCase();
        } else {
            temp = "" + brand.charAt(0);
        }
        return temp;
    }

    private String generateCode(String type, String brand, String performance, String color){
        String sTemp = "";

        typeChar = "" + type.charAt(0);
        brandChar = checkBrandSelected(brand);
        performanceChar = "" + performance.charAt(0);
        colorChar = "" + color.charAt(0);

        sTemp = typeChar + brandChar + performanceChar + colorChar;

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
