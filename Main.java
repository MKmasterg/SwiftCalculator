import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    public static void main(String[] args) {
        //Initializing the main frame
        Dimension frameDimensions = new Dimension(300, 400);
        JFrame frame = new JFrame("Calculator");
        frame.setSize(frameDimensions);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Initializing the Big Panel
        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
        frame.add(bigPanel);

        //Initializing the input area panel
        JPanel inputPanel = new JPanel();
//        inputPanel.setPreferredSize(new Dimension());
        //The components
        JTextField inputArea = new JTextField();
        inputPanel.setLayout(new GridLayout(3, 1, 5, 5));
        inputArea.setEnabled(false);
        inputArea.setDisabledTextColor(Color.BLACK);
        inputPanel.add(new JPanel());
        inputPanel.add(inputArea);


        //Initializing the number and math operation panel
        JPanel mathPanel = new JPanel();
        mathPanel.setLayout(new GridLayout(4, 4, 3, 1));
        //The components
        JButton[] numbers = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            numbers[i] = new JButton(Integer.toString(i));
        }
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton product = new JButton("*");
        JButton divide = new JButton("/");
        JButton point = new JButton(".");
        JButton equal = new JButton("=");
        for (int i = 1; i <= 3; i++) {
            mathPanel.add(numbers[i]);
        }
        mathPanel.add(plus);
        for (int i = 4; i <= 6; i++) {
            mathPanel.add(numbers[i]);
        }
        mathPanel.add(minus);
        for (int i = 7; i <= 9; i++) {
            mathPanel.add(numbers[i]);
        }
        mathPanel.add(product);
        mathPanel.add(point);
        mathPanel.add(numbers[0]);
        mathPanel.add(equal);
        mathPanel.add(divide);


        //Initializing the calculator operation panel
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(1, 3, 4, 1));
        //The components
        JButton delete = new JButton("DEL");
        JButton clear = new JButton("CLR");
        JButton makeNegative = new JButton("-");
        operationPanel.add(delete);
        operationPanel.add(clear);
        operationPanel.add(makeNegative);


        bigPanel.add(inputPanel);
        bigPanel.add(mathPanel);
        bigPanel.add(operationPanel);
        frame.revalidate();
        frame.repaint();
        //Finished Initializing


        //Adding functionality to the buttons

        final MathComplex mathFinal = new MathComplex();

        //The number buttons
        for (int i = 0; i <= 9; i++) {
            int finalI = i;
            numbers[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inputArea.setText(inputArea.getText() + finalI);
                }
            });
        }

        point.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!inputArea.getText().contains("."))
                    inputArea.setText(inputArea.getText() + ".");
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!mathFinal.isPlus && !mathFinal.isMinus && !mathFinal.isDivide && !mathFinal.isProduct){
                    mathFinal.leftHandContainer = Double.parseDouble(inputArea.getText());
                    inputArea.setText("");
                } else if (mathFinal.isPlus) {
                    mathFinal.rightHandContainer = Double.parseDouble(inputArea.getText());
                    mathFinal.result = mathFinal.leftHandContainer + mathFinal.rightHandContainer;
                    inputArea.setText(Double.toString(mathFinal.result));
                    mathFinal.nextOpReset();
                } else {
                    return;
                }
                mathFinal.isPlus = !mathFinal.isPlus;
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!mathFinal.isMinus && !mathFinal.isPlus && !mathFinal.isDivide && !mathFinal.isProduct){
                    mathFinal.leftHandContainer = Double.parseDouble(inputArea.getText());
                    inputArea.setText("");
                } else if (mathFinal.isMinus) {
                    mathFinal.rightHandContainer = Double.parseDouble(inputArea.getText());
                    mathFinal.result = mathFinal.leftHandContainer - mathFinal.rightHandContainer;
                    inputArea.setText(Double.toString(mathFinal.result));
                    mathFinal.nextOpReset();
                } else {
                    return;
                }
                mathFinal.isMinus = !mathFinal.isMinus;
            }
        });

        product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!mathFinal.isProduct && !mathFinal.isMinus && !mathFinal.isDivide && !mathFinal.isPlus){
                    mathFinal.leftHandContainer = Double.parseDouble(inputArea.getText());
                    inputArea.setText("");
                } else if (mathFinal.isProduct) {
                    mathFinal.rightHandContainer = Double.parseDouble(inputArea.getText());
                    mathFinal.result = mathFinal.leftHandContainer * mathFinal.rightHandContainer;
                    inputArea.setText(Double.toString(mathFinal.result));
                    mathFinal.nextOpReset();
                } else {
                    return;
                }
                mathFinal.isProduct = !mathFinal.isProduct;
            }
        });

        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!mathFinal.isDivide && !mathFinal.isMinus && !mathFinal.isPlus && !mathFinal.isProduct){
                    mathFinal.leftHandContainer = Double.parseDouble(inputArea.getText());
                    inputArea.setText("");
                } else if (mathFinal.isDivide) {
                    mathFinal.rightHandContainer = Double.parseDouble(inputArea.getText());
                    mathFinal.result = mathFinal.leftHandContainer / mathFinal.rightHandContainer;
                    inputArea.setText(Double.toString(mathFinal.result));
                    mathFinal.nextOpReset();
                } else {
                    return;
                }
                mathFinal.isDivide = !mathFinal.isDivide;
            }
        });

        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mathFinal.isPlus || mathFinal.isDivide || mathFinal.isMinus || mathFinal.isProduct){
                    if(inputArea.getText().compareTo("") == 0){
                        inputArea.setText("");
                        mathFinal.leftHandContainer = 0;
                        mathFinal.reset();
                        return;
                    }
                    if(mathFinal.isPlus)
                        inputArea.setText(Double.toString(mathFinal.leftHandContainer + Double.parseDouble(inputArea.getText())));
                    else if(mathFinal.isProduct)
                        inputArea.setText(Double.toString(mathFinal.leftHandContainer * Double.parseDouble(inputArea.getText())));
                    else if(mathFinal.isDivide)
                        inputArea.setText(Double.toString(mathFinal.leftHandContainer / Double.parseDouble(inputArea.getText())));
                    else
                        inputArea.setText(Double.toString(mathFinal.leftHandContainer - Double.parseDouble(inputArea.getText())));

                }
                mathFinal.reset();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputArea.getText().isEmpty())
                    return;
                inputArea.setText(inputArea.getText().substring(0,inputArea.getText().length() - 1));
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputArea.setText("");
                mathFinal.leftHandContainer = 0;
                mathFinal.reset();
            }
        });

        makeNegative.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!inputArea.getText().contains("-"))
                    inputArea.setText("-" + inputArea.getText());
                else{
                    inputArea.setText(inputArea.getText().substring(1));
                }
            }
        });
    }

    static class MathComplex{
        double rightHandContainer = 0;
        double leftHandContainer = 0;
        double result = 0;
        boolean isPlus = false,isMinus = false,isProduct = false,isDivide = false;
        public void nextOpReset(){
            this.leftHandContainer = this.result;
            this.result = 0;
            this.rightHandContainer = 0;
        }
        public void reset(){
            this.isPlus = this.isMinus = this.isProduct = this.isDivide = false;
            this.rightHandContainer = this.result = 0;
        }
    }

}