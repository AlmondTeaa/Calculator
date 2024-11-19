package Calculator;

public class Main {
    public static void main(String[] args) {
        CalculatorGUI calc = new CalculatorGUI();
        CalculatorController controller = new CalculatorController(calc);
    }
}
