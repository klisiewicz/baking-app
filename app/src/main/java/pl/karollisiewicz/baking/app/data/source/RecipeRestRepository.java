package pl.karollisiewicz.baking.app.data.source;

import android.support.annotation.NonNull;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Collection;

import pl.karollisiewicz.baking.app.data.source.web.RecipesService;
import pl.karollisiewicz.baking.domain.Ingredient;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.baking.domain.RecipeRepository;
import pl.karollisiewicz.common.collection.CollectionUtils;

import static java.util.Collections.emptyList;
import static org.androidannotations.annotations.EBean.Scope.Singleton;

@EBean(scope = Singleton)
public class RecipeRestRepository implements RecipeRepository {

    @RestService
    RecipesService recipesService;

    @Override
    public Iterable<Recipe> fetchAll() {
        final Collection<RecipeDto> recipesDto = recipesService.fetchAll();
        return recipesDto != null ? CollectionUtils.map(recipesDto, this::toDomain) : emptyList();
    }

    private Recipe toDomain(@NonNull final RecipeDto recipe) {
        return new Recipe(recipe.getName(),
                recipe.getServings(),
                recipe.getImage(),
                CollectionUtils.map(recipe.getIngredients(), this::toDomain),
                emptyList()
        );
    }

    private Ingredient toDomain(@NonNull final RecipeIngredientDto ingredient) {
        return new Ingredient(ingredient.getIngredient(), ingredient.getQuantity(), Ingredient.Measure.UNDEFINED);
    }
}
