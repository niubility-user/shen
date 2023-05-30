package com.jingdong.jdsdk.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.jdsdk.widget.context.ContextSafeWrapper;
import com.jingdong.sdk.jdtoast.R;

/* loaded from: classes14.dex */
public class JDToast extends Toast {
    public static final byte MODE_BOTTOM = 2;
    public static final byte MODE_BOTTOM_Y = 3;
    public static final byte MODE_CENTER = 1;
    public static final byte MODE_CENTER_NO_ICON = 4;
    public static final byte MODE_CUSTOM_CENTER = 5;
    public static final byte MODE_IMAGE_ERROR = 3;
    public static final byte MODE_IMAGE_EXCLAMATORY = 1;
    public static final byte MODE_IMAGE_TICK = 2;
    private static final String TAG = "JDToast";
    private FrameLayout centerRootLayout;
    private int currentMode;
    public ImageView iv;

    /* renamed from: tv  reason: collision with root package name */
    public TextView f12933tv;

    public JDToast(Context context, byte b) {
        super(context);
        this.currentMode = b;
        if (b == 1) {
            initCenterView(context);
        } else if (b == 2) {
            initBottomView(context);
        } else if (b == 4) {
            initCenterNoIconView(context);
        } else if (b != 5) {
        } else {
            initCustomCenter(context);
        }
    }

    private LayoutInflater getLayoutInflater(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return LayoutInflater.from(context).cloneInContext(new ContextSafeWrapper(context));
        }
        return LayoutInflater.from(context);
    }

    private void initBottomView(Context context) {
        View inflate = getLayoutInflater(context).inflate(R.layout.jd_common_toast_style_bottom, (ViewGroup) null);
        setView(inflate);
        this.f12933tv = (TextView) inflate.findViewById(R.id.jd_toast_txt);
    }

    private void initCenterNoIconView(Context context) {
        View inflate = getLayoutInflater(context).inflate(R.layout.jd_common_toast_style_bottom, (ViewGroup) null);
        setView(inflate);
        this.f12933tv = (TextView) inflate.findViewById(R.id.jd_toast_txt);
        setGravity(17, 0, 0);
    }

    private void initCenterView(Context context) {
        View inflate = getLayoutInflater(context).inflate(R.layout.jd_common_toast_style_center, (ViewGroup) null);
        setView(inflate);
        this.f12933tv = (TextView) inflate.findViewById(R.id.jd_toast_txt);
        this.iv = (ImageView) inflate.findViewById(R.id.jd_toast_image);
        setGravity(17, 0, 0);
    }

    private void initCustomCenter(Context context) {
        View inflate = getLayoutInflater(context).inflate(R.layout.jd_toast_center, (ViewGroup) null);
        setView(inflate);
        this.centerRootLayout = (FrameLayout) inflate.findViewById(R.id.rootView);
        setGravity(17, 0, 0);
    }

    public void setCustomViewByCenter(View view) {
        FrameLayout frameLayout = this.centerRootLayout;
        if (frameLayout != null) {
            frameLayout.addView(view);
        }
    }

    public void setImage(byte b) {
        ImageView imageView = this.iv;
        if (imageView == null) {
            return;
        }
        if (b == 1) {
            imageView.setBackgroundResource(R.drawable.jd_toast_exclamation);
        } else if (b == 2) {
            imageView.setBackgroundResource(R.drawable.jd_toast_tick);
        } else if (b != 3) {
        } else {
            imageView.setBackgroundResource(R.drawable.jd_toast_error);
        }
    }

    public void setImageResource(int i2) {
        try {
            this.iv.setBackgroundResource(i2);
        } catch (Exception unused) {
            this.iv.setBackgroundResource(R.drawable.jd_toast_exclamation);
        }
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        if (this.f12933tv == null) {
            return;
        }
        if (com.jingdong.sdk.jdtoast.ToastUtils.isElder()) {
            this.f12933tv.setTextSize(18.0f);
        }
        this.f12933tv.setTypeface(Typeface.MONOSPACE);
        this.f12933tv.setText(charSequence);
    }

    private void initBottomView(Context context, int i2) {
        View inflate = getLayoutInflater(context).inflate(R.layout.jd_common_toast_style_bottom, (ViewGroup) null);
        setView(inflate);
        this.f12933tv = (TextView) inflate.findViewById(R.id.jd_toast_txt);
        setGravity(80, 0, i2);
    }

    public JDToast(Context context, int i2) {
        super(context);
        initBottomView(context, i2);
    }
}
