package fluentconditionals;

import static fluentconditionals.FluentConditionals.*;
//Task 4
class IfThenReturnElseThrow {

    public static void main(String[] args) {

        int resultLow = when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::getLowNumber)
                .orElseThrowE(new RuntimeException());
        System.out.println(resultLow); // 1

        int resultOther = when(!TestHelper.somethingIsTrue())
                .thenReturn(TestHelper::getLowNumber)
                .orElseThrow(RuntimeException::new);
        // Exception is thrown
    }
}
