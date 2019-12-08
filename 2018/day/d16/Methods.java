package day.d16;

import java.util.List;

public class Methods {

	public void addr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue(before.get(a).getValue() + before.get(b).getValue());
	}

	public void addi(int op, List<Register> before, int a, int B, int c) {
		before.get(c).setValue(before.get(a).getValue() + B);
	}

	public void mulr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue(before.get(a).getValue() * before.get(b).getValue());
	}

	public void muli(int op, List<Register> before, int a, int B, int c) {
		before.get(c).setValue(before.get(a).getValue() * B);
	}

	public void banr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue(before.get(a).getValue() & before.get(b).getValue());
	}

	public void bani(int op, List<Register> before, int a, int B, int c) {
		before.get(c).setValue(before.get(a).getValue() & B);
	}

	public void borr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue(before.get(a).getValue() | before.get(b).getValue());
	}

	public void bori(int op, List<Register> before, int a, int B, int c) {
		before.get(c).setValue(before.get(a).getValue() | B);
	}

	public void setr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue(before.get(a).getValue());
	}

	public void seti(int op, List<Register> before, int A, int b, int c) {
		before.get(c).setValue(A);
	}

	public void gtir(int op, List<Register> before, int A, int b, int c) {
		before.get(c).setValue((A > before.get(b).getValue()) ? 1 : 0);
	}

	public void gtri(int op, List<Register> before, int a, int B, int c) {
		before.get(c).setValue((before.get(a).getValue() > B) ? 1 : 0);
	}

	public void gtrr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue((before.get(a).getValue() > before.get(b).getValue()) ? 1 : 0);
	}

	public void eqir(int op, List<Register> before, int A, int b, int c) {
		before.get(c).setValue((A == before.get(b).getValue()) ? 1 : 0);
	}

	public void eqri(int op, List<Register> before, int a, int B, int c) {
		before.get(c).setValue((before.get(a).getValue() == B) ? 1 : 0);
	}

	public void eqrr(int op, List<Register> before, int a, int b, int c) {
		before.get(c).setValue((before.get(a).getValue() == before.get(b).getValue()) ? 1 : 0);
	}

}
