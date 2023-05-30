package com.jingdong.common.XView2;

import android.app.Activity;
import androidx.annotation.NonNull;

/* loaded from: classes5.dex */
public interface IActivityLifeCallBack {
    void onCreate(@NonNull Activity activity);

    void onDestroy(@NonNull Activity activity);

    void onPause(@NonNull Activity activity);

    void onResume(@NonNull Activity activity);

    void onStop(@NonNull Activity activity);
}
