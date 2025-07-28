//Actividad de calculadora cientifica
//Kevin Antonio Molina Cruz MC251958


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraCientifica extends JFrame implements ActionListener {

    private JTextField display;
    private double primerNumero = 0;
    private String operador = "";
    private boolean nuevaOperacion = true; // indica si se debe limpiar display al ingresar nuevo número

    public CalculadoraCientifica() {

        // Configuración básica de la ventana
        setTitle("Calculadora Científica");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Campo para mostrar números
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);


        // Panel para contener todos los botones, con grilla de 7 filas y 4 columnas
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 4, 5, 5));

        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "CE", "+",
                "sin", "cos", "tan", "√",
                "log", "bin", "dec", "hex",
                "", "", "", "="
        };

        // Creamos los botones y los agregamos al panel
        for (String texto : botones) {
            if (!texto.equals("")) {
                JButton btn = new JButton(texto);
                btn.setFont(new Font("Arial", Font.PLAIN, 16));
                btn.addActionListener(this);
                panel.add(btn);
            } else {
                panel.add(new JLabel(""));
            }
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true); // Mostrar ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand(); // Saber qué botón fue presionado

        try {
            if ("0123456789.".contains(cmd)) {
                if (nuevaOperacion) {
                    display.setText("");
                    nuevaOperacion = false;
                }
                display.setText(display.getText() + cmd); // Agrega el número o punto al display

            } else if ("+-*/".contains(cmd)) {
                // guardamos el número y operador, para calcular después
                primerNumero = Double.parseDouble(display.getText());
                operador = cmd;
                nuevaOperacion = true;
            } else {
                switch (cmd) {
                    case "=":

                        // Cuando presionás igual, tomamos el segundo número y hacemos la operación
                        double segundoNumero = Double.parseDouble(display.getText());
                        double resultado = calcular(primerNumero, segundoNumero, operador);
                        if (resultado == (int) resultado)
                            // mostrar sin decimales si es número entero
                            display.setText(String.valueOf((int) resultado));
                        else
                            display.setText(String.valueOf(resultado));
                        nuevaOperacion = true;
                        break;

                    // Funciones científicas usan radianes, por eso convertimos grados a radianes
                    case "sin":
                        display.setText(String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(display.getText())))));
                        nuevaOperacion = true;
                        break;
                    case "cos":
                        display.setText(String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(display.getText())))));
                        nuevaOperacion = true;
                        break;
                    case "tan":
                        display.setText(String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(display.getText())))));
                        nuevaOperacion = true;
                        break;
                    case "√":
                        display.setText(String.valueOf(Math.sqrt(Double.parseDouble(display.getText()))));
                        nuevaOperacion = true;
                        break;
                    case "log":
                        display.setText(String.valueOf(Math.log(Double.parseDouble(display.getText()))));
                        nuevaOperacion = true;
                        break;
                    case "bin":
                        display.setText(Integer.toBinaryString((int) Double.parseDouble(display.getText())));
                        nuevaOperacion = true;
                        break;
                    case "dec":
                        display.setText(String.valueOf(Integer.parseInt(display.getText(), 2)));
                        nuevaOperacion = true;
                        break;
                    case "hex":
                        display.setText(Integer.toHexString((int) Double.parseDouble(display.getText())));
                        nuevaOperacion = true;
                        break;
                    case "CE":
                        display.setText("");
                        primerNumero = 0;
                        operador = "";
                        nuevaOperacion = true;
                        break;
                }
            }
        } catch (Exception ex) {
            display.setText("Error");
            nuevaOperacion = true;
        }
    }

    // Método que recibe dos números y el operador para hacer la cuenta y regresar el resultado
    private double calcular(double num1, double num2, String op) {
        switch (op) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) throw new ArithmeticException("División por cero");
                return num1 / num2;
            default: return num2;
        }
    }

    public static void main(String[] args) {
        new CalculadoraCientifica();
    }
}