import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    
    // Declare buttons and text field
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton clearButton, equalButton, decimalButton;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";

    public Calculator() {
        // Frame settings
        setTitle("Simple Calculator");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        // Text field to display input
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        add(buttonPanel, BorderLayout.CENTER);

        // Define number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        // Define operation buttons
        operationButtons = new JButton[4];
        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].addActionListener(this);
            buttonPanel.add(operationButtons[i]);
        }

        // Define other buttons
        clearButton = new JButton("C");
        equalButton = new JButton("=");
        decimalButton = new JButton(".");

        clearButton.addActionListener(this);
        equalButton.addActionListener(this);
        decimalButton.addActionListener(this);

        buttonPanel.add(clearButton);
        buttonPanel.add(equalButton);
        buttonPanel.add(decimalButton);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) == 'C') {
            currentInput = "";
            previousInput = "";
            operator = "";
        } else if (command.charAt(0) == '=') {
            if (!previousInput.isEmpty() && !currentInput.isEmpty()) {
                double num1 = Double.parseDouble(previousInput);
                double num2 = Double.parseDouble(currentInput);
                double result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                previousInput = "";
                operator = "";
            }
        } else if (command.charAt(0) == '.') {
            if (!currentInput.contains(".")) {
                currentInput += ".";
            }
        } else if (command.charAt(0) == '+' || command.charAt(0) == '-' || 
                   command.charAt(0) == '*' || command.charAt(0) == '/') {
            if (!currentInput.isEmpty()) {
                if (!previousInput.isEmpty()) {
                    double num1 = Double.parseDouble(previousInput);
                    double num2 = Double.parseDouble(currentInput);
                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }
                    previousInput = String.valueOf(result);
                    currentInput = "";
                    operator = command;
                    display.setText(previousInput);
                }
            }
        } else {
            currentInput += command;
            display.setText(currentInput);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
