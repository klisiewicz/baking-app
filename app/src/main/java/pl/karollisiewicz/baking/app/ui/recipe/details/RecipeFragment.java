package pl.karollisiewicz.baking.app.ui.recipe.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.lifecycle.ViewModelFactory;

@EFragment(R.layout.fragment_recipe)
public class RecipeFragment extends Fragment {

    @Bean
    ViewModelFactory viewModelFactory;

    private RecipeDetailsViewModel recipeDetailsViewModel;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipeDetailsViewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
                .get(RecipeDetailsViewModel.class);
    }

    @AfterViews
    void onViewInjected() {
        recipeDetailsViewModel.getSelected().observe(this, it -> {
            // Update view when selection changes
        });
    }
}
