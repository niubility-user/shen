package com.jingdong.common.XView2.utils.log.floatview;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes5.dex */
public abstract class FloatBase {
    public static final int FULLSCREEN_NOT_TOUCHABLE = 2;
    public static final int FULLSCREEN_TOUCHABLE = 1;
    public static final int WRAP_CONTENT_NOT_TOUCHABLE = 4;
    public static final int WRAP_CONTENT_TOUCHABLE = 3;
    private boolean mAdded;
    protected View mContentView;
    protected Context mContext;
    protected WindowManager mWindowManager;
    private int mGravity = 17;
    private boolean mInvisibleNeed = false;
    private boolean mRequestFocus = false;
    protected int mAddX = 0;
    protected int mAddY = 0;
    private int mShowMode = 3;
    protected WindowManager.LayoutParams mLayoutParams = getLayoutParam(this.mShowMode);

    public FloatBase(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.mContentView = inflateLayout(context, getLayoutId());
        initView();
    }

    private WindowManager.LayoutParams getLayoutParam(int i2) {
        WindowManager.LayoutParams floatLayoutParam;
        if (i2 == 1) {
            floatLayoutParam = getFloatLayoutParam(true, true);
        } else if (i2 == 2) {
            floatLayoutParam = getFloatLayoutParam(true, false);
        } else if (i2 != 3) {
            floatLayoutParam = getFloatLayoutParam(false, false);
        } else {
            floatLayoutParam = getFloatLayoutParam(false, true);
        }
        if (this.mRequestFocus) {
            floatLayoutParam.flags &= -9;
        }
        floatLayoutParam.gravity = this.mGravity;
        return floatLayoutParam;
    }

    private View inflateLayout(Context context, int i2) {
        return LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
    }

    public WindowManager.LayoutParams getFloatLayoutParam(boolean z, boolean z2) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            layoutParams.type = R2.attr.textIsDisplayable;
            if (i2 >= 28) {
                layoutParams.layoutInDisplayCutoutMode = 1;
            }
        } else if (i2 >= 19 && i2 < 23) {
            layoutParams.type = 2005;
        } else {
            layoutParams.type = 2002;
        }
        int i3 = layoutParams.flags | 16777216;
        layoutParams.flags = i3;
        if (z2) {
            layoutParams.flags = i3 | 40;
        } else {
            layoutParams.flags = i3 | 24;
        }
        if (z) {
            layoutParams.flags |= 66816;
            layoutParams.width = -1;
            layoutParams.height = -1;
        } else {
            layoutParams.flags |= 65792;
            layoutParams.width = -2;
            layoutParams.height = -2;
        }
        layoutParams.format = -2;
        return layoutParams;
    }

    public abstract int getLayoutId();

    public boolean getVisibility() {
        View view = this.mContentView;
        return view != null && view.getVisibility() == 0;
    }

    public void gone() {
        View view = this.mContentView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void hide() {
        View view = this.mContentView;
        if (view != null) {
            view.setVisibility(4);
        }
    }

    public abstract void initView();

    public void remove() {
        WindowManager windowManager;
        View view = this.mContentView;
        if (view == null || (windowManager = this.mWindowManager) == null) {
            return;
        }
        windowManager.removeView(view);
        this.mAdded = false;
    }

    public void setGravity(int i2) {
        this.mGravity = i2;
    }

    public void setInvisibleNeed(boolean z) {
        this.mInvisibleNeed = z;
    }

    public void setLayoutParam(int i2) {
        this.mShowMode = i2;
        this.mLayoutParams = getLayoutParam(i2);
    }

    public void setRequestFocus(boolean z) {
        this.mRequestFocus = z;
    }

    public void show() {
    }

    public void toggleVisibility() {
        if (this.mContentView != null) {
            if (getVisibility()) {
                if (this.mInvisibleNeed) {
                    hide();
                    return;
                } else {
                    gone();
                    return;
                }
            }
            show();
        }
    }
}
