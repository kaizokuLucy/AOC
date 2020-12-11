package AOC20_d08;

public class Instruction {
    private String operation;
    private int argument;

    public Instruction(String operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "operation='" + operation + '\'' +
                ", argument=" + argument +
                '}';
    }

    public String getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
