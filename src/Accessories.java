import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accessories {
    public JPanel panel1;
    private JComboBox typeBox;
    private JComboBox materialBox;
    private JComboBox genderBox;
    private JComboBox sizeBox;
    private JButton generateProductCodeButton;
    private JTextField productCodeField;

    private String typeSelected = "";
    private String materialSelected = "";
    private String genderSelected = "";
    private String sizeSelected = "";

    private String index = "0";
    private int state = 7;

    private String typeChar = "";
    private String materialChar = "";
    private String genderChar = "";
    private String sizeChar = "";

    private String sNewIndex = "";
    private int iNewIndex = 0;

    private boolean accepting = false;
    private boolean nextState = false;


    public Accessories() {

        sNewIndex = index;
        iNewIndex = Integer.parseInt(sNewIndex);

        generateProductCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generateProductCode(sNewIndex);
                } catch (Exception err) {
                    err.printStackTrace();
                    System.exit(0);
                }
            }
        });
    }
    private void generateProductCode(String currentIndex){
        try{
            String code, temp = "";
            int currentState = state;
            sNewIndex = currentIndex;
            iNewIndex = Integer.parseInt(sNewIndex);

            nextState = checkInput(typeBox);
            if(nextState){
                currentState++;
                typeSelected = checkInputToString(typeBox);
                nextState = checkInput(materialBox);
                if(nextState){
                    currentState++;
                    materialSelected= checkInputToString(materialBox);
                    nextState = checkInput(genderBox);
                    if (nextState){
                        currentState++;
                        genderSelected = checkInputToString(genderBox);
                        nextState = checkInput(sizeBox);
                        if(nextState){
                            currentState++;
                            sizeSelected = checkInputToString(sizeBox);
                            accepting = isAccepting(currentState);
                            if(accepting){
                                temp = generateCode(typeSelected, materialSelected, genderSelected, sizeSelected);
                                if(iNewIndex == 0){
                                    sNewIndex = String.format("%04d", iNewIndex);
                                    code = "A-" + temp +"-"+ sNewIndex;
                                    iNewIndex = Integer.parseInt(sNewIndex) + 1;
                                    sNewIndex = String.valueOf(iNewIndex);
                                } else {
                                    sNewIndex = String.format("%04d", iNewIndex);
                                    code = "A-" + temp +"-"+ sNewIndex;
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
                        JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a gender.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a material.");
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

    private String checkMaterialSelected(String material){
        String temp = "";
        if(material.contains(" ")){
            String aTemp[] = material.split(" ");
            temp += aTemp[0].substring(0, Math.min(aTemp[0].length(), 1));
            temp += aTemp[1].substring(0, Math.min(aTemp[1].length(), 1)).toLowerCase();
        } else {
            temp = "" + material.charAt(0);
        }
        return temp;
    }

    private String generateCode(String type, String material, String gender, String size){
        String sTemp = "";

        typeChar = "" + type.charAt(0);
        materialChar = checkMaterialSelected(material);
        genderChar = "" + gender.charAt(0);
        sizeChar = "" + size.charAt(0);

        sTemp = typeChar + materialChar + genderChar + sizeChar;

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
