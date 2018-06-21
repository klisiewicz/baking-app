package pl.karollisiewicz.baking.app.ui.recipe.details.ingredient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.karollisiewicz.baking.app.ui.recipe.details.step.MeasureNameProvider;
import pl.karollisiewicz.baking.domain.Ingredient;
import pl.karollisiewicz.common.ui.RecyclerViewAdapterBase;
import pl.karollisiewicz.common.ui.ViewWrapper;

@EBean
public class RecipeIngredientAdapter extends RecyclerViewAdapterBase<Ingredient, RecipeIngredientView> {

    @RootContext
    Context context;

    @Bean
    MeasureNameProvider measureNameProvider;

    @Override
    protected RecipeIngredientView onCreateItemView(ViewGroup parent, int viewType) {
        return RecipeIngredientView_.build(context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<RecipeIngredientView> holder, int position) {
        final RecipeIngredientView view = holder.getView();
        final Ingredient ingredient = items.get(position);

        view.bind(new RecipeIngredientView.Model(
                capitalizeFirstLetter(ingredient.getName()),
                String.valueOf(ingredient.getQuantity()),
                measureNameProvider.getPresentableName(ingredient.getMeasure())
        ));
    }

    private static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
