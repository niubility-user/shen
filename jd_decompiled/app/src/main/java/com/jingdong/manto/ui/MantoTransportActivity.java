package com.jingdong.manto.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.jingdong.manto.MantoActivityResult;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/* loaded from: classes16.dex */
public class MantoTransportActivity extends Activity {
    static Reference<MantoActivityResult.ResultCallback> a;
    static Reference<OnCreateActivityListener> b;

    /* loaded from: classes16.dex */
    public interface OnCreateActivityListener {
        void onCreate(Activity activity);
    }

    public static final void start(@NonNull Activity activity, @NonNull OnCreateActivityListener onCreateActivityListener, @NonNull MantoActivityResult.ResultCallback resultCallback) {
        a = new SoftReference(resultCallback);
        b = new SoftReference(onCreateActivityListener);
        activity.startActivity(new Intent(activity, MantoTransportActivity.class));
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        Reference<MantoActivityResult.ResultCallback> reference = a;
        if (reference != null && reference.get() != null) {
            a.get().onActivityResult(i2, i3, intent);
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        Reference<OnCreateActivityListener> reference = b;
        if (reference == null || reference.get() == null) {
            return;
        }
        b.get().onCreate(this);
    }
}
