package com.jingdong.discovertask.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes12.dex */
public class TaskIconView extends SimpleDraweeView {
    public static final String TAG = "TaskIconView";
    private int[] mLocalScreenPos;

    public TaskIconView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        this.mLocalScreenPos = new int[2];
    }

    public int[] getLocalScreenPos() {
        return this.mLocalScreenPos;
    }

    public TaskIconView initWithUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            init();
        } else {
            setBackgroundResource(0);
            JDImageUtils.displayImage(str, this, new JDImageLoadingListener() { // from class: com.jingdong.discovertask.widget.TaskIconView.1
                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                    TaskIconView.this.init();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    TaskIconView.this.init();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        }
        return this;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        getLocationOnScreen(this.mLocalScreenPos);
    }

    public TaskIconView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TaskIconView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
}
