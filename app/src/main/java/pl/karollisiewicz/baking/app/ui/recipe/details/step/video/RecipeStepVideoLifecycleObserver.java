package pl.karollisiewicz.baking.app.ui.recipe.details.step.video;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;

import com.google.android.exoplayer2.Player;

import static android.arch.lifecycle.Lifecycle.Event.ON_DESTROY;
import static android.arch.lifecycle.Lifecycle.Event.ON_PAUSE;

class RecipeStepVideoLifecycleObserver implements LifecycleObserver {
    private Player player;

    RecipeStepVideoLifecycleObserver(@Nullable final Player player) {
        this.player = player;
    }

    @OnLifecycleEvent(ON_PAUSE)
    public void stop() {
        stopAndRelease();
    }

    @OnLifecycleEvent(ON_DESTROY)
    public void destroy() {
        stopAndRelease();
    }

    private void stopAndRelease() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
