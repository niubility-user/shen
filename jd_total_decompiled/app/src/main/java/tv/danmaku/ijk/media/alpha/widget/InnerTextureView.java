package tv.danmaku.ijk.media.alpha.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;

/* loaded from: classes11.dex */
public class InnerTextureView extends TextureView {
    private static final String TAG = InnerTextureView.class.getSimpleName();
    private AlphaPlayer player;

    public InnerTextureView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AlphaPlayer alphaPlayer = this.player;
        boolean z = false;
        if (alphaPlayer != null && motionEvent != null && alphaPlayer.pluginManager != null && alphaPlayer.isRunning() && this.player.pluginManager.onDispatchTouchEvent(motionEvent)) {
            z = true;
        }
        if (z) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public AlphaPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
    }

    public InnerTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InnerTextureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
