import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.border.LineBorder;

public class Calculator {
    int bordwidth = 360;
    int bordheight = 540;
    Color customLightGray = new Color(212,212,210);
    Color customDarkGray = new Color(80,80,80);
    Color customBlack = new Color(28,28,28);
    Color customOrange = new Color(255,149,0);

    String[] buttonvalues = {
      "AC","+/-","%","/",
      "7","8","9","*",
      "4","5","6","-",
      "1","2","3","+",
      "0",".","^","="
    };
    String[] rightButton = {"/","*","-","+","="};
    String[] topButton = {"AC","+/-","%"};
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;



    Calculator(){

        frame.setSize(bordwidth,bordheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial",Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5,4));
        buttonPanel.setBackground(customBlack);
        frame.add(buttonPanel);

        for(int i=0 ; i<buttonvalues.length; i++){
            JButton button = new JButton();
            String buttonvalue = buttonvalues[i];
            button.setFont(new Font("Arial", Font.PLAIN,30));
            button.setText(buttonvalue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            if(Arrays.asList(topButton).contains(buttonvalue)){
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }else if(Arrays.asList(rightButton).contains(buttonvalue)){
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            }else{
                button.setBackground(customDarkGray);
                button.setForeground(Color.WHITE);
            }
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonvalue = button.getText();

                    if (Arrays.asList(rightButton).contains(buttonvalue)){
                        if(buttonvalue == "="){
                            if (A != null){
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator == "+"){
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }else if(operator == "-"){
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }else if(operator == "*"){
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }else if(operator == "/"){
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearALL();
                            }
                        }else if("/*-+".contains(buttonvalue)){
                            if(operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonvalue;
                        }
                    } else if (Arrays.asList(topButton).contains(buttonvalue)) {
                                if (buttonvalue == "AC"){
                                    clearALL();
                                    displayLabel.setText("0");
                                }else if(buttonvalue == "+/-"){
                                    double numDisplay = Double.parseDouble((displayLabel.getText()));
                                    numDisplay *= -1;
                                    displayLabel.setText(removeZeroDecimal(numDisplay));
                                }else if(buttonvalue == "%"){
                                    double numDisplay = Double.parseDouble((displayLabel.getText()));
                                    numDisplay /= 100;
                                    displayLabel.setText(removeZeroDecimal(numDisplay));
                                }
                    }else{
                        if(buttonvalue == "."){
                            if(!displayLabel.getText().contains(buttonvalue)){
                                displayLabel.setText(displayLabel.getText() + buttonvalue);
                            }

                        } else if ("0123456789".contains(buttonvalue)) {
                            if(displayLabel.getText() == "0"){
                                displayLabel.setText(buttonvalue);
                            }else{
                                displayLabel.setText(displayLabel.getText() + buttonvalue);
                            }
                        }
                    }
                }
            });
            frame.setVisible(true);
        }
    }
    void clearALL(){
        A = "0";
        operator = null;
        B = null;
    }
    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
