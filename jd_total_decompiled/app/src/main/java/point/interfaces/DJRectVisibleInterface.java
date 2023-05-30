package point.interfaces;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import java.util.List;

/* loaded from: classes11.dex */
public interface DJRectVisibleInterface {
    void calculateRectVisible(ViewGroup viewGroup);

    void calculateRectVisible(ViewGroup viewGroup, List<String> list);

    void calculateRectVisible(ViewGroup viewGroup, List<String> list, boolean z);

    long getEpMTADelayDuration();

    float getEpMTADelayRatio();

    boolean isDisableReport();

    boolean isEnableRatioWithDelay();

    boolean isPointView(View view);

    boolean isVisible(ViewGroup viewGroup, View view);

    boolean isVisible(ViewGroup viewGroup, View view, float f2);

    boolean isVisibleForRatio(ViewGroup viewGroup, View view);

    boolean isVisibleForRatio(ViewGroup viewGroup, View view, float f2);

    DJPointListener registerRootView(ViewGroup viewGroup, int i2);

    DJPointListener registerRootView(ViewGroup viewGroup, ViewGroup viewGroup2, int i2);

    void registerView(View view);

    void release();

    void report(Context context, String str, DJPointData dJPointData);

    DJRectVisibleInterface setDisableReport(boolean z);

    DJRectVisibleInterface setEnableRatioWithDelay(boolean z);

    DJRectVisibleInterface setEpMTADelayDuration(@IntRange(from = 0, to = 2147483647L) long j2);

    DJRectVisibleInterface setEpMTADelayRatio(@FloatRange(from = 0.0d, to = 1.0d) float f2);

    DJRectVisibleInterface setGlobalLoopModel(boolean z);

    void unregisterView(View view);
}
