package tv.danmaku.ijk.media.alpha.plugin;

import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.IAlphaPlugin;
import tv.danmaku.ijk.media.alpha.mask.MaskAlphaPlugin;
import tv.danmaku.ijk.media.alpha.mix.MixAlphaPlugin;
import tv.danmaku.ijk.media.alpha.player.AlphaPlayer;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class AlphaPluginManager {
    private static final int DIFF_TIMES = 4;
    private static final String TAG = AlphaPlayer.class.getSimpleName();
    private MaskAlphaPlugin maskAlphaPlugin;
    private MixAlphaPlugin mixAlphaPlugin;
    private AlphaPlayer player;
    private ArrayList<IAlphaPlugin> plugins = new ArrayList<>();
    private int frameIndex = 0;
    private int decodeIndex = 0;
    private int frameDiffTimes = 0;

    public AlphaPluginManager(AlphaPlayer alphaPlayer) {
        this.player = alphaPlayer;
        this.mixAlphaPlugin = new MixAlphaPlugin(alphaPlayer);
        this.maskAlphaPlugin = new MaskAlphaPlugin(alphaPlayer);
        this.plugins.add(this.mixAlphaPlugin);
        this.plugins.add(this.maskAlphaPlugin);
    }

    public MaskAlphaPlugin getMaskAlphaPlugin() {
        return this.maskAlphaPlugin;
    }

    public MixAlphaPlugin getMixAlphaPlugin() {
        return this.mixAlphaPlugin;
    }

    public int onConfigCreate(AlphaConfig alphaConfig) {
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            int onConfigCreate = it.next().onConfigCreate(alphaConfig);
            if (onConfigCreate != 0) {
                return onConfigCreate;
            }
        }
        return 0;
    }

    public void onDecoding(int i2) {
        DebugLog.d(TAG, "onDecoding");
        this.decodeIndex = i2;
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            it.next().onDecoding(i2);
        }
    }

    public void onDestroy() {
        DebugLog.i(TAG, "onDestroy");
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    public boolean onDispatchTouchEvent(MotionEvent motionEvent) {
        DebugLog.i(TAG, "onDispatchTouchEvent");
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            if (it.next().onDispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    public void onLoopStart() {
        DebugLog.i(TAG, "onLoopStart");
        this.frameIndex = 0;
        this.decodeIndex = 0;
    }

    public void onRelease() {
        DebugLog.i(TAG, "onRelease");
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            it.next().onRelease();
        }
    }

    public void onRenderCreate() {
        DebugLog.i(TAG, "onReaderCreate");
        this.frameIndex = 0;
        this.decodeIndex = 0;
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            it.next().onRenderCreate();
        }
    }

    public void onRendering() {
        if (this.decodeIndex > this.frameIndex + 1 || this.frameDiffTimes >= 4) {
            DebugLog.d(TAG, "jump frameIndex " + this.frameIndex + ", decodingIndex = " + this.decodeIndex + ", frameDiffTimes = " + this.frameDiffTimes);
            this.frameIndex = this.decodeIndex;
        }
        if (this.decodeIndex != this.frameIndex) {
            this.frameDiffTimes++;
        } else {
            this.frameDiffTimes = 0;
        }
        DebugLog.d(TAG, "onRendering frameIndex = " + this.frameIndex);
        Iterator<IAlphaPlugin> it = this.plugins.iterator();
        while (it.hasNext()) {
            it.next().onRendering(this.frameIndex);
        }
        this.frameIndex++;
    }
}
