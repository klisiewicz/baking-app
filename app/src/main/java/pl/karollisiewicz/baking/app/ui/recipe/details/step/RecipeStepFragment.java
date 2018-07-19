package pl.karollisiewicz.baking.app.ui.recipe.details.step;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.badoualy.stepperindicator.StepperIndicator;
import com.evernote.android.state.State;
import com.evernote.android.state.StateSaver;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.navigation.NavigationFragment;
import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.common.ui.ActionBarBuilder;

@EFragment(R.layout.fragment_recipe_step)
public class RecipeStepFragment extends NavigationFragment {

    @State
    @FragmentArg
    int selectedStepNumber;

    @ViewById(R.id.view_pager)
    ViewPager pager;

    @ViewById(R.id.indicator)
    StepperIndicator indicator;

    @AfterViews
    void onViewInjected() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders.of(requireActivity())
                .get(RecipeDetailsViewModel.class);

        recipeDetailsViewModel.getSelected().observe(getViewLifecycleOwner(), recipe -> {
            if (recipe != null) {
                setupToolbar(recipe.getName());
                setupPager(recipe);
            }
        });
    }

    private void setupPager(Recipe recipe) {
        final PagerAdapter adapter = new RecipeStepPagerAdapter(getChildFragmentManager(), recipe);
        pager.setAdapter(adapter);
        pager.setCurrentItem(selectedStepNumber);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                selectedStepNumber = position;
                setupToolbar(recipe.getName());
            }
        });
        indicator.setViewPager(pager);
        indicator.onPageSelected(selectedStepNumber);
    }

    private void setupToolbar(final String recipeName) {
        setupActionBar(ActionBarBuilder.withView(R.id.toolbar)
                .setTitle(getString(R.string.recipe_step_title, recipeName, selectedStepNumber))
                .setBackClickListener(() -> getAppCompatActivity().onBackPressed())
        );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StateSaver.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

}
