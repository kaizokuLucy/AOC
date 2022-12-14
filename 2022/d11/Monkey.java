package d11;

import java.util.List;
import java.util.function.IntBinaryOperator;

public class Monkey {

    private final int name;
    private List<String> startingItems;
    private final IntBinaryOperator operator;
    private final int arg1;
    private final int arg2;
    private final int divisor;
    private final int divisorTrue;
    private final int divisorFalse;

    public Monkey(final int name,
                  List<String> startingItems,
                  final IntBinaryOperator operator,
                  final int arg1,
                  final int arg2,
                  final int divisor,
                  final int divisorTrue,
                  final int divisorFalse) {
        this.name = name;
        this.operator = operator;
        this.startingItems = startingItems;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.divisor = divisor;
        this.divisorTrue = divisorTrue;
        this.divisorFalse = divisorFalse;
    }

    public int operation () {
        return this.operator.applyAsInt(this.arg1, this.arg2);
    }
}
