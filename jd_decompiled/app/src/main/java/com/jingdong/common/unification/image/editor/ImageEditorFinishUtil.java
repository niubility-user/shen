package com.jingdong.common.unification.image.editor;

import android.content.Context;

/* loaded from: classes6.dex */
public class ImageEditorFinishUtil {
    private static ImageEditorFinishUtil instance;
    private OnFinishClickListener listener;

    /* loaded from: classes6.dex */
    public interface OnFinishClickListener {
        void onFinish(Context context, String str, int i2);
    }

    private ImageEditorFinishUtil() {
    }

    public static ImageEditorFinishUtil getInstance() {
        if (instance == null) {
            synchronized (ImageEditorFinishUtil.class) {
                if (instance == null) {
                    instance = new ImageEditorFinishUtil();
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
