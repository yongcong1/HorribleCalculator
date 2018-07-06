/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horriblecalculator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Yongcong
 */
public class Calculate {
    HashMap<Character, Integer> precedence;
    String expression;
    public Calculate(String expression){
        this.expression = expression;
        precedence = new HashMap<>();
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('+', 1);
        precedence.put('-', 1);
    }
    
    public double calculateExpression(){
        Stack<Character> operator = new Stack<>();
        Queue<Character> outputOperator = new LinkedList<>();
        Stack<Double> output = new Stack<>();
        String[] expressions = expression.split(" ");
        prepareStructure(operator, outputOperator, output, expressions);
        while(!outputOperator.isEmpty()){
            double second = output.pop();
            double first = output.pop();
            double result = operatorOperation(outputOperator.remove(), first, second);
            output.push(result);
        }
        return output.pop();
    }
    
    public double calculateExpression(String expression){
        Stack<Character> operator = new Stack<>();
        Queue<Character> outputOperator = new LinkedList<>();
        Stack<Double> output = new Stack<>();
        String[] expressions = expression.split(" ");
        prepareStructure(operator, outputOperator, output, expressions);
        while(!outputOperator.isEmpty()){
            double second = output.pop();
            double first = output.pop();
            double result = operatorOperation(outputOperator.remove(), first, second);
            output.push(result);
        }
        return output.pop();
    }
    
    public double operatorOperation(Character operator, double first, double second){
        switch(operator){
            case '*': return first * second;
            case '/': return first / second;
            case '+': return first + second;
            case '-': return first - second;
            default: return 0;
        }
    }
    
    public void prepareStructure(Stack<Character> operator, Queue<Character> outputOperator, 
            Stack<Double> output, String[] expressions){
        for(int i=0; i<expressions.length; i++){
            if(isDouble(expressions[i])){
                output.push(Double.parseDouble(expressions[i]));
            }
            else{
                while(!operator.isEmpty() && precedence.get(operator.peek())>precedence.get(expressions[i].charAt(0))){
                    outputOperator.add(operator.pop());
                }
                operator.push(expressions[i].charAt(0));
            }
        }
        while(!operator.isEmpty()){
            outputOperator.add(operator.pop());
        }
    }
    
    public boolean isDouble(String str){
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }
}
