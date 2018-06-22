package pl.karollisiewicz.common.ui;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

public final class ActionBarBuilder {
    @IdRes
    private final int viewId;

    @Nullable
    private String title;

    @Nullable
    private BackClickListener backClickListener;

    private ActionBarBuilder(@IdRes int viewId) {
        this.viewId = viewId;
    }

    public int getViewId() {
        return viewId;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public ActionBarBuilder setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    @Nullable
    public BackClickListener getBackClickListener() {
        return backClickListener;
    }

    public ActionBarBuilder setBackClickListener(@Nullable BackClickListener backClickListener) {
        this.backClickListener = backClickListener;
        return this;
    }

    public static ActionBarBuilder withView(@IdRes int viewId) {
        return new ActionBarBuilder(viewId);
    }

    @FunctionalInterface
    public interface BackClickListener {
        void onBackClicked();
    }
}
