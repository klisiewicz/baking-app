package pl.karollisiewicz.baking.app.ui.recipe.details.ingredient;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.lifecycle.ViewLifecycleFragment;
import pl.karollisiewicz.common.collection.CollectionUtils;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static java.util.Collections.emptyList;

@EFragment(R.layout.fragment_recipe_ingredients)
public class RecipeIngredientsFragment extends ViewLifecycleFragment {

    @ViewById(value = R.id.ingredients_list)
    RecyclerView ingredientsList;

    @Bean
    RecipeIngredientAdapter adapter;

    @AfterViews
    void onViewInjected() {
        setupIngredientList();
        subscribeForSelection();
    }

    private void setupIngredientList() {
        ingredientsList.setAdapter(adapter);
        ingredientsList.setHasFixedSize(true);
        ingredientsList.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
    }

    private void subscribeForSelection() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders.of(getActivity())
                .get(RecipeDetailsViewModel.class);

        recipeDetailsViewModel.getSelected().observe(getViewLifecycleOwner(), it -> {
            if (it != null) adapter.setItems(new ArrayList<>(CollectionUtils.from(it.getIngredients())));
            else adapter.setItems(emptyList());
        });
    }
}
