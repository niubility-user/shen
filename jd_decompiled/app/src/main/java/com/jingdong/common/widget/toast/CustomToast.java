package com.jingdong.common.widget.toast;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.StringRes;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jingdong.common.UnLog;

/* loaded from: classes12.dex */
public class CustomToast {
    public static final int LENGTH_LONG = 3500;
    public static final int LENGTH_SHORT = 2000;
    private final String LIFE_CYCLE_FRAGMENT_TAG = "custom_toast_life_cycle_fragment_tag";
    private boolean initSuccess = false;
    private ICustomLifecycleListener lifecycleListener;
    private final Activity mContext;
    private int mDuration;
    private View mNextView;
    private final TN mTN;
    private final CustomToastManager toastManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface ITnCallback {
        void hide();

        void show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class TN implements ITnCallback {
        int mDuration;
        int mGravity;
        final Handler mHandler;
        float mHorizontalMargin;
        View mNextView;
        private final WindowManager.LayoutParams mParams;
        float mVerticalMargin;
        View mView;
        WindowManager mWM;
        int mX;
        int mY;
        int status;
        final Runnable mShow = new Runnable() { // from class: com.jingdong.common.widget.toast.CustomToast.TN.1
            @Override // java.lang.Runnable
            public void run() {
                TN.this.handleShow();
            }
        };
        final Runnable mHide = new Runnable() { // from class: com.jingdong.common.widget.toast.CustomToast.TN.2
            @Override // java.lang.Runnable
            public void run() {
                TN.this.handleHide();
                TN.this.mNextView = null;
            }
        };

        TN() {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mParams = layoutParams;
            this.mHandler = new Handler();
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = 16973828;
            layoutParams.setTitle("Toast");
            layoutParams.flags = 152;
            layoutParams.type = 1000;
        }

        private boolean isParentWindowShow() {
            return this.status == 0;
        }

        void handleHide() {
            View view = this.mView;
            if (view != null) {
                if (view.getParent() != null) {
                    this.mWM.removeView(this.mView);
                }
                this.mView = null;
            }
        }

        void handleShow() {
            int i2;
            if (this.mView != this.mNextView) {
                handleHide();
                View view = this.mNextView;
                this.mView = view;
                this.mWM = (WindowManager) view.getContext().getSystemService("window");
                Configuration configuration = this.mView.getContext().getResources().getConfiguration();
                if (Build.VERSION.SDK_INT >= 17) {
                    i2 = Gravity.getAbsoluteGravity(this.mGravity, configuration.getLayoutDirection());
                } else {
                    i2 = this.mGravity;
                }
                WindowManager.LayoutParams layoutParams = this.mParams;
                layoutParams.gravity = i2;
                if ((i2 & 7) == 7) {
                    layoutParams.horizontalWeight = 1.0f;
                }
                if ((i2 & 112) == 112) {
                    layoutParams.verticalWeight = 1.0f;
                }
                layoutParams.x = this.mX;
                layoutParams.y = this.mY;
                layoutParams.verticalMargin = this.mVerticalMargin;
                layoutParams.horizontalMargin = this.mHorizontalMargin;
                try {
                    if (this.mView.getParent() != null) {
                        this.mWM.removeView(this.mView);
                    }
                    this.mWM.addView(this.mView, this.mParams);
                } catch (Exception e2) {
                    if (UnLog.D) {
                        UnLog.e("CustomToast", "CustomToast\u51fa\u73b0\u5f02\u5e38\uff1a" + e2.toString());
                    }
                }
            }
        }

        @Override // com.jingdong.common.widget.toast.CustomToast.ITnCallback
        public void hide() {
            this.mHandler.post(this.mHide);
        }

        @Override // com.jingdong.common.widget.toast.CustomToast.ITnCallback
        public void show() {
            if (isParentWindowShow()) {
                this.mHandler.post(this.mShow);
            }
        }
    }

    /* loaded from: classes12.dex */
    class ToastStatus {
        public static final int HIDDEN = 1;
        public static final int SHOW = 0;

        ToastStatus() {
        }
    }

    public CustomToast(Activity activity) {
        this.mContext = activity;
        TN tn = new TN();
        this.mTN = tn;
        tn.mY = dip2px(activity, 64.0f);
        tn.mGravity = 81;
        this.toastManager = CustomToastManager.getInstance();
        initLifecycleListener();
        registerLifecycleListener();
    }

    private static int calculateDuration(int i2) {
        if (i2 < 0) {
            return 0;
        }
        if (i2 == 0) {
            return 2000;
        }
        if (i2 == 1) {
            return 3500;
        }
        return i2;
    }

    private boolean checkContext(Activity activity) {
        return Build.VERSION.SDK_INT >= 17 ? (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true : (activity == null || activity.isFinishing()) ? false : true;
    }

    private int dip2px(Context context, float f2) {
        return (int) ((f2 * UnDeviceInfo.getDensity()) + 0.5f);
    }

    private void initLifecycleListener() {
        if (this.lifecycleListener == null) {
            this.lifecycleListener = new ICustomLifecycleListener() { // from class: com.jingdong.common.widget.toast.CustomToast.1
                @Override // com.jingdong.common.widget.toast.ICustomLifecycleListener
                public void onStart() {
                    CustomToast.this.mTN.status = 0;
                }

                @Override // com.jingdong.common.widget.toast.ICustomLifecycleListener
                public void onStop() {
                    CustomToast.this.mTN.status = 1;
                    CustomToast.this.cancel();
                }
            };
        }
    }

    private boolean isParentWindowShow() {
        TN tn = this.mTN;
        return tn != null && tn.status == 0;
    }

    public static CustomToast makeText(Activity activity, CharSequence charSequence, int i2) {
        CustomToast customToast = new CustomToast(activity);
        View view = Toast.makeText(activity, charSequence, 0).getView();
        ((TextView) view.findViewById(16908299)).setText(charSequence);
        customToast.mNextView = view;
        customToast.mDuration = calculateDuration(i2);
        return customToast;
    }

    private void registerLifecycleListener() {
        this.initSuccess = false;
        if (checkContext(this.mContext)) {
            FragmentManager fragmentManager = this.mContext.getFragmentManager();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("custom_toast_life_cycle_fragment_tag");
            if (findFragmentByTag == null) {
                try {
                    CustomLifecycleFragment customLifecycleFragment = new CustomLifecycleFragment();
                    customLifecycleFragment.addLifecycleListener(this.lifecycleListener);
                    FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                    beginTransaction.add(customLifecycleFragment, "custom_toast_life_cycle_fragment_tag");
                    beginTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                    this.initSuccess = true;
                } catch (Exception e2) {
                    if (UnLog.D) {
                        UnLog.e("CustomToast", "CustomToast\u521d\u59cb\u5316\u51fa\u73b0\u5f02\u5e38\uff1a" + e2.toString());
                    }
                    this.initSuccess = false;
                }
            } else if (findFragmentByTag instanceof CustomLifecycleFragment) {
                ((CustomLifecycleFragment) findFragmentByTag).addLifecycleListener(this.lifecycleListener);
                this.initSuccess = true;
            } else {
                this.initSuccess = false;
            }
        }
    }

    public void cancel() {
        this.mTN.hide();
        this.toastManager.cancelToast(this.mTN);
    }

    public Activity getContext() {
        return this.mContext;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public int getGravity() {
        return this.mTN.mGravity;
    }

    public float getHorizontalMargin() {
        return this.mTN.mHorizontalMargin;
    }

    public float getVerticalMargin() {
        return this.mTN.mVerticalMargin;
    }

    public View getView() {
        return this.mNextView;
    }

    public WindowManager.LayoutParams getWindowParams() {
        return this.mTN.mParams;
    }

    public int getXOffset() {
        return this.mTN.mX;
    }

    public int getYOffset() {
        return this.mTN.mY;
    }

    public boolean isInitSuccess() {
        return this.initSuccess;
    }

    public void setDuration(int i2) {
        int calculateDuration = calculateDuration(i2);
        this.mDuration = calculateDuration;
        this.mTN.mDuration = calculateDuration;
    }

    public void setGravity(int i2, int i3, int i4) {
        TN tn = this.mTN;
        tn.mGravity = i2;
        tn.mX = i3;
        tn.mY = i4;
    }

    public void setMargin(float f2, float f3) {
        TN tn = this.mTN;
        tn.mHorizontalMargin = f2;
        tn.mVerticalMargin = f3;
    }

    public void setText(@StringRes int i2) {
        setText(this.mContext.getText(i2));
    }

    public void setView(View view) {
        this.mNextView = view;
    }

    public void show() {
        View view = this.mNextView;
        if (view != null) {
            TN tn = this.mTN;
            tn.mNextView = view;
            if (isParentWindowShow() && this.initSuccess) {
                this.toastManager.enqueueToast(tn, this.mDuration);
                return;
            }
            return;
        }
        throw new RuntimeException("setView must have been called");
    }

    public void setText(CharSequence charSequence) {
        View view = this.mNextView;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(16908299);
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        throw new RuntimeException("This Toast was not created with Toast.makeText()");
    }

    public static CustomToast makeText(Activity activity, @StringRes int i2, int i3) throws Resources.NotFoundException {
        return makeText(activity, activity.getResources().getText(i2), i3);
    }
}
