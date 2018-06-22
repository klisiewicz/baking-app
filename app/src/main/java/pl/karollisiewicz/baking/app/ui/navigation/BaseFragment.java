package pl.karollisiewicz.baking.app.ui.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pl.karollisiewicz.common.ui.ActionBarBuilder;

public class BaseFragment extends Fragment {

    private FragmentNavigation fragmentNavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation)
            fragmentNavigation = (FragmentNavigation) context;
    }

    protected void navigateTo(@NonNull final Fragment fragment) {
        fragmentNavigation.push(fragment);
    }

    protected void setupActionBar(@NonNull final ActionBarBuilder actionBarBuilder) {
        buildToolbar(actionBarBuilder);
        buildActionBar(actionBarBuilder);
    }

    private void buildToolbar(ActionBarBuilder actionBarBuilder) {
        final AppCompatActivity activity = getAppCompatActivity();
        final Toolbar toolbar = getToolbar(actionBarBuilder.getViewId());
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            if (actionBarBuilder.getBackClickListener() != null)
                actionBarBuilder.getBackClickListener().onBackClicked();
        });
    }

    private void buildActionBar(ActionBarBuilder actionBarBuilder) {
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(actionBarBuilder.getTitle());
            actionBar.setDisplayHomeAsUpEnabled(actionBarBuilder.getBackClickListener() != null);
        }
    }

    private Toolbar getToolbar(int viewId) {
        final View view = getView();
        return view != null ? view.findViewById(viewId) : null;
    }

    @Nullable
    private ActionBar getActionBar() {
        final AppCompatActivity activity = getAppCompatActivity();
        return activity != null ? activity.getSupportActionBar() : null;
    }

    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }
}
