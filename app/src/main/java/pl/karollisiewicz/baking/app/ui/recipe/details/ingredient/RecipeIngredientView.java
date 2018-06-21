package pl.karollisiewicz.baking.app.ui.recipe.details.ingredient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;

@EViewGroup(R.layout.list_item_recipe_ingredient)
class RecipeIngredientView extends LinearLayout {

    @ViewById(R.id.ingredient_name)
    AppCompatTextView name;

    @ViewById(R.id.ingredient_amount)
    AppCompatTextView amount;

    @ViewById(R.id.ingredient_unit)
    AppCompatTextView unit;

    public RecipeIngredientView(Context context) {
        super(context);
    }

    public RecipeIngredientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecipeIngredientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void bind(@NonNull final Model viewModel) {
        name.setText(viewModel.name);
        amount.setText(viewModel.amount);
        unit.setText(viewModel.unit);
    }
    
    static class Model {
        private final String name;
        private final String amount;
        private final String unit;

        Model(String name, String amount, String unit) {
            this.name = name;
            this.amount = amount;
            this.unit = unit;
        }
    }
}
