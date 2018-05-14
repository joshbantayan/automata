import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthAndBeauty {
    public JPanel panel1;
    private JComboBox typeBox;
    private JComboBox brandBox;
    private JComboBox toneBox;
    private JComboBox coverageBox;
    private JButton generateProductCodeButton;
    private JTextField productCodeField;

    private String code = "HaB-";

    private String typeSelected = "";
    private String brandSelected = "";
    private String toneSelected = "";
    private String coverageSelected = "";

    private String index = "0";
    private int state = 17;

    private String typeChar = "";
    private String brandChar = "";
    private String toneChar = "";
    private String coverageChar = "";

    private String sNewIndex = "";
    private int iNewIndex = 0;

    private boolean accepting = false;
    private boolean nextState = false;


    public HealthAndBeauty() {

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
                    nextState = checkInput(toneBox);
                    if (nextState){
                        currentState++;
                        toneSelected = checkInputToString(toneBox);
                        nextState = checkInput(coverageBox);
                        if(nextState){
                            currentState++;
                            coverageSelected = checkInputToString(coverageBox);
                            accepting = isAccepting(currentState);
                            if(accepting){
                                temp = generateCode(typeSelected, brandSelected, toneSelected, coverageSelected);
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
                            JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a coverage.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient input, Please choose a tone.");
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
        if(currState == 21){
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

    private String checkTypeSelected(String type){
        String temp = "";
        if(type.contains(" ")){
            String aTemp[] = type.split(" ");
            temp += aTemp[0].substring(0, Math.min(aTemp[0].length(), 1));
            temp += aTemp[1].substring(0, Math.min(aTemp[1].length(), 1)).toLowerCase();
        } else {
            temp = "" + type.charAt(0);
        }
        return temp;
    }

    private String checkBrandSelected(String type){
        String temp = "";
        if(type.contains(" ")){
            String aTemp[] = type.split(" ");
            temp += aTemp[0].substring(0, Math.min(aTemp[0].length(), 1));
            temp += aTemp[1].substring(0, Math.min(aTemp[1].length(), 1)).toLowerCase();
            temp += aTemp[2].substring(0, Math.min(aTemp[2].length(), 1)).toLowerCase();
        } else {
            temp = "" + type.charAt(0);
        }
        return temp;
    }

    private String generateCode(String type, String brand, String tone, String coverage){
        String sTemp = "";

        typeChar = checkTypeSelected(type);
        brandChar = checkBrandSelected(brand);
        toneChar = "" + tone.charAt(0);
        coverageChar = "" + coverage.charAt(0);

        sTemp = typeChar + brandChar + toneChar + coverageChar;

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

