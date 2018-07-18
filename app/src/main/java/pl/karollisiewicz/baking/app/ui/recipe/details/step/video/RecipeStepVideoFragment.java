package pl.karollisiewicz.baking.app.ui.recipe.details.step.video;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.karollisiewicz.baking.R;
import pl.karollisiewicz.baking.app.ui.lifecycle.RecipeStepViewModel;
import pl.karollisiewicz.baking.app.ui.lifecycle.ViewLifecycleFragment;
import pl.karollisiewicz.common.ui.ExoPlayerView;

import static android.text.TextUtils.isEmpty;
import static pl.karollisiewicz.common.ui.ViewUtil.showViewWhen;

@EFragment(R.layout.fragment_recipe_video)
public class RecipeStepVideoFragment extends ViewLifecycleFragment {

    @ViewById(R.id.player)
    ExoPlayerView playerView;

    @ViewById(R.id.recipe_description)
    TextView description;

    @AfterViews
    void onViewInjected() {
        setupPlayer();
        subscribeForSelection();
    }

    private void setupPlayer() {
        final Player player = createDefaultPlayerInstance();
        playerView.setPlayer(player);
        getViewLifecycleOwner().getLifecycle().addObserver(new RecipeStepVideoLifecycleObserver(player));
    }

    @NonNull
    private SimpleExoPlayer createDefaultPlayerInstance() {
        return ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())),
                new DefaultLoadControl()
        );
    }

    private void subscribeForSelection() {
        final RecipeStepViewModel recipeStepViewModel = ViewModelProviders.of(requireActivity())
                .get(RecipeStepViewModel.class);
        recipeStepViewModel.getSelected().observe(getViewLifecycleOwner(), step -> {
            if (step == null) return;

            description.setText(step.getFullDescription());
            showViewWhen(playerView, !isEmpty(step.getVideoUrl()));
            playerView.play(step.getVideoUrl());
        });
    }
}