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

    class FluentCondition<T> {
        Boolean condition;
        T t;

        FluentCondition(boolean condition) {
            this.condition = condition;
        }

        public FluentCondition(Boolean condition, T t) {
            this.condition = condition;
            this.t = t;
        }

        FluentCondition<T> then(Runnable runnable) {
            if (condition) {
                runnable.run();
            }
            return new FluentCondition<>(condition);
        }

        void orElse(Runnable runnable) {
            if (!condition) {
                runnable.run();
            }
        }

        T orElseThrowE(RuntimeException e) {
            if (!condition) {
                throw new RuntimeException(e);
            }
            return t;
        }

        T orElseThrow(Supplier<RuntimeException> runtimeExceptionSupplier) {
            if (!condition) {
                throw runtimeExceptionSupplier.get();
            }
            return t;
        }

        FluentCondition<T> thenReturn(Supplier<T> supplier) {
            if (condition) {
                t = supplier.get();
            }
            return new FluentCondition<>(condition, t);
        }

        FluentCondition<T> thenReturn(T t) {
            if (condition) {
                this.t = t;
            }
            return new FluentCondition<>(condition, this.t);
        }

        T orElse(Supplier<T> supplier) {
            if (!condition) {
                t = supplier.get();
            }
            return t;
        }

        T orElse(T t) {
            if (!condition) {
                this.t = t;
            }
            return this.t;
        }
    }
}
