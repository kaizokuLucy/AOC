package day.d14;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	
	List<Chemical> ingredients = new ArrayList<>();
	Chemical result;
	
	public Recipe(List<Chemical> ingredients, Chemical result) {
		this.ingredients = ingredients;
		this.result = result;
	}

	@Override
	public String toString() {
		return ingredients + " => " + result;
	}	
}
