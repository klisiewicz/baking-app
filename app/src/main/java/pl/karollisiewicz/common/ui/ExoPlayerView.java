package pl.karollisiewicz.common.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.evernote.android.state.State;
import com.evernote.android.state.StateSaver;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static android.text.TextUtils.isEmpty;

public final class ExoPlayerView extends PlayerView {
    @State
    public long position;

    public ExoPlayerView(Context context) {
        super(context);
    }

    public ExoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPlayer(Player player) {
        super.setPlayer(player);
        setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
    }

    public void play(@Nullable final String videoUrl) {
        if (isEmpty(videoUrl)) return;

        final MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "BakingApp"))
        ).createMediaSource(Uri.parse(videoUrl));

        final Player player = getPlayer();
        if (player instanceof ExoPlayer) {
            ((ExoPlayer) player).prepare(mediaSource, false, false);
            player.seekTo(position);
            player.setPlayWhenReady(true);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Player player = getPlayer();
        if (player != null) position = player.getCurrentPosition();
        return StateSaver.saveInstanceState(this, super.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Parcelable state) {
        super.onRestoreInstanceState(StateSaver.restoreInstanceState(this, state));
    }
}
