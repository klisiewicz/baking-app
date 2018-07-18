package pl.karollisiewicz.baking.app.ui.recipe.details.step;

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
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeStepViewModel;
import pl.karollisiewicz.baking.app.ui.navigation.NavigationFragment;
import pl.karollisiewicz.common.collection.CollectionUtils;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static java.util.Collections.emptyList;

@EFragment(R.layout.fragment_recipe_steps)
public class RecipeStepsFragment extends NavigationFragment {

    @ViewById(value = R.id.steps_list)
    RecyclerView stepsList;

    @Bean
    RecipeStepsAdapter adapter;

    private RecipeDetailsViewModel recipeDetailsViewModel;
    private RecipeStepViewModel recipeStepViewModel;

    @AfterViews
    void onViewInjected() {
        setupViewModels();
        setupStepsList();
        subscribeForSelection();
    }

    private void setupViewModels() {
        recipeDetailsViewModel = ViewModelProviders.of(getActivity()).get(RecipeDetailsViewModel.class);
        recipeStepViewModel = ViewModelProviders.of(getActivity()).get(RecipeStepViewModel.class);
    }

    private void setupStepsList() {
        adapter.setRecipeStepClickListener(step -> {
                    recipeStepViewModel.select(step);
                    navigateTo(RecipeStepFragment_.builder().build());
                }
        );
        stepsList.setAdapter(adapter);
        stepsList.setHasFixedSize(true);
        stepsList.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
    }

    private void subscribeForSelection() {
        recipeDetailsViewModel.getSelected()
                .observe(getViewLifecycleOwner(), it -> {
                    if (it == null) adapter.setItems(emptyList());
                    else adapter.setItems(new ArrayList<>(CollectionUtils.from(it.getSteps())));
                });
    }
}
