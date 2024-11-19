package Calculator;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CalculatorController implements ActionListener {
    private CalculatorGUI view;
    private JTextField inScreen;
    private JTextField outScreen;
    private JButton [] numButtons;
    private JButton [] funcButtons;
    private JButton [] miscButtons;
    private boolean funcOrMiscPressed = false;
    private double result = 0;
    private boolean resultChecker = false;
    private boolean isPositive = true;

    public CalculatorController(CalculatorGUI view) {
        this.view = view; 
        inScreen = view.getInscreen();
        outScreen = view.getOutscreen();
        numButtons = view.getNumButtons();
        funcButtons = view.getFuncButtons();
        miscButtons = view.getMiscButtons();
        
        for (int i = 0; i < 10; i++) {
            numButtons[i].addActionListener(this); // Set action listener for each button
            if (i < 10)funcButtons[i].addActionListener(this);
            if (i < 4)miscButtons[i].addActionListener(this);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks here
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                numButtonHandler(i);
            }
            else if (i < 10 && e.getSource() == funcButtons[i] ) {
                funcButtonHandler(i);
            }
            else if (i < 4 && e.getSource() == miscButtons[i]){
                miscButtonHandler(i);
            }
        }
    }

    private void numButtonHandler (int i){
        String inExpression = inScreen.getText();
        String outExpression = outScreen.getText();
        if (resultChecker){
            outExpression = "";
            outScreen.setText("");
            resultChecker = false;
        }
        inScreen.setText(inExpression.concat(String.valueOf(i)));
        outScreen.setText(outExpression.concat(String.valueOf(i)));
    } 

    private void funcButtonHandler (int i){
        if (i != 0 && !(i >= 5 && i <= 8)  ){ // button pressed for equal, del, reciprocal, squared, and sqrt respectively
            if (funcOrMiscPressed ){
                String expression = inScreen.getText();
                result = Calculator.evaluate(expression);
                display(result);
                inScreen.setText(outScreen.getText().concat(funcButtons[i].getText()));
                resultChecker = true;
            } else{
                inScreen.setText(inScreen.getText().concat(funcButtons[i].getText()));
                outScreen.setText("");
                funcOrMiscPressed = true;
            }
        } else if (i == 5){ //for delete
            String expression = inScreen.getText();
            delete(expression);
        } else if (i == 0){ // for equal
            String expression = inScreen.getText();
            result = Calculator.evaluate(expression);
            funcOrMiscPressed = false;
            display(result);
        } else if (i == 6){ // for reciprocal
            double value = Double.parseDouble(outScreen.getText());
            value = Calculator.reciprocal(value);
            funcOrMiscPressed = false;
            display(value); 
        } else if (i == 7){ // for sqr
            double value = Double.parseDouble(outScreen.getText());
            value = Calculator.sqr(value);
            funcOrMiscPressed = false;
            display(value); 
        } else if (i == 8){ //for square root
            double value = Double.parseDouble(outScreen.getText());
            value = Calculator.sqrT(value);
            funcOrMiscPressed = false;
            display(value);
        }
    }
    
    private void miscButtonHandler(int i){
        String expression = "";
        switch (i) {
            case 0:
                expression = inScreen.getText();
                String [] numbers = expression.split("\\+|~|\\*|\\/|%");
                if(numbers.length == 1){ 
                    inScreen.setText("");
                    outScreen.setText("");
                } else{
                    int endIndex = numbers[1].length();
                    inScreen.setText(expression.substring(0,endIndex + 1));
                    outScreen.setText("");
                }
                funcOrMiscPressed = false;
                break;

            case 1:
                inScreen.setText("");
                outScreen.setText("");
                funcOrMiscPressed = false;
                break;

            case 2:
                expression = inScreen.getText();
                if (isPositive){
                    inScreen.setText("-" + expression);
                    outScreen.setText("-" + expression);
                    isPositive= false;
                } else{
                    inScreen.setText(expression.substring(1));
                    outScreen.setText(expression.substring(1));
                    isPositive= true;
                }
                funcOrMiscPressed = false;
                break;
            
            case 3:
                expression = inScreen.getText();
                inScreen.setText(expression + ".");
                outScreen.setText(expression + ".");
                funcOrMiscPressed = false;

            
            default:
                break;
         }
    }

    private void display (double value){
        if (value == (int) value){
            outScreen.setText(String.valueOf((int)value));
            inScreen.setText(String.valueOf((int)value));
        } else {
            outScreen.setText(String.valueOf(value));
            inScreen.setText(String.valueOf(value));
        }
    }

    private void delete (String expression){
        String [] inScreenValues = expression.split("\\+|~|\\*|\\/|%");
        if (inScreenValues.length == 2){
            inScreen.setText(expression.substring(0, inScreenValues[1].length() + 1));
            outScreen.setText("");
        }
        else {
            inScreen.setText("");
            outScreen.setText("");
        }
    }
}
