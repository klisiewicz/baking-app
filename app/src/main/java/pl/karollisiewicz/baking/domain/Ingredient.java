package pl.karollisiewicz.baking.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.karollisiewicz.common.collection.CollectionUtils;

import static java.util.Arrays.asList;

public final class Ingredient {
    private final String name;
    private final double quantity;
    private final Measure measure;

    public Ingredient(@NonNull final String name, final double quantity, @NonNull final Measure measure) {
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    @NonNull
    public Measure getMeasure() {
        return measure;
    }

    public enum Measure {
        CUP("CUP"),
        TABLESPOON("TBLSP"),
        TEASPOON("TSP"),
        KILOGRAM("K"),
        GRAM("G"),
        OUNCE("OZ"),
        UNIT("UNIT"),
        UNDEFINED("UNDEF");

        private final String shortName;

        Measure(final String shortName) {
            this.shortName = shortName;
        }

        public static Measure fromString(@Nullable final String shortName) {
            final Measure measure = CollectionUtils.find(asList(Measure.values()), it -> it.shortName.equals(shortName));
            return measure != null ? measure : Measure.UNDEFINED;
        }
    }
}
