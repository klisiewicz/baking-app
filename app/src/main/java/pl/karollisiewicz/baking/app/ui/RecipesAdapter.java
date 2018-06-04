package pl.karollisiewicz.baking.app.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import pl.karollisiewicz.baking.domain.Recipe;
import pl.karollisiewicz.common.ui.RecyclerViewAdapterBase;
import pl.karollisiewicz.common.ui.ViewWrapper;

@EBean
public class RecipesAdapter extends RecyclerViewAdapterBase<Recipe, RecipeView> {
    @RootContext
    Context context;

    @Override
    protected RecipeView onCreateItemView(ViewGroup parent, int viewType) {
        return RecipeView_.build(context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<RecipeView> holder, int position) {
        final RecipeView view = holder.getView();
        final Recipe recipe = items.get(position);
        view.bind(recipe);
    }

    public void setItems(@NonNull final List<Recipe> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
