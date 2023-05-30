package com.jingdong.common.messagecenter;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class MessageActivityUtils {
    private static volatile MessageActivityUtils instance;
    private WeakReference<Activity> activity;

    public static MessageActivityUtils getInstance() {
        if (instance == null) {
            synchronized (MessageActivityUtils.class) {
                if (instance == null) {
                    instance = new MessageActivityUtils();
                }
            }
        }
        return instance;
    }

    public synchronized Activity getActivity() {
        WeakReference<Activity> weakReference = this.activity;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public synchronized void setActivity(Activity activity) {
        if (this.activity == null) {
            this.activity = new WeakReference<>(activity);
        }
    }
}
