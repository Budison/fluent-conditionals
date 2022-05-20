package fluentconditionals;

import static fluentconditionals.FluentConditionals.*;
//Task 3
class IfElseThenReturn {

    public static void main(String[] args) {

        var resultHigh = when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);
        System.out.println(resultHigh); // 1_000

        var resultZero = when(!TestHelper.somethingIsTrue())
                .thenReturn(TestHelper::getHighNumber)
                .orElse(0);
        System.out.println(resultZero); // 0

    }
}
