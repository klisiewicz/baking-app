package pl.karollisiewicz.baking.app.data.source;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.karollisiewicz.baking.domain.Ingredient;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.baking.domain.RecipeStep;
import pl.karollisiewicz.common.collection.CollectionUtils;

final class RecipeMapper {
    private static final RecipeMapper INSTANCE = new RecipeMapper();

    private RecipeMapper() {
    }

    Recipe map(@NonNull final RecipeDto recipe) {
        return new Recipe(recipe.getName(),
                recipe.getServings(),
                recipe.getImage(),
                CollectionUtils.map(recipe.getIngredients(), this::toDomain),
                getSteps(recipe.getSteps())
        );
    }

    private Ingredient toDomain(@NonNull final RecipeIngredientDto ingredient) {
        return new Ingredient(ingredient.getIngredient(),
                ingredient.getQuantity(),
                Ingredient.Measure.fromString(ingredient.getMeasure())
        );
    }

    private Collection<RecipeStep> getSteps(List<RecipeStepDto> steps) {
        final Collection<RecipeStep> recipeSteps = new ArrayList<>(steps.size());
        for (int i = 0; i < steps.size(); i++) {
            recipeSteps.add(getStep(i, steps.get(i)));
        }
        return recipeSteps;
    }

    @NonNull
    private RecipeStep getStep(int index, RecipeStepDto step) {
        return RecipeStep.Builder.withOrdinalNumber(index)
                .setThumbnailUrl(step.getThumbnailURL())
                .setVideoUrl(step.getVideoURL())
                .setFullDescription(step.getDescription())
                .setShortDescription(step.getShortDescription())
                .build();
    }

    public static RecipeMapper getInstance() {
        return INSTANCE;
    }
}
