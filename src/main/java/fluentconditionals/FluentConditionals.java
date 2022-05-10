package fluentconditionals;

import java.util.function.Supplier;

/**
 * @author Kevin Nowak
 */
interface FluentConditionals {

    static FluentCondition when(boolean b) {
        return new FluentCondition(b);
    }

    static FluentCondition when(Supplier<Boolean> supplier) {
        return new FluentCondition(supplier.get());
    }

    Runnable doNothing = () -> {};

    class FluentCondition {
        Boolean condition;
        int returnInt;

        FluentCondition(boolean condition) {
            this.condition = condition;
        }

        public FluentCondition(Boolean condition, int returnInt) {
            this.condition = condition;
            this.returnInt = returnInt;
        }

        FluentCondition then(Runnable runnable) {
            if (condition) {
                runnable.run();
            }
            return new FluentCondition(condition);
        }

        void orElse(Runnable runnable) {
            if (!condition) {
                runnable.run();
            }
        }

        int orElseThrowE(RuntimeException e) {
            if (!condition) {
                throw new RuntimeException(e);
            }
            return returnInt;
        }

        int orElseThrow(Supplier<RuntimeException> runtimeExceptionSupplier) {
            if (!condition) {
                throw runtimeExceptionSupplier.get();
            }
            return returnInt;
        }

        FluentCondition thenReturn(Supplier<Integer> supplier) {
            if (condition) {
                returnInt = supplier.get();
            }
            return new FluentCondition(condition, returnInt);
        }

        int orElse(Supplier<Integer> supplier) {
            if (!condition) {
                returnInt = supplier.get();
            }
            return returnInt;
        }

        int orElse(int i) {
            if (!condition) {
                returnInt = i;
            }
            return returnInt;
        }
    }
}


