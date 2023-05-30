package com.jdpay.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes18.dex */
public class JDRToast {
    private static final int CHAR_DURATION_LIMIT = 20;
    public static final int LENGTH_ALWAYS = 0;
    public static final int LENGTH_LONG = 4;
    public static final int LENGTH_SHORT = 2;
    private Method mHide;
    private WindowManager.LayoutParams mParams;
    private Method mShow;
    private Object mTN;
    private Toast mToast;
    private int mDuration = 2;
    private int mAnimations = -1;
    private boolean mIsShow = false;
    private Handler mHandler = new Handler();
    private Runnable hideRunnable = new Runnable() { // from class: com.jdpay.widget.JDRToast.1
        @Override // java.lang.Runnable
        public void run() {
            JDRToast.this.hide();
        }
    };

    public JDRToast(Context context) {
        if (this.mToast == null) {
            this.mToast = new Toast(context);
            setGravity(17, 0, 0);
        }
    }

    private void initTN() {
        try {
            Field declaredField = this.mToast.getClass().getDeclaredField("mTN");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.mToast);
            this.mTN = obj;
            try {
                this.mShow = obj.getClass().getMethod("show", new Class[0]);
            } catch (Exception unused) {
                this.mShow = this.mTN.getClass().getMethod("show", IBinder.class);
            }
            this.mHide = this.mTN.getClass().getMethod(ExposureRvUtils.HIDE, new Class[0]);
            Field declaredField2 = this.mTN.getClass().getDeclaredField("mParams");
            declaredField2.setAccessible(true);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) declaredField2.get(this.mTN);
            this.mParams = layoutParams;
            layoutParams.flags = 40;
            int i2 = this.mAnimations;
            if (i2 != -1) {
                layoutParams.windowAnimations = i2;
            }
            Field declaredField3 = this.mTN.getClass().getDeclaredField("mNextView");
            declaredField3.setAccessible(true);
            declaredField3.set(this.mTN, this.mToast.getView());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        setGravity(17, 0, 0);
    }

    public static JDRToast makeText(Context context, int i2) {
        return makeText(context, context.getResources().getText(i2));
    }

    @SuppressLint({"ShowToast"})
    public static JDRToast makeText(Context context, CharSequence charSequence) {
        if (context == null) {
            return null;
        }
        int i2 = (charSequence == null || charSequence.length() <= 20) ? 0 : 1;
        Toast makeText = Toast.makeText(context, charSequence, i2);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(com.jdpay.membercode.R.layout.jdpay_common_cp_toast, (ViewGroup) null);
        ((TextView) inflate.findViewById(com.jdpay.membercode.R.id.txt_tip)).setText(charSequence);
        makeText.setView(inflate);
        makeText.setGravity(17, 0, 0);
        JDRToast jDRToast = new JDRToast(context);
        jDRToast.mToast = makeText;
        jDRToast.mDuration = i2 == 0 ? 2 : 4;
        return jDRToast;
    }

    public int getAnimations() {
        return this.mAnimations;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public int getGravity() {
        return this.mToast.getGravity();
    }

    public float getHorizontalMargin() {
        return this.mToast.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return this.mToast.getVerticalMargin();
    }

    public View getView() {
        return this.mToast.getView();
    }

    public int getXOffset() {
        return this.mToast.getXOffset();
    }

    public int getYOffset() {
        return this.mToast.getYOffset();
    }

    public void hide() {
        if (this.mIsShow) {
            try {
                this.mHide.invoke(this.mTN, new Object[0]);
            } catch (Exception unused) {
            }
            this.mIsShow = false;
        }
    }

    public void setAnimations(int i2) {
        this.mAnimations = i2;
    }

    public void setDuration(int i2) {
        this.mDuration = i2;
    }

    public void setGravity(int i2, int i3, int i4) {
        this.mToast.setGravity(i2, i3, i4);
    }

    public void setMargin(float f2, float f3) {
        this.mToast.setMargin(f2, f3);
    }

    public void setText(int i2) {
        this.mToast.setText(i2);
    }

    public void setText(CharSequence charSequence) {
        this.mToast.setText(charSequence);
    }

    public void setView(View view) {
        this.mToast.setView(view);
    }

    public void show() {
        if (this.mIsShow) {
            return;
        }
        initTN();
        try {
            try {
                this.mShow.invoke(this.mTN, new Object[0]);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            this.mShow.invoke(this.mTN, null);
        }
        this.mIsShow = true;
        if (this.mDuration > 0) {
            this.mHandler.postDelayed(this.hideRunnable, r0 * 1000);
        }
    }
}
