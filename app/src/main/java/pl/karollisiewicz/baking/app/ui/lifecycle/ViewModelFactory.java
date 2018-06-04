package pl.karollisiewicz.baking.app.ui.lifecycle;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.karollisiewicz.baking.app.data.source.RecipeRestRepository;
import pl.karollisiewicz.baking.domain.RecipeRepository;

@EBean
public class ViewModelFactory implements ViewModelProvider.Factory {

    @Bean(RecipeRestRepository.class)
    RecipeRepository recipeRepository;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipesViewModel.class)) {
            final RecipesViewModel recipesViewModel = new RecipesViewModel();
            recipesViewModel.recipeRepository = recipeRepository;
            return (T) recipesViewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
