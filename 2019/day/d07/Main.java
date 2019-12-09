//package day.d07;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//public class Main {
//	public static void main(String[] args) {		
//		File file = new File("2019/day/d07/input.txt");
//
//		try {
//			Scanner sc = new Scanner(file);
//			String input = sc.nextLine();
//			sc.close();
//
//			List<Integer> instructions = Arrays.asList(input.split(",")).stream().map(Integer::parseInt)
//					.collect(Collectors.toList());
//			Amplifier amp = new Amplifier(instructions);
//			
//			int[] comb = {0,1,2,3,4};
//			List<int[]> combinations = new ArrayList<>();
//			permute(comb, 0, combinations);
//			
//			List<Integer> amplifiedValues = new ArrayList<>();
//			
//			for (int i = 0; i < combinations.size(); i++) {
//				int[] data = new int[2];
//				data[1] = 0;
//				for (int j = 0; j < 5; j++) {
//					data[0] = combinations.get(i)[j];
//					amp.runProgram(data);
//					data[1] = amp.getResult();
//				}
//				amplifiedValues.add(data[1]);
//			}
//			
//			System.out.println(amplifiedValues.stream().reduce(Integer::max).get());
//
//			
//		} catch (FileNotFoundException e) {
//			System.out.println("file no existy");
//		}
//	}
//
//	private static void permute(int[] comb, int index, List<int[]> combinations){
//	    if(index >= comb.length - 1){
//	    	int[] a = new int[5];
//	    	System.arraycopy(comb, 0, a, 0, 5);
//	        combinations.add(a);
//	        return;
//	    }
//
//	    for(int i = index; i < comb.length; i++){
//
//	        int t = comb[index];
//	        comb[index] = comb[i];
//	        comb[i] = t;
//
//	        permute(comb, index+1, combinations);
//
//	        t = comb[index];
//	        comb[index] = comb[i];
//	        comb[i] = t;
//	    }
//	}
//}
