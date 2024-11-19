package Calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI{
    private JFrame frame = new JFrame("Calculator");
    private JTextField input = new JTextField();
    private JTextField output = new JTextField();
    private JButton [] funcButt = new JButton[10];
    private JButton [] numButt = new JButton[10];
    private JButton [] miscButt = new JButton[4];
    private JButton addButt, subButt, mulButt, divButt, eqButt, delButt;
    private JButton negPos, decButt,clr, ce, mod,overButt, squButt,sqrtButt;
    private JPanel panel = new JPanel();
    private GridBagConstraints property = new GridBagConstraints();

    CalculatorGUI(){
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        frame.add(panel);

        //Default property of different components
        property.fill = GridBagConstraints.BOTH;
        property.weightx = 1;
        property.weighty = 1;
        property.insets = new Insets(1, 1, 1, 1);
        
        //Input Screen
        property.gridwidth = 4;
        posChange(property, 0, 0);
        tfLooks(input);
        input.setFont(new Font("Monospaced", Font.PLAIN, 24));
        panel.add(input,property);

        //Output Screen
        posChange(property, 0, 1);
        tfLooks(output);
        output.setFont(new Font("Monospaced", Font.PLAIN, 40));
        property.ipady = 40;
        panel.add(output,property);

        //Number buttons
        property.ipady = 0;
        property.gridwidth = 1;
        int numRow = 6;
        for (int i = 0; i < 10; i++) {
            if (i == 0) posChange(property, 1, 7);
             else {
                int col = (i - 1) % 3;           // Calculate column
                posChange(property, col, numRow);
                if (col == 2) numRow--;            // Move to the next row after every 3rd button
             }
             numButt[i] = new JButton(String.valueOf(i));
             styleButt(numButt[i], Color.DARK_GRAY);
             panel.add(numButt[i],property);
        }
        
        //Function buttons
        eqButt = new JButton("=");
        addButt = new JButton("+");
        subButt = new JButton("~");
        mulButt = new JButton("*");
        divButt = new JButton("/");
        delButt = new JButton("del");
        overButt = new JButton("1/x");
        squButt = new JButton("x^2");
        sqrtButt = new JButton("x^(-2)");
        mod = new JButton("%");
        funcButt[0] = eqButt;
        funcButt[1] = addButt;
        funcButt[2] = subButt;
        funcButt[3] = mulButt;
        funcButt[4] = divButt;
        funcButt[5] = delButt;
        funcButt[6] = overButt;
        funcButt[7] = squButt;
        funcButt[8] = sqrtButt;
        funcButt[9] = mod;

        for (int i = 0; i < 6 ; i ++){
            posChange(property, 3, 7 - i);
            styleButt(funcButt[i],new Color(44, 62, 80));
            panel.add(funcButt[i],property);
        }

        for(int i = 6; i < 9; i++){
            styleButt(funcButt[i],new Color(44, 62, 80));
            posChange(property, i - 6, 3);
            panel.add(funcButt[i],property);
        }
        styleButt(funcButt[9], new Color(44, 62, 80));
        posChange(property, 0, 2);
        panel.add(funcButt[9],property);

        //Misc Buttons
        clr = new JButton("CLR");
        ce = new JButton("CE");        
        negPos = new JButton("+/-");
        decButt = new JButton(".");

        miscButt[0] = ce;
        miscButt[1] = clr;
        miscButt[2] = negPos;
        miscButt[3] = decButt;

        for (int i = 0; i < 3; i++){
            styleButt(miscButt[i],new Color(44, 62, 80));
            posChange(property, i + 1,2);
            panel.add(miscButt[i],property);
         }

         posChange(property, 0, 7);
         styleButt(miscButt[2],new Color(44, 62, 80));
         panel.add(miscButt[2],property);
         posChange(property, 2, 7);
         styleButt(miscButt[3],new Color(44, 62, 80));
         panel.add(miscButt[3],property);

        frame.setVisible(true);
    }

    //Style of input and output screen
    private void tfLooks(JTextField tf){
        tf.setBackground(Color.DARK_GRAY);
        tf.setForeground(Color.WHITE);
        tf.setHorizontalAlignment(SwingConstants.RIGHT);
        tf.setBorder(BorderFactory.createEmptyBorder());
        tf.setEditable(false);
    }

    //Style Buttons
    private void styleButt(JButton num, Color color){
        num.setBackground(color);
        num.setForeground(Color.WHITE);
        num.setFocusable(false);
        num.setBorder(BorderFactory.createEmptyBorder());
    }

    //positioning of components
    private void posChange (GridBagConstraints gbc, int x, int y){
        gbc.gridx = x;
        gbc.gridy = y;
    }

    //Accessor
    public JTextField getInscreen(){
        return input;
    }

    
    public JTextField getOutscreen(){
        return output;
    }

    public JButton[] getNumButtons(){
        return numButt;
    }
    
    public JButton[] getFuncButtons(){
        return funcButt;
    }
    
    public JButton[] getMiscButtons(){
        return miscButt;
    }
}