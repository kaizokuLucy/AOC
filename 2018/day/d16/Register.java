package day.d16;

public class Register {

	private int value;
	
	public Register(int value) {
		this.value = value;
	}
	
	public Register(Register r) {
		this.value = r.getValue();
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Register value = " + value;
	}
	
	
	
}
