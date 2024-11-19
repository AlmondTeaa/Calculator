package Calculator;

public class Calculator {

    public static double evaluate (String expression){
        String [] numbers = expression.split("\\+|~|\\*|\\/|%");
        double doubleResult = 0;
        double num1 = 0;
        double num2 = 0;
        if (numbers.length == 2){
            num1 = Double.parseDouble(numbers[0]);
            num2 = Double.parseDouble(numbers[1]);
            if (expression.contains("+")) {
                doubleResult = num1 + num2;
            } else if (expression.contains("~")) {
                doubleResult = num1 - num2; 
            } else if (expression.contains("*")) {
                doubleResult = num1 * num2; 
            } else if (expression.contains("/")) {
                doubleResult = num1 / num2;
            } else if (expression.contains("%")) {
                doubleResult = num1 % num2; 
            }
            return doubleResult;
      }
      else {
        return num1 + num1;
      }
        
    }

    public static double reciprocal (double expression){
        Double reciprocal = 1 / expression;
        return reciprocal; 
    }

    public static double sqr (double expression){
        Double sqr = Math.pow(expression, 2);
        return sqr; 
    }

    public static double sqrT (double expression){
        Double sqrt = Math.sqrt(expression);
        return sqrt; 
    }
}
