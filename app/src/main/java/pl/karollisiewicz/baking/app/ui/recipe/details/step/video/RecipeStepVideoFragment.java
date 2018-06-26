package pl.karollisiewicz.baking.app.ui.recipe.details.step.video;

import android.arch.lifecycle.ViewModelProviders;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeDetailsViewModel;
import pl.karollisiewicz.baking.app.ui.navigation.NavigationFragment;
import pl.karollisiewicz.common.ui.ActionBarBuilder;

@EFragment(R.layout.fragment_recipe_video)
public class RecipeStepVideoFragment extends NavigationFragment {

    @ViewById(R.id.exo_player)
    SimpleExoPlayerView exoPlayer;

    @AfterViews
    void onViewInjected() {
        setupActionBar(ActionBarBuilder.withView(R.id.toolbar)
                .setTitle("Step: 1")
                .setBackClickListener(() -> getAppCompatActivity().onBackPressed())
        );

        subscribeForSelection();
    }

    private void subscribeForSelection() {
        final RecipeDetailsViewModel recipeDetailsViewModel = ViewModelProviders.of(getAppCompatActivity())
                .get(RecipeDetailsViewModel.class);
    }
}