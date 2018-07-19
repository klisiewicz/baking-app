package pl.karollisiewicz.baking.app.ui.recipe.details.step;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.karollisiewicz.baking.domain.RecipeStep;
import pl.karollisiewicz.common.ui.RecyclerViewAdapterBase;
import pl.karollisiewicz.common.ui.ViewWrapper;

@EBean
public class RecipeStepsAdapter extends RecyclerViewAdapterBase<RecipeStep, RecipeStepView> {

    @FunctionalInterface
    public interface RecipeStepClickListener {
        void onRecipeStepSelected(final RecipeStep recipeStep, int position);
    }

    @RootContext
    Context context;

    @Nullable
    private RecipeStepClickListener recipeStepClickListener;

    @Override
    protected RecipeStepView onCreateItemView(ViewGroup parent, int viewType) {
        return RecipeStepView_.build(context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<RecipeStepView> holder, int position) {
        final RecipeStepView recipeStepView = holder.getView();
        final RecipeStep step = items.get(position);

        recipeStepView.bind(new RecipeStepView.Model(
                String.valueOf(step.getOrdinal()) + ".",
                step.getShortDescription()
        ));
        recipeStepView.setOnClickListener(v -> {
            if (recipeStepClickListener != null)
                recipeStepClickListener.onRecipeStepSelected(step, position);
        });
    }

    public void setRecipeStepClickListener(@Nullable RecipeStepClickListener recipeStepClickListener) {
        this.recipeStepClickListener = recipeStepClickListener;
    }
}
