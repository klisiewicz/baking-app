package pl.karollisiewicz.baking.app.data.source;

import android.support.annotation.NonNull;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.karollisiewicz.baking.app.data.source.web.RecipesService;
import pl.karollisiewicz.baking.domain.Ingredient;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.baking.domain.RecipeRepository;
import pl.karollisiewicz.baking.domain.RecipeStep;
import pl.karollisiewicz.common.collection.CollectionUtils;

import static java.util.Collections.emptyList;
import static org.androidannotations.annotations.EBean.Scope.Singleton;

@EBean(scope = Singleton)
public class RecipeRestRepository implements RecipeRepository {

    @RestService
    RecipesService recipesService;

    @Override
    public Iterable<Recipe> fetchAll() {
        final RecipeMapper recipeMapper = new RecipeMapper();
        final Collection<RecipeDto> recipesDto = recipesService.fetchAll();
        return recipesDto != null ? CollectionUtils.map(recipesDto, recipeMapper::map) : emptyList();
    }

    private static final class RecipeMapper {
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
            return RecipeStep.Builder.withOrdinalNumber(index + 1)
                    .setThumbnailUrl(step.getThumbnailURL())
                    .setVideoUrl(step.getVideoURL())
                    .setFullDescription(step.getDescription())
                    .setShortDescription(step.getShortDescription())
                    .build();
        }
    }
}
