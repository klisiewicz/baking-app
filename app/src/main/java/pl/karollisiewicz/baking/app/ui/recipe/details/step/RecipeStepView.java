package pl.karollisiewicz.baking.app.ui.recipe.details.step;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;

@EViewGroup(R.layout.list_item_recipe_step)
public class RecipeStepView extends LinearLayout {

    @ViewById(R.id.step_name)
    AppCompatTextView name;

    public RecipeStepView(Context context) {
        super(context);
    }

    public RecipeStepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecipeStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void bind(@NonNull final String description) {
        name.setText(description);
    }
}
