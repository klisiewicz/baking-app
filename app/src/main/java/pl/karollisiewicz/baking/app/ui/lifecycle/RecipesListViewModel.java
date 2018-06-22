package pl.karollisiewicz.baking.app.ui.lifecycle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import pl.karollisiewicz.baking.app.ui.recipe.list.RecipesViewState;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.baking.domain.RecipeRepository;
import pl.karollisiewicz.common.collection.CollectionUtils;

@EBean
public class RecipesListViewModel extends ViewModel {

    RecipeRepository recipeRepository;

    private MutableLiveData<RecipesViewState> recipes = new MutableLiveData<>();

    public LiveData<RecipesViewState> getRecipes() {
        return recipes;
    }

    public void fetchRecipes() {
        recipes.postValue(RecipesViewState.loading());

        final Iterable<Recipe> fetched = recipeRepository.fetchAll();
        recipes.postValue(RecipesViewState.success(new ArrayList<>(CollectionUtils.from(fetched))));
    }
}
