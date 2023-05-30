package tv.danmaku.ijk.media.alpha;

import android.view.MotionEvent;

/* loaded from: classes11.dex */
public interface IAlphaPlugin {
    int onConfigCreate(AlphaConfig alphaConfig);

    void onDecoding(int i2);

    void onDestroy();

    boolean onDispatchTouchEvent(MotionEvent motionEvent);

    void onRelease();

    void onRenderCreate();

    void onRendering(int i2);
}
