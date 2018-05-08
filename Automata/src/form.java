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

    String genderSelected = "";
    String typeSelected = "";
    String colorSelected = "";
    String sizeSelected = "";

    String index = "0";

    String genderChar = "";
    String typeChar = "";
    String colorChar = "";
    String sizeChar = "";

    String temp = "";
    String sNewIndex = "";
    int iNewIndex = 0;

    boolean accepting = false;


    public form() {

        sNewIndex = index;
        iNewIndex = Integer.parseInt(sNewIndex);

        generateProductCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    genderSelected = genderBox.getSelectedItem().toString();
                    typeSelected = typeBpx.getSelectedItem().toString();
                    colorSelected = colorBox.getSelectedItem().toString();
                    System.out.println(colorSelected);
                    sizeSelected = sizeBox.getSelectedItem().toString();

                    genderChar = checkSelection(genderSelected);
                    typeChar = checkSelection(typeSelected);
                    colorChar = checkSelection(colorSelected);
                    sizeChar = checkSelection(sizeSelected);

                    if(iNewIndex == 0){
                        sNewIndex = String.format("%04d", iNewIndex);
                        temp = genderChar +"-"+ typeChar +"-"+ colorChar +"-"+ sizeChar +"-"+ sNewIndex;
                        iNewIndex = Integer.parseInt(sNewIndex) + 1;
                        sNewIndex = String.valueOf(iNewIndex);
                    } else if(iNewIndex == 1) {
                        sNewIndex = String.format("%04d", iNewIndex);
                        temp = genderChar +"-"+ typeChar +"-"+ colorChar +"-"+ sizeChar +"-"+ sNewIndex;
                        iNewIndex = Integer.parseInt(sNewIndex) + 1;
                    } else {
                        iNewIndex = Integer.parseInt(sNewIndex) + 1;
                        sNewIndex = String.format("%04d", iNewIndex);
                        temp = genderChar +"-"+ typeChar +"-"+ colorChar +"-"+ sizeChar +"-"+ sNewIndex;
                    }

                    productCodeField.setText(temp);

                }catch (Exception err){
                    err.printStackTrace();
                    System.exit(0);
                }

            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sample");
        frame.setContentPane(new form().panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String checkSelection(String input){
        String sTemp = input;

        if (sTemp.isEmpty()){
            return "X";
        } else if(sTemp.matches("[' ']")){ // not yet functional
            String[] aTemp = sTemp.split("[ ]");
            for(int i = 0; aTemp.length > i; i++){
                System.out.println(aTemp[i]);
                sTemp += aTemp[i].charAt(0);
            }
            return sTemp;
        }
        return sTemp.charAt(0) + "";
    }




}
