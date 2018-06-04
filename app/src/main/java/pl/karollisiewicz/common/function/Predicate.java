package pl.karollisiewicz.common.function;

import android.support.annotation.NonNull;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(@NonNull Predicate<? super T> other) {
        return t -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return t -> !test(t);
    }

    default Predicate<T> or(@NonNull Predicate<? super T> other) {
        return t -> test(t) || other.test(t);
    }
}

