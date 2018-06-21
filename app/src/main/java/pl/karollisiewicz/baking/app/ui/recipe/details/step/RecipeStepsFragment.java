package pl.karollisiewicz.baking.app.ui.recipe.details.step;

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
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.baking.domain.RecipeStep;
import pl.karollisiewicz.common.collection.CollectionUtils;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static java.util.Collections.emptyList;

@EFragment(R.layout.fragment_recipe_steps)
public class RecipeStepsFragment extends Fragment {

    @ViewById(value = R.id.steps_list)
    RecyclerView stepsList;

    @Bean
    RecipeStepAdapter adapter;

    @Bean
    ViewModelFactory viewModelFactory;

    @AfterViews
    void onViewInjected() {
        adapter.setItems(getSteps());
        stepsList.setAdapter(adapter);
        stepsList.setHasFixedSize(true);
        stepsList.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
    }

    public List<RecipeStep> getSteps() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders
                .of(getActivity(), viewModelFactory)
                .get(pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel.class);

        final Recipe value = recipeDetailsViewModel.getSelected().getValue();
        return (value != null) ? new ArrayList<>(CollectionUtils.from(value.getSteps())) : emptyList();
    }
}
