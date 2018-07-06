/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horriblecalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Yongcong Uses the Shunting-yard Algorithm to convert infix expression
 * to postfix expression then calculate postfix expression by using stack
 */
public class Calculate {

    HashMap<Character, Integer> precedence;
    String expression;

    public Calculate(String expression) {
        this.expression = expression;
        precedence = new HashMap<>();
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('+', 1);
        precedence.put('-', 1);
    }

    public double calculateExpression() {
        String[] expressions = expression.split(" ");
        ArrayList<Object> postfix = postFix(expressions);
        Stack<Double> output = new Stack<>();
        for (int i = 0; i < postfix.size(); i++) {
            if (postfix.get(i) instanceof Double) {
                Double temp = (Double) postfix.get(i);
                output.push(temp);
            } else {
                double second = output.pop();
                double first = output.pop();
                char operator = (Character) postfix.get(i);
                double result = operatorOperation(operator, first, second);
                output.push(result);
            }
        }
        return output.pop();
    }

    public double operatorOperation(Character operator, double first, double second) {
        switch (operator) {
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '+':
                return first + second;
            case '-':
                return first - second;
            default:
                return 0;
        }
    }

    public ArrayList<Object> postFix(String[] expressions) {
        Stack<Character> operatorStack = new Stack<>();
        ArrayList<Object> output = new ArrayList<>();
        for (int i = 0; i < expressions.length; i++) {
            if (isDouble(expressions[i])) {
                output.add(Double.parseDouble(expressions[i]));
            } else {
                while (!operatorStack.isEmpty() && precedence.get(operatorStack.peek()) > precedence.get(expressions[i].charAt(0))) {
                    output.add(operatorStack.pop());
                }
                operatorStack.push(expressions[i].charAt(0));
            }
        }
        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }
        return output;
    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
