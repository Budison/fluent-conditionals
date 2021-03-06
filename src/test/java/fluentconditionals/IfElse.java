package fluentconditionals;

import static fluentconditionals.FluentConditionals.*;

//Task 1
class IfElse {

    public static void main(String[] args) {

        when(TestHelper.somethingIsTrue())
                .then(TestHelper::printBar)
                .orElse(TestHelper::printFoo);
        //'Bar' printed to console

        when(TestHelper::somethingIsTrue)
                .then(TestHelper::printBar)
                .orElse(TestHelper::printFoo);
        //'Bar' printed to console

        when(!TestHelper.somethingIsTrue())
                .then(TestHelper::printBar)
                .orElse(doNothing);
        //nothing printed to console
    }
}
