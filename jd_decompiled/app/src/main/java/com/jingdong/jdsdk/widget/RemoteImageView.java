package com.jingdong.jdsdk.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.RemoteImageUtil;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.io.File;

/* loaded from: classes14.dex */
public class RemoteImageView extends SimpleDraweeView {
    private String iconRes;
    private boolean isFromLocal;
    private int ivColor;
    private String ivId;
    private String moduleId;
    private float rotaAngle;
    private String ttfPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RemoteImageView.this.dealImage();
        }
    }

    public RemoteImageView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealImage() {
        try {
            if (!TextUtils.isEmpty(this.iconRes) && !TextUtils.isEmpty(this.ivId)) {
                if (TextUtils.isEmpty(this.ttfPath)) {
                    this.ttfPath = "common.ttf";
                }
                String concat = "fonts".concat(File.separator).concat(this.ttfPath);
                int min = Math.min(getWidth(), getHeight());
                IconDrawable iconDrawable = new IconDrawable(getContext(), new IconImpl("", this.iconRes), concat);
                if (min != 0) {
                    iconDrawable.sizePx(min);
                }
                if (TextUtils.isEmpty(this.moduleId)) {
                    this.moduleId = "21";
                }
                RemoteImageUtil.setImageUrlFromXjs(this, this.moduleId, this.ivId, iconDrawable, this.ivColor);
            } else if (!TextUtils.isEmpty(this.iconRes)) {
                if (TextUtils.isEmpty(this.ttfPath)) {
                    this.ttfPath = "common.ttf";
                }
                String concat2 = "fonts".concat(File.separator).concat(this.ttfPath);
                int min2 = Math.min(getWidth(), getHeight());
                IconDrawable iconDrawable2 = new IconDrawable(getContext(), new IconImpl("", this.iconRes), concat2);
                if (min2 != 0) {
                    iconDrawable2.sizePx(min2);
                }
                setImageDrawable(iconDrawable2);
                int i2 = this.ivColor;
                if (i2 != -1) {
                    setColorFilter(i2);
                } else {
                    clearColorFilter();
                }
            } else if (!TextUtils.isEmpty(this.ivId)) {
                if (TextUtils.isEmpty(this.moduleId)) {
                    this.moduleId = "21";
                }
                RemoteImageUtil.setImageUrlFromXjs(this, this.moduleId, this.ivId, null, this.ivColor);
            }
            float f2 = this.rotaAngle;
            if (0.0f != f2) {
                rotaView(this, Float.valueOf(f2));
            }
        } catch (Exception unused) {
        }
    }

    private void initCustomAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lib_remote_view);
        if (obtainStyledAttributes != null) {
            this.moduleId = obtainStyledAttributes.getString(R.styleable.lib_remote_view_moduleId_id);
            this.ivId = obtainStyledAttributes.getString(R.styleable.lib_remote_view_iv_id);
            this.iconRes = obtainStyledAttributes.getString(R.styleable.lib_remote_view_icon_font_res);
            this.ivColor = obtainStyledAttributes.getColor(R.styleable.lib_remote_view_color, -1);
            this.ttfPath = obtainStyledAttributes.getString(R.styleable.lib_remote_view_ttf_path);
            this.isFromLocal = obtainStyledAttributes.getBoolean(R.styleable.lib_remote_view_from_local, false);
            this.rotaAngle = obtainStyledAttributes.getFloat(R.styleable.lib_remote_view_rota_angle, 0.0f);
            obtainStyledAttributes.recycle();
            post(new a());
        }
    }

    public static void rotaView(View view, Float f2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", 0.0f, f2.floatValue());
        ofFloat.setDuration(0L);
        ofFloat.start();
    }

    public RemoteImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RemoteImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initCustomAttrs(context, attributeSet);
    }
}
