package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.core.internal.view.SupportMenu;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class TabIconLayout extends RelativeLayout {
    private int iconHeight;
    private int iconWidth;
    private boolean isText;
    private SimpleDraweeView mIconView;
    private RedPointTextView mTextView;

    public TabIconLayout(Context context) {
        this(context, null);
    }

    private void init() {
        setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        setGravity(17);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        this.mIconView = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        RedPointTextView redPointTextView = new RedPointTextView(getContext());
        this.mTextView = redPointTextView;
        redPointTextView.setGravity(17);
        this.mTextView.setIncludeFontPadding(false);
    }

    public void bindData(String str) {
        bindData(str, "");
    }

    public RedPointTextView getRedPointTextView() {
        return this.mTextView;
    }

    public boolean isShowText() {
        return this.isText;
    }

    public void setIconWidthHeight(int i2, int i3) {
        this.iconWidth = i2;
        this.iconHeight = i3;
    }

    public TabIconLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.iconWidth = -2;
        this.iconHeight = -2;
        this.isText = true;
        init();
    }

    public void bindData(String str, String str2) {
        bindData(str, str2, false, 0.0f);
    }

    public void bindData(String str, String str2, boolean z, float f2) {
        bindData(str, str2, z, f2, SupportMenu.CATEGORY_MASK);
    }

    public void bindData(String str, String str2, boolean z, float f2, @ColorInt int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        removeAllViews();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.iconWidth, this.iconHeight);
        layoutParams.addRule(13);
        this.mIconView.setLayoutParams(layoutParams);
        addView(this.mIconView);
        this.mTextView.setRadius(f2);
        this.mTextView.setPointColor(i2);
        this.mTextView.setRedPointShow(z);
        this.mTextView.setText(str);
        addView(this.mTextView);
        this.mIconView.setVisibility(8);
        this.mTextView.setVisibility(0);
        if (TextUtils.isEmpty(str2)) {
            this.isText = true;
            return;
        }
        if (OKLog.D) {
            OKLog.d("TabIconLayout", "bindTabEntity -> entity.icon: " + str2);
        }
        this.mIconView.setVisibility(0);
        JDImageUtils.displayImage(str2, this.mIconView, new JDDisplayImageOptions(), new JDImageLoadingListener() { // from class: com.jingdong.common.widget.TabIconLayout.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str3, View view) {
                TabIconLayout.this.mIconView.setVisibility(8);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                TabIconLayout.this.mIconView.setVisibility(0);
                TabIconLayout.this.mTextView.setVisibility(8);
                TabIconLayout.this.isText = false;
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                TabIconLayout.this.mIconView.setVisibility(8);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str3, View view) {
            }
        });
    }
}
