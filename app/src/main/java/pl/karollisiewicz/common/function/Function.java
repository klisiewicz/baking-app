package pl.karollisiewicz.common.function;

import android.support.annotation.NonNull;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);

    default <V> Function<V, R> compose(@NonNull Function<? super V, ? extends T> before) {
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(@NonNull Function<? super R, ? extends V> after) {
        return (T t) -> after.apply(apply(t));
    }

    static <T> Function<T, T> identity() {
        return t -> t;
    }
}
