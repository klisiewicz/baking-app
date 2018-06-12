package pl.karollisiewicz.baking.domain;

import android.support.annotation.NonNull;

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
        CUP, TABLESPOON, TEASPOON, KILOGRAM, GRAM, OUNCE, UNIT, UNDEFINED
    }
}
