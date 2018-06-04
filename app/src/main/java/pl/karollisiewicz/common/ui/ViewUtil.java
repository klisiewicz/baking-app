package pl.karollisiewicz.common.ui;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;

import static android.view.View.VISIBLE;

public final class ViewUtil {
    private ViewUtil() {
    }

    @UiThread
    public static void showView(@NonNull final View view) {
        view.setVisibility(VISIBLE);
    }

    @UiThread
    public static void hideView(@NonNull final View view) {
        view.setVisibility(View.GONE);
    }

    @UiThread
    public static void showViewWhen(@NonNull final View view, boolean condition) {
        if (condition) view.setVisibility(VISIBLE);
        else view.setVisibility(View.GONE);
    }
}
