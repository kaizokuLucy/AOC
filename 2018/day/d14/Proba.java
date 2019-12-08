package day.d14;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Proba {

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		
		ListIterator<Integer> it1 = list.listIterator();
		ListIterator<Integer> it2 = list.listIterator();
		
		it1.add(666);
		
		while(it2.hasNext()) {
			System.out.println(it2.next());
		}
	}
}
