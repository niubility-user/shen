package com.jingdong.common.widget.toast;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.UnLog;

/* loaded from: classes12.dex */
public class JDCustomToast extends CustomToast {
    public static final byte MODE_BOTTOM = 2;
    public static final byte MODE_BOTTOM_Y = 3;
    public static final byte MODE_CENTER = 1;
    public static final byte MODE_CENTER_NO_ICON = 4;
    public static final byte MODE_IMAGE_EXCLAMATORY = 1;
    public static final byte MODE_IMAGE_TICK = 2;
    private static final String TAG = "JDCustomToast";
    public ImageView iv;

    /* renamed from: tv  reason: collision with root package name */
    public TextView f12567tv;

    public JDCustomToast(Activity activity, byte b) {
        super(activity);
        if (b == 1) {
            initCenterView(activity);
        } else if (b == 2) {
            initBottomView(activity);
        } else if (b != 4) {
        } else {
            initCenterNoIconView(activity);
        }
    }

    private void initBottomView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jd_custom_common_toast_style_bottom, (ViewGroup) null);
        setView(inflate);
        this.f12567tv = (TextView) inflate.findViewById(R.id.jd_custom_toast_txt);
    }

    private void initCenterNoIconView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jd_custom_common_toast_style_bottom, (ViewGroup) null);
        setView(inflate);
        this.f12567tv = (TextView) inflate.findViewById(R.id.jd_custom_toast_txt);
        setGravity(17, 0, 0);
    }

    private void initCenterView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jd_custom_common_toast_style_center, (ViewGroup) null);
        setView(inflate);
        this.f12567tv = (TextView) inflate.findViewById(R.id.jd_custom_toast_txt);
        this.iv = (ImageView) inflate.findViewById(R.id.jd_custom_toast_image);
        setGravity(17, 0, 0);
    }

    public void setImage(byte b) {
        ImageView imageView = this.iv;
        if (imageView == null) {
            return;
        }
        if (b == 1) {
            imageView.setBackgroundResource(R.drawable.jd_custom_toast_exclamation);
        } else if (b != 2) {
        } else {
            imageView.setBackgroundResource(R.drawable.jd_custom_toast_tick);
        }
    }

    public void setImageResource(int i2) {
        try {
            this.iv.setBackgroundResource(i2);
        } catch (Exception e2) {
            if (UnLog.D) {
                UnLog.e(TAG, e2.toString());
            }
            this.iv.setBackgroundResource(R.drawable.jd_custom_toast_exclamation);
        }
    }

    @Override // com.jingdong.common.widget.toast.CustomToast
    public void setText(CharSequence charSequence) {
        TextView textView = this.f12567tv;
        if (textView == null) {
            return;
        }
        textView.setTypeface(Typeface.MONOSPACE);
        this.f12567tv.setText(charSequence);
    }

    private void initBottomView(Context context, int i2) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jd_custom_common_toast_style_bottom, (ViewGroup) null);
        setView(inflate);
        this.f12567tv = (TextView) inflate.findViewById(R.id.jd_custom_toast_txt);
        setGravity(80, 0, i2);
    }

    public JDCustomToast(Activity activity, int i2) {
        super(activity);
        initBottomView(activity, i2);
    }
}
