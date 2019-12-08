package day.five;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainTwo {
    static String line = "";
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/day/five/input.txt");


        try (Scanner sc = new Scanner(file)){
            line = sc.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        List<Integer> list = new ArrayList<>();
        int size;
        for(char c = 'a'; c <= 'z'; c++) {
            String linePom = line.replaceAll("["+c+Character.toUpperCase(c)+"]", "");
            size = getSize(linePom);
            System.out.println(c + ": " + size);
            list.add(size);
        }
        System.out.println("minimum: " + Collections.min(list));
    }

    static int getSize(String linePom) {
        //System.out.println(linePom);
        Stack<Character> stack = new Stack<>();
        stack.push('\0');
        char pom;
        for(int i = 0; i < linePom.length(); i++) {
            pom = linePom.charAt(i);
            if (Math.abs(stack.peek() - pom) == 32) {
                stack.pop();
            } else {
                stack.push(pom);
            }
        }
        return (stack.size()-1);
    }
}
