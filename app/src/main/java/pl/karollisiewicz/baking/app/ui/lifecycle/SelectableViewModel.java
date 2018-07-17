package pl.karollisiewicz.baking.app.ui.lifecycle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SelectableViewModel<T> extends ViewModel {
    private final MutableLiveData<T> selected = new MutableLiveData<>();

    public void select(final T item) {
        selected.setValue(item);
    }

    public LiveData<T> getSelected() {
        return selected;
    }
}
