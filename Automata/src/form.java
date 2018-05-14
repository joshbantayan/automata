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

    private String genderSelected = "";
    private String typeSelected = "";
    private String colorSelected = "";
    private String sizeSelected = "";

    private String index = "0";
    private int state = 1;

    private String genderChar = "";
    private String typeChar = "";
    private String colorChar = "";
    private String sizeChar = "";

    private String temp = "";
    private String sNewIndex = "";
    private int iNewIndex = 0;

    private boolean accepting = false;
    private boolean nextState = false;



    public form() {

        sNewIndex = index;
        iNewIndex = Integer.parseInt(sNewIndex);

        generateProductCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    generateProductCode(sNewIndex);

//
//                    genderSelected = genderBox.getSelectedItem().toString();
//                    typeSelected = typeBpx.getSelectedItem().toString();
//                    colorSelected = colorBox.getSelectedItem().toString();
//                    sizeSelected = sizeBox.getSelectedItem().toString();
//
//                    genderChar = checkSelection(genderSelected);
//                    typeChar = checkSelection(typeSelected);
//                    colorChar = checkSelection(colorSelected);
//                    sizeChar = checkSelection(sizeSelected);
//
//                    if(iNewIndex == 0){
//                        sNewIndex = String.format("%04d", iNewIndex);
//                        temp = genderChar +"-"+ typeChar +"-"+ colorChar +"-"+ sizeChar +"-"+ sNewIndex;
//                        iNewIndex = Integer.parseInt(sNewIndex) + 1;
//                        sNewIndex = String.valueOf(iNewIndex);
//                    } else if(iNewIndex == 1) {
//                        sNewIndex = String.format("%04d", iNewIndex);
//                        temp = genderChar +"-"+ typeChar +"-"+ colorChar +"-"+ sizeChar +"-"+ sNewIndex;
//                        iNewIndex = Integer.parseInt(sNewIndex) + 1;
//                    } else {
//                        iNewIndex = Integer.parseInt(sNewIndex) + 1;
//                        sNewIndex = String.format("%04d", iNewIndex);
//                        temp = genderChar +"-"+ typeChar +"-"+ colorChar +"-"+ sizeChar +"-"+ sNewIndex;
//                    }
//
//                    productCodeField.setText(temp);

                }catch (Exception err){
                    err.printStackTrace();
                    System.exit(0);
                }

            }
        });

    }

//    private String checkSelection(String input){
//        String sTemp = input;
//
//        if (sTemp.isEmpty()){
//            return "X";
//        } else if(sTemp.matches("[ ]")){ // not yet functional
//            String[] aTemp = sTemp.split("[ ]");
//            for(int i = 0; aTemp.length > i; i++){
//                System.out.println(aTemp[i]);
//                sTemp += aTemp[i].charAt(0);
//            }
//            return sTemp;
//        }
//        return sTemp.charAt(0) + "";
//    }

//    private void checkState(String gender, String type, String color, String size, boolean accept){
//        if(gender != null){
//            state++; // state = 1
//            accept = false;
//            if(type != null) {
//                state++; // state = 2
//                accept = false;
//                if(color != null){
//                    state++;
//                    accept = false;
//                }
//            }
//        }
//    }

    /*
    * Finite State Automaton
    *
    *   An automaton that will generate product codes basing from user input. Generated product code which corresponds
    *   to product specifications
    *
    * Input:
    *
    *   Using GUI
    *       a) Category : Clothes(1), Shoes(2), Fashion and Accessories(3), Health and Beauty(4), Electronics(5)
    *           a.1) Clothes :
    *               a.1.1) Gender(6) : Male, Female, Neutral
    *               a.1.2) Type(7) : Top, Bottom, Overall, Underwear, Swimwear
    *               a.1.3) Color(8) : Black, White, Red, Orange, Yellow, Green, Blue, Violet, Lemon Lime
    *               a.1.4) Size(9) : Small, Medium, Large
    *
    *           a.2) Shoes :
    *               a.2.1) Gender : Male, Female, Neutral
    *               a.2.2) Size :
    *
    *           a.3) Accessories :
    *
    *           a.4) Health and Beauty :
    *
    *           a.5) Electronics :
    *
    * Output:
    *
    *   Generated Product Code based from user inputs in the form GUI / form.
    *
    * Algorithm:
    *
    *   Check each combo box from the form
    *       if
    *           form has sufficient user input
    *           List of accepting states :
    *       then
    *           change the state of the program based from the input
    *       else
    *           throw error
    *           pop-up appropriate error message to alert the user
    *           using JOptionPane.showMessageDialog
    *           List of rejecting states :
    *
    */

    private void generateProductCode(String currentIndex){
        try{
            String code, temp = "";
            int currentState = state;
            sNewIndex = currentIndex;
            iNewIndex = Integer.parseInt(sNewIndex);

            nextState = checkInput(genderBox);
            if(nextState){
                currentState++;
                genderSelected = checkInputToString(genderBox);
                nextState = checkInput(typeBpx);
                if(nextState){
                    currentState++;
                    typeSelected = checkInputToString(typeBpx);
                    nextState = checkInput(colorBox);
                    if (nextState){
                        currentState++;
                        colorSelected = checkInputToString(colorBox);
                        nextState = checkInput(sizeBox);
                        if(nextState){
                            currentState++;
                            sizeSelected = checkInputToString(sizeBox);
                            isAccepting(currentState);
                            if(accepting){
                                temp = generateCode(genderSelected, typeSelected, colorSelected, sizeSelected);
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
                            JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a size");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a color");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a type");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a gender");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void isAccepting(int currState){
        if(currState == 5){
            accepting = true;
        }
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

    private String generateCode(String gender, String type, String color, String size){
        String sTemp = "";

        genderChar = "" + gender.charAt(0);
        typeChar = "" + type.charAt(0);
        colorChar = checkColorSelected(color);
        sizeChar = "" + size.charAt(0);

        sTemp = genderChar + typeChar + colorChar + sizeChar;

        return sTemp;
    }

    private String checkColorSelected(String color){
        String temp = "";
        if(color.contains(" ")){
            String aTemp[] = color.split(" ");
            temp += aTemp[0].substring(0, Math.min(aTemp[0].length(), 1));
            temp += aTemp[1].substring(0, Math.min(aTemp[1].length(), 1)).toLowerCase();
        } else {
            temp = "" + color.charAt(0);
        }
        return temp;
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
