package pl.karollisiewicz.baking.app.ui.recipe.details.step;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.badoualy.stepperindicator.StepperIndicator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.navigation.NavigationFragment;
import pl.karollisiewicz.common.ui.ActionBarBuilder;

@EFragment(R.layout.fragment_recipe_step)
public class RecipeStepFragment extends NavigationFragment {

    @ViewById(R.id.view_pager)
    ViewPager pager;

    @ViewById(R.id.indicator)
    StepperIndicator indicator;

    @AfterViews
    void onViewInjected() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders.of(getAppCompatActivity())
                .get(RecipeDetailsViewModel.class);

        recipeDetailsViewModel.getSelected().observe(getViewLifecycleOwner(), recipe -> {
            if (recipe != null) {
                setupActionBar(ActionBarBuilder.withView(R.id.toolbar)
                        .setTitle(recipe.getName())
                        .setBackClickListener(() -> getAppCompatActivity().onBackPressed())
                );

                final PagerAdapter adapter = new RecipeStepPagerAdapter(getChildFragmentManager(), recipe);
                pager.setAdapter(adapter);
//                pager.setCurrentItem(0);
                indicator.setViewPager(pager);
            }
        });
    }

    private void setupToolbarWithStepNumber(int ordinal) {
        setupActionBar(ActionBarBuilder.withView(R.id.toolbar)
                .setTitle(getString(R.string.step_number, ordinal))
                .setBackClickListener(() -> getAppCompatActivity().onBackPressed())
        );
    }
}
