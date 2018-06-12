package pl.karollisiewicz.baking.app.ui.recipe.step;

import android.content.Context;
import android.support.annotation.Nullable;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.karollisiewicz.baking.domain.Ingredient;

@EBean
public class MeasureNameProvider {

    @RootContext
    Context context;

    public String getPresentableName(@Nullable final Ingredient.Measure measure) {
        if (measure == null || measure == Ingredient.Measure.UNDEFINED) return "";

        final String name = "measure_" + measure.name().toLowerCase();
        int measureStringId = context.getResources().getIdentifier(name , "string", context.getPackageName());
        return context.getString(measureStringId);
    }
}
