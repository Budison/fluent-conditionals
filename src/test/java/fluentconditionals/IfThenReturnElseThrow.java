package fluentconditionals;

import static fluentconditionals.FluentConditionals.*;
//Task 3
class IfElseThenReturn {

    public static void main(String[] args) {

        int resultHigh = when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);
        System.out.println(resultHigh); // 1_000

        int resultZero = when(!TestHelper.somethingIsTrue())
                .thenReturn(TestHelper::getHighNumber)
                .orElse(0);
        System.out.println(resultZero); // 0

    }
}
