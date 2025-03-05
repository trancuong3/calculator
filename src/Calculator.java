import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField screen;
    private String currentOperator = "";
    private double firstNumber = 0, secondNumber = 0;
    private boolean operatorPressed = false;

    public Calculator() {
        setTitle("Calculator");
        setSize(200, 300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(true);

        screen = new JTextField(5);
        screen.setEditable(true);
        screen.setHorizontalAlignment(JTextField.LEFT);
        screen.setFont(new Font("Arial", Font.PLAIN, 10));
        add(screen);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 10));
            button.setBackground(Color.GRAY);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.matches("[0-9]") || actionCommand.equals(".")) {
            if (operatorPressed) {
                screen.setText("");
                operatorPressed = false;
            }
            screen.setText(screen.getText() + actionCommand);
        } else if (actionCommand.equals("C")) {
            screen.setText("0");
            firstNumber = secondNumber = 0;
            currentOperator = "";
        } else if (actionCommand.equals("=")) {
            if (!currentOperator.isEmpty() && !screen.getText().isEmpty()) {
                secondNumber = Double.parseDouble(screen.getText());
                double result = performCalculation(firstNumber, secondNumber, currentOperator);
                screen.setText(String.valueOf(result));
                currentOperator = "";
            }
        } else {
            firstNumber = Double.parseDouble(screen.getText());
            currentOperator = actionCommand;
            operatorPressed = true;
        }
    }

    private double performCalculation(double a, double b, String op) {
        switch (op) {
            case "-": return a - b;
            case "*": return a * b;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
