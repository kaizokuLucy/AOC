package day.d14;

public class Chemical {

	private String name;
	private int amount;

	public Chemical(String[] data) {
		this.name = data[1];
		this.amount = Integer.parseInt(data[0]);
	}
	
	public Chemical(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public Chemical(Chemical chemical) {
		this.name = chemical.getName();
		this.amount = chemical.getAmount();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chemical other = (Chemical) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return name + " " + amount;
	}

	
}
