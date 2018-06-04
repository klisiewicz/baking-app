package pl.karollisiewicz.baking.app.ui;

import java.util.List;

import pl.karollisiewicz.baking.domain.Recipe;

import static java.util.Collections.emptyList;

public final class RecipesViewState {
    private final boolean isLoading;
    private final List<Recipe> recipes;

    private RecipesViewState(boolean isLoading, List<Recipe> recipes) {
        this.isLoading = isLoading;
        this.recipes = recipes;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public static RecipesViewState loading() {
        return new RecipesViewState(true, emptyList());
    }

    public static RecipesViewState success(List<Recipe> recipes) {
        return new RecipesViewState(false, recipes);
    }
}
