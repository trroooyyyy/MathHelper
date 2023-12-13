package edu.com;

import java.util.Scanner;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class Service {
    public static final Repository repository;

    static {
        try {
            repository = new Repository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Scanner sc = new Scanner(System.in);


    public static boolean isBracketsRight(String equation) {
        int i = 0;
        for (char c : equation.toCharArray()) {
            if (c == '(') {
                i++;
            }
            else if (c == ')') {
                if (i == 0) {
                    return false;
                }
                i--;
                if (i < 0) {
                    return false;
                }
            }
        }
        if(i != 0){
            System.out.println("The brackets are placed incorrect");
        }
        return i == 0;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=';
    }

    public static boolean isQuestion(String question, String allowedChars){
        for (char c : question.toCharArray()) {
            if (allowedChars.indexOf(c) == -1) {
                System.out.println(String.format("Symbol %c are not allowed", c));
                return false;
            }
        }
        return true;
    }

    public static boolean isSignsRight(String equation){
        if(isOperator(equation.charAt(0))||isOperator(equation.charAt(equation.length()-1))){
            System.out.println("Error! Review your signs placing.");
            return false;

        }
        for (int i = 0; i < equation.length() - 1; i++) {
            char currentChar = equation.charAt(i);
            char nextChar = equation.charAt(i + 1);

            if (isOperator(currentChar) && isOperator(nextChar) && nextChar!='-' && nextChar!='+') {
                System.out.println("Error! Review your signs placing.");
                return false;
            }
        }
        return true;
    }
    public static boolean solution(String s, double x) throws SQLException {
        List<String> strings = Arrays.asList(s.split("="));
        String equation = strings.stream().filter(el -> el.contains("x")).findFirst().get();
        String result = strings.stream().filter(el -> !el.contains("x")).findFirst().get();

        DoubleEvaluator eval = new DoubleEvaluator();
        StaticVariableSet<Double> variables = new StaticVariableSet<>();
        variables.set("x", x);
        Double evaluate = eval.evaluate(equation, variables);
        if (evaluate == Double.parseDouble(result) || Math.abs(evaluate - Double.parseDouble(result)) <= 1.0e-9){
            System.out.println("The root is correct.");
            repository.add(s, x);
            return true;
        }
        else{
            System.out.println("The root is incorrect.");
            return false;
        }
    }
    public static List<Question> findAllBySolution(double x) throws SQLException {
        return repository.findAll(x);
    }




}