package pl.karollisiewicz.baking.domain;

public interface RecipeRepository {
    Iterable<Recipe> fetchAll();
}
