package pl.karollisiewicz.baking.app.ui.recipe.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.common.ui.RecyclerViewAdapterBase;
import pl.karollisiewicz.common.ui.ViewWrapper;

@EBean
public class RecipesAdapter extends RecyclerViewAdapterBase<Recipe, RecipeView> {

    @FunctionalInterface
    public interface RecipeClickListener {
        void onRecipeSelected(final Recipe recipe);
    }

    @RootContext
    Context context;

    @Nullable
    private RecipeClickListener recipeClickListener;

    @Override
    protected RecipeView onCreateItemView(ViewGroup parent, int viewType) {
        return RecipeView_.build(context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<RecipeView> holder, int position) {
        final RecipeView view = holder.getView();
        final Recipe recipe = items.get(position);
        view.bind(recipe);
        view.setOnClickListener(v -> {
            if (recipeClickListener != null) recipeClickListener.onRecipeSelected(recipe);
        });
    }

    public void setRecipeClickListener(@Nullable RecipeClickListener recipeClickListener) {
        this.recipeClickListener = recipeClickListener;
    }
}
