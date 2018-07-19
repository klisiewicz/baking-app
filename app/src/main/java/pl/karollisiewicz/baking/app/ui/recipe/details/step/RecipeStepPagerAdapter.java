package pl.karollisiewicz.baking.app.ui.recipe.details.step;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import pl.karollisiewicz.baking.app.ui.recipe.details.step.video.RecipeStepVideoFragment_;
import pl.karollisiewicz.baking.domain.Recipe;

public final class RecipeStepPagerAdapter extends FragmentStatePagerAdapter {
    @NonNull
    private final Recipe recipe;

    RecipeStepPagerAdapter(@NonNull final android.support.v4.app.FragmentManager fragmentManager,
                           @NonNull final Recipe recipe) {
        super(fragmentManager);
        this.recipe = recipe;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeStepVideoFragment_.builder()
                .recipeStep(recipe.getStepAt(position))
                .build();
    }

    @Override
    public int getCount() {
        return recipe.getStepsNumber();
    }
}
