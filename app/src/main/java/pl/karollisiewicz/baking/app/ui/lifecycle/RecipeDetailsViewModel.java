package pl.karollisiewicz.baking.app.ui.lifecycle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.androidannotations.annotations.EBean;

import pl.karollisiewicz.baking.domain.Recipe;

@EBean
public class RecipeDetailsViewModel extends ViewModel {
    private final MutableLiveData<Recipe> selected = new MutableLiveData<>();

    public void select(final Recipe recipe) {
        selected.setValue(recipe);
    }

    public LiveData<Recipe> getSelected() {
        return selected;
    }
}
