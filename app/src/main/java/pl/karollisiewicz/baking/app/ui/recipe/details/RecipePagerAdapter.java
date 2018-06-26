package pl.karollisiewicz.baking.app.ui.recipe.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.recipe.details.ingredient.RecipeIngredientsFragment_;
import pl.karollisiewicz.baking.app.ui.recipe.details.step.RecipeStepsFragment_;

final class RecipePagerAdapter extends FragmentStatePagerAdapter {
    private final Context context;

    RecipePagerAdapter(@NonNull final Context context,
                       @NonNull final FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return RecipeIngredientsFragment_.builder().build();
        else if (position == 1) return RecipeStepsFragment_.builder().build();
        else throw new IllegalArgumentException(position + " is not a valid position.");
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return context.getString(R.string.ingredients);
        else if (position == 1) return context.getString(R.string.steps);
        else return null;
    }
}
