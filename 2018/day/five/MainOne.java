package day.five;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MainOne {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/day/five/input.txt");

        String line = "";
        try (Scanner sc = new Scanner(file)) {
            line = sc.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        Stack<Character> stack = new Stack<>();
        stack.push('#');
        char pom;
        for(int i = 0; i < line.length(); i++) {
            pom = line.charAt(i);
            if (Math.abs(stack.peek() - pom) == 32) {
                stack.pop();
            } else {
                stack.push(pom);
            }
        }
        System.out.println(stack.size()-1);


    }
}
