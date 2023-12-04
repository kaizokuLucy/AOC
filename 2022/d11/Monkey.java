package d11;

import java.math.BigInteger;
import java.util.List;

public class Monkey {

    private final String name;
    private List<BigInteger> startingItems;
    private final String operation;
    private final String arg1;
    private final String arg2;
    private final BigInteger divisor;
    private final Integer divisorTrue;
    private final Integer divisorFalse;
    private Integer itemsChecked;

    public Monkey(final String name,
                  List<BigInteger> startingItems,
                  String operation,
                  final String arg1,
                  final String arg2,
                  final BigInteger divisor,
                  final Integer divisorTrue,
                  final Integer divisorFalse,
                  Integer itemsChecked) {
        this.name = name;
        this.operation = operation;
        this.startingItems = startingItems;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.divisor = divisor;
        this.divisorTrue = divisorTrue;
        this.divisorFalse = divisorFalse;
        this.itemsChecked = itemsChecked;
    }

    public String getName() {
        return name;
    }

    public List<BigInteger> getStartingItems() {
        return startingItems;
    }

    public void setStartingItems(List<BigInteger> startingItems) {
        this.startingItems = startingItems;
    }

    public String getOperation() {
        return operation;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public BigInteger getDivisor() {
        return divisor;
    }

    public Integer getDivisorTrue() {
        return divisorTrue;
    }

    public Integer getDivisorFalse() {
        return divisorFalse;
    }

    public Integer getItemsChecked() {
        return itemsChecked;
    }

    public void setItemsChecked(Integer itemsChecked) {
        this.itemsChecked = itemsChecked;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", startingItems=" + startingItems +
                ", operation='" + operation + '\'' +
                ", arg1='" + arg1 + '\'' +
                ", arg2='" + arg2 + '\'' +
                ", divisor='" + divisor + '\'' +
                ", divisorTrue='" + divisorTrue + '\'' +
                ", divisorFalse='" + divisorFalse + '\'' +
                '}';
    }
}
