package fluentconditionals;

import java.util.Optional;
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

        FluentCondition(boolean condition) {
            this.condition = condition;
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

        void orElseThrowE(RuntimeException e) {
            if (!condition) {
                throw new RuntimeException(e);
            }
        }

        void orElseThrow(Supplier<RuntimeException> runtimeExceptionSupplier) {
            if (!condition) {
                throw runtimeExceptionSupplier.get();
            }
        }
    }
}


