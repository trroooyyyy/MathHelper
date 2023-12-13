package edu.com;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String question = null;
        boolean condition = true;
        Repository repository = new Repository();
        while (condition){
            System.out.println("1 - Enter the question.\n" + "2 - Get all roots of the question.\n" + "3 - Check the root.");
            int s = sc.nextInt();
            switch (s) {
                case 1 -> {
                    String allowedChars = "x0123456789+-*/().=";
                    Scanner scanner = new Scanner(System.in);
                    while(true){
                        System.out.print("Enter the question: ");
                        question = scanner.nextLine();
                        if(Service.isSignsRight(question) && Service.isBracketsRight(question) && Service.isQuestion(question, allowedChars)){
                            question = question.replaceAll("\\s", "");
                            System.out.println("Question is right. Saving in DB.");
                            repository.save(question);
                            break;
                        }
                        else{
                            System.out.println("Equation is invalid. Try again");
                        }
                    }
                }
                case 2 ->{
                    System.out.println("Enter the root.");
                    double x = sc.nextDouble();
                    System.out.println(Service.findAllBySolution(x));
                }
                case 3 ->{
                    if(question != null){
                        System.out.println("Enter the root for the question:" + question);
                        double x = sc.nextDouble();
                        Service.solution(question,  x);
                    }
                    else{
                        System.out.println("Enter the question. ");
                    }
                }
                case 4 ->{
                    System.out.println("Bye!");
                    condition = false;
                }
                default -> System.out.println("No allowed variants.");
            }
        }


    }
}

