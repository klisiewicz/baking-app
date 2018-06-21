package pl.karollisiewicz.baking.app.ui.recipe.details.ingredient;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.lifecycle.ViewModelFactory;
import pl.karollisiewicz.baking.domain.Ingredient;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.common.collection.CollectionUtils;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static java.util.Collections.emptyList;

@EFragment(R.layout.fragment_recipe_ingredients)
public class RecipeIngredientsFragment extends Fragment {

    @ViewById(value = R.id.ingredients_list)
    RecyclerView ingredientsList;

    @Bean
    RecipeIngredientAdapter adapter;

    @Bean
    ViewModelFactory viewModelFactory;

    @AfterViews
    void onViewInjected() {
        adapter.setItems(getIngredients());
        ingredientsList.setAdapter(adapter);
        ingredientsList.setHasFixedSize(true);
        ingredientsList.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
    }

    private List<Ingredient> getIngredients() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders
                .of(getActivity(), viewModelFactory)
                .get(RecipeDetailsViewModel.class);

        final Recipe value = recipeDetailsViewModel.getSelected().getValue();
        return (value != null) ? new ArrayList<>(CollectionUtils.from(value.getIngredients())) : emptyList();
    }
}
