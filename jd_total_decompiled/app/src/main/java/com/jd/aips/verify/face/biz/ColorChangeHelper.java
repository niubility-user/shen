package com.jd.aips.verify.face.biz;

import android.os.SystemClock;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.jd.aips.verify.config.FaceDazzleSdk;
import java.util.List;

/* loaded from: classes12.dex */
public class ColorChangeHelper {
    private static final int MIN_DELAY = 200;
    private volatile ColorChangeCallback callback;
    private volatile List<String> colorList;
    private final int interval;
    private final List<FaceDazzleSdk.Config.DazzleRule> rules;
    private volatile int ruleIndex = -1;
    private volatile int colorIndex = 0;
    private volatile long lastChangeTime = -1;

    /* loaded from: classes12.dex */
    public interface ColorChangeCallback {
        void onChange(String str, int i2);

        void onCompleted();
    }

    public ColorChangeHelper(@NonNull FaceDazzleSdk.Config config) {
        this.rules = config.face_dazzle_rules;
        this.interval = Math.max(config.facedazzle_time, 200);
    }

    private void reset() {
        this.callback = null;
        this.colorIndex = -1;
        this.lastChangeTime = -1L;
    }

    public synchronized void cancel() {
        reset();
    }

    public String getCurrentColor() {
        return (this.colorList == null || this.colorList.isEmpty() || this.colorIndex < 0 || this.colorIndex >= this.colorList.size()) ? "" : this.colorList.get(this.colorIndex);
    }

    public boolean needCapture() {
        return this.lastChangeTime > 0 && SystemClock.elapsedRealtime() - this.lastChangeTime >= ((long) this.interval);
    }

    public synchronized void toChangeColor() {
        this.colorIndex++;
        int size = this.colorList.size();
        if (this.colorIndex < size) {
            this.callback.onChange(this.colorList.get(this.colorIndex), ((this.colorIndex + 1) * 100) / size);
            this.lastChangeTime = SystemClock.elapsedRealtime();
        } else {
            this.callback.onCompleted();
            reset();
        }
    }

    @MainThread
    public synchronized boolean toPrepare(@NonNull ColorChangeCallback colorChangeCallback) {
        boolean z;
        List<String> list;
        reset();
        z = true;
        this.ruleIndex++;
        if (this.rules != null && this.ruleIndex < this.rules.size()) {
            try {
                list = this.rules.get(this.ruleIndex).face_dazzle_colours;
            } catch (Exception unused) {
            }
            if (list != null && !list.isEmpty()) {
                this.callback = colorChangeCallback;
                this.colorList = list;
            }
        }
        z = false;
        return z;
    }
}
