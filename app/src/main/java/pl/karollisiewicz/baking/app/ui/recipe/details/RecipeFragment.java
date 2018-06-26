package pl.karollisiewicz.baking.app.ui.recipe.details;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.navigation.NavigationFragment;
import pl.karollisiewicz.common.ui.ActionBarBuilder;

@EFragment(R.layout.fragment_recipe)
public class RecipeFragment extends NavigationFragment {

    @ViewById(R.id.tab_layout)
    TabLayout tabLayout;

    @ViewById(R.id.view_pager)
    ViewPager viewPager;

    @AfterViews
    void onViewInjected() {
        setupTabs();
        subscribeToViewModel();
    }

    private void setupTabs() {
        final PagerAdapter adapter = new RecipePagerAdapter(getAppCompatActivity(), getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void subscribeToViewModel() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders.of(getAppCompatActivity())
                .get(RecipeDetailsViewModel.class);

        recipeDetailsViewModel.getSelected().observe(getViewLifecycleOwner(), it -> {
            if (it != null) {
                setupActionBar(ActionBarBuilder.withView(R.id.toolbar)
                        .setTitle(it.getName())
                        .setBackClickListener(() -> getAppCompatActivity().onBackPressed())
                );
            }
        });
    }
}
