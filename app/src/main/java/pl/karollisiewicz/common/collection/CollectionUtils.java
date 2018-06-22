package pl.karollisiewicz.common.collection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;

import pl.karollisiewicz.common.function.Function;
import pl.karollisiewicz.common.function.Predicate;

public final class CollectionUtils {
    private CollectionUtils() {
        // No instances should be crated.
    }

    @Nullable
    public static <E> E find(Collection<E> collection, Predicate<? super E> predicate) {
        for (E element: collection)
            if (predicate.test(element))
                return element;
        return null;
    }

    public static <E> Collection<E> filter(Collection<E> unfiltered, Predicate<? super E> predicate) {
        final Collection<E> filtered = new ArrayList<>();
        for (E element: unfiltered)
            if (predicate.test(element))
                filtered.add(element);
        return  filtered;
    }

    public static <F,T> Collection<T> map(Collection<F> fromCollection, Function<? super F,T> function) {
        final Collection<T> transformed = new ArrayList<>();
        for (F element: fromCollection)
            transformed.add(function.apply(element));

        return transformed;
    }

    public static <E> Collection<E> from(@NonNull final Iterable<E> iterable) {
        final Collection<E> list = new ArrayList<>();
        for (E item : iterable) {
            list.add(item);
        }
        return list;
    }
}
