package pl.karollisiewicz.baking.domain;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableList;

public final class Recipe {
    private final String name;
    private final String servings;
    private final String imageUrl;
    private final Collection<Ingredient> ingredients;
    private final List<RecipeStep> steps;

    public Recipe(@NonNull final String name,
                  @NonNull final String servings,
                  @NonNull final String imageUrl,
                  @NonNull final Collection<Ingredient> ingredients,
                  @NonNull final Collection<RecipeStep> steps) {
        this.name = name;
        this.servings = servings;
        this.imageUrl = imageUrl;
        this.ingredients = unmodifiableCollection(ingredients);
        this.steps = unmodifiableList(new ArrayList<>(steps));
    }

    public String getName() {
        return name;
    }

    public String getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Iterable<Ingredient> getIngredients() {
        return ingredients;
    }

    public Iterable<RecipeStep> getSteps() {
        return steps;
    }

    public int getStepsNumber() {
        return steps.size();
    }

    public RecipeStep getStepAt(int index) {
        return steps.get(index);
    }
}
