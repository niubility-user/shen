package tv.danmaku.ijk.media.example.widget.media;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes11.dex */
public interface IRenderView {
    public static final int AR_16_9_FIT_PARENT = 4;
    public static final int AR_4_3_FIT_PARENT = 5;
    public static final int AR_ASPECT_FILL_PARENT = 1;
    public static final int AR_ASPECT_FIT_PARENT = 0;
    public static final int AR_ASPECT_WRAP_CONTENT = 2;
    public static final int AR_MATCH_PARENT = 3;

    /* loaded from: classes11.dex */
    public interface IRenderCallback {
        void onSurfaceChanged(@NonNull ISurfaceHolder iSurfaceHolder, int i2, int i3, int i4);

        void onSurfaceCreated(@NonNull ISurfaceHolder iSurfaceHolder, int i2, int i3);

        void onSurfaceDestroyed(@NonNull ISurfaceHolder iSurfaceHolder);

        void onSurfaceTextureUpdated();
    }

    /* loaded from: classes11.dex */
    public interface ISurfaceHolder {
        void bindToMediaPlayer(IMediaPlayer iMediaPlayer);

        @NonNull
        IRenderView getRenderView();

        @Nullable
        SurfaceHolder getSurfaceHolder();

        @Nullable
        SurfaceTexture getSurfaceTexture();

        @Nullable
        Surface openSurface();
    }

    void addRenderCallback(@NonNull IRenderCallback iRenderCallback);

    View getView();

    void release();

    void removeRenderCallback(@NonNull IRenderCallback iRenderCallback);

    void removeRenderCallbacks();

    void setAspectRatio(int i2);

    void setVideoRotation(int i2);

    void setVideoSampleAspectRatio(int i2, int i3);

    void setVideoSize(int i2, int i3);

    boolean shouldWaitForResize();
}
