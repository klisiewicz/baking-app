package pl.karollisiewicz.baking.app.ui.recipe.list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipesListViewModel;
import pl.karollisiewicz.baking.app.ui.lifecycle.ViewModelFactory;
import pl.karollisiewicz.baking.app.ui.navigation.NavigationFragment;
import pl.karollisiewicz.baking.app.ui.recipe.details.RecipeFragment_;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.common.ui.ActionBarBuilder;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static android.view.View.GONE;
import static java.util.Collections.emptyList;

@EFragment(R.layout.fragment_recipes)
public class RecipesFragment extends NavigationFragment {

    @ViewById
    ProgressBar progress;

    @ViewById(value = R.id.recipes_list)
    RecyclerView recipesList;

    @Bean
    RecipesAdapter adapter;

    @Bean
    ViewModelFactory viewModelFactory;

    private RecipesListViewModel recipesListViewModel;

    private RecipeDetailsViewModel recipeDetailsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipesListViewModel = ViewModelProviders.of(getAppCompatActivity(), viewModelFactory)
                .get(RecipesListViewModel.class);
        recipeDetailsViewModel  = ViewModelProviders.of(getAppCompatActivity())
                .get(RecipeDetailsViewModel.class);
    }

    @AfterViews
    void onViewInjected() {
        setupActionBar();
        setupRecipesList();
        subscribeForRecipes();
    }


    private void setupActionBar() {
        setupActionBar(ActionBarBuilder.withView(R.id.toolbar)
                .setTitle(getString(R.string.recipes))
        );
    }

    private void setupRecipesList() {
        adapter.setRecipeClickListener(recipe -> {
            recipeDetailsViewModel.select(recipe);
            navigateTo(RecipeFragment_.builder().build());
        });
        recipesList.setAdapter(adapter);
        recipesList.setHasFixedSize(true);
        recipesList.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
    }

    private void subscribeForRecipes() {
        recipesListViewModel.getRecipes().observe(getViewLifecycleOwner(), it -> {
            if (it != null) showRecipes(it.getRecipes());
            else showRecipes(emptyList());
        });

        fetchRecipes();
    }

    @Background
    void fetchRecipes() {
        recipesListViewModel.fetchRecipes();
    }

    @UiThread
    void showRecipes(@NonNull final List<Recipe> recipes) {
        progress.setVisibility(GONE);
        adapter.setItems(recipes);
    }
}
