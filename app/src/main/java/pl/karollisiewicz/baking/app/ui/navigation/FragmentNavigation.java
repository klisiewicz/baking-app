package pl.karollisiewicz.baking.app.ui.navigation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface FragmentNavigation {
    void push(@NonNull final Fragment fragment);
}
