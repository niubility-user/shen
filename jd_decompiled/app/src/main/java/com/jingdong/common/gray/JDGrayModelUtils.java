package com.jingdong.common.gray;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import androidx.annotation.IntRange;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class JDGrayModelUtils {
    public static final int GRAY_MODEL = 1;
    public static final int NORMAL_MODEL = 0;
    private static final Paint sDefPaint = new Paint();
    private static final Paint sGrayPaint = new Paint();
    private static final ColorMatrixColorFilter sGrayFilter = new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    private final ConcurrentHashMap<GrayModelListener, Boolean> mListeners = new ConcurrentHashMap<>();
    private final AtomicInteger mCurrentModel = new AtomicInteger(0);

    /* loaded from: classes5.dex */
    private static class Instance {
        static JDGrayModelUtils instance = new JDGrayModelUtils();

        private Instance() {
        }
    }

    public static JDGrayModelUtils getInstance() {
        return Instance.instance;
    }

    public void addListener(GrayModelListener grayModelListener) {
        if (grayModelListener != null) {
            this.mListeners.put(grayModelListener, Boolean.TRUE);
        }
    }

    public boolean isGrayModel() {
        return this.mCurrentModel.get() == 1;
    }

    public void removeListener(GrayModelListener grayModelListener) {
        if (grayModelListener != null) {
            this.mListeners.remove(grayModelListener);
        }
    }

    public void setModel(@IntRange(from = 0, to = 1) int i2) {
        try {
            AtomicInteger atomicInteger = this.mCurrentModel;
            if (atomicInteger.compareAndSet(atomicInteger.get(), i2)) {
                Iterator<GrayModelListener> it = this.mListeners.keySet().iterator();
                while (it.hasNext()) {
                    it.next().onModelChanged();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setPaintGray(Paint paint) {
        setPaintGray(paint, isGrayModel());
    }

    public void setViewGray(View view) {
        setViewGray(view, isGrayModel());
    }

    public void setPaintGray(Paint paint, boolean z) {
        if (paint == null) {
            return;
        }
        paint.setColorFilter(z ? sGrayFilter : null);
    }

    public void setViewGray(View view, boolean z) {
        if (view == null) {
            return;
        }
        Paint paint = sGrayPaint;
        paint.setColorFilter(sGrayFilter);
        if (!z) {
            paint = sDefPaint;
        }
        view.setLayerType(2, paint);
    }
}
