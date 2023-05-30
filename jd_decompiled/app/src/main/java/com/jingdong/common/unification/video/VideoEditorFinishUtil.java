package com.jingdong.common.unification.video;

import android.content.Context;

/* loaded from: classes6.dex */
public class VideoEditorFinishUtil {
    private static VideoEditorFinishUtil instance;
    private OnFinishClickListener listener;

    /* loaded from: classes6.dex */
    public interface OnFinishClickListener {
        void onFinish(Context context, String str, int i2);
    }

    private VideoEditorFinishUtil() {
    }

    public static VideoEditorFinishUtil getInstance() {
        if (instance == null) {
            synchronized (VideoEditorFinishUtil.class) {
                if (instance == null) {
                    instance = new VideoEditorFinishUtil();
                }
            }
        }
        return instance;
    }

    public OnFinishClickListener getListener() {
        return this.listener;
    }

    public void setListener(OnFinishClickListener onFinishClickListener) {
        this.listener = onFinishClickListener;
    }
}
