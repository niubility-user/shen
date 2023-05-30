package com.jd.lib.productdetail.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.PopupWindow;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class PopWindowUtil {
    private static final int DELAYED_TIME_WHAT = 100;
    private Handler mHandler;
    private boolean mIsDestory;
    private PopupWindow popWindow;

    public PopWindowUtil() {
    }

    private int[] calculatePopWindowPos(View view, View view2) {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int height = view.getHeight();
        Context context = view.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            int appHeight = PDUtils.getAppHeight(activity);
            int appWidth = PDUtils.getAppWidth(activity);
            view2.measure(0, 0);
            int measuredHeight = view2.getMeasuredHeight();
            int measuredWidth = view2.getMeasuredWidth();
            if ((appHeight - iArr2[1]) - height < measuredHeight) {
                iArr[0] = PDUtils.dip2px(51.0f);
                iArr[1] = iArr2[1] - measuredHeight;
            } else {
                iArr[0] = appWidth - measuredWidth;
                iArr[1] = (iArr2[1] + height) - PDUtils.dip2px(35.0f);
            }
        }
        return iArr;
    }

    public void dismiss() {
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(100)) {
            this.mHandler.removeMessages(100);
        }
        PopupWindow popupWindow = this.popWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.popWindow.dismiss();
    }

    public boolean isShowing() {
        PopupWindow popupWindow = this.popWindow;
        return popupWindow != null && popupWindow.isShowing();
    }

    public void onDestroy() {
        this.mIsDestory = true;
        dismiss();
    }

    public void show(Context context, View view, View view2, int i2, int i3) {
        if (this.popWindow == null) {
            PopupWindow popupWindow = new PopupWindow(view, -1, -2, true);
            this.popWindow = popupWindow;
            popupWindow.setFocusable(false);
            this.popWindow.setBackgroundDrawable(new ColorDrawable());
            this.popWindow.setOutsideTouchable(false);
        }
        if (context != null && (context instanceof Activity)) {
            if (((Activity) context).isFinishing()) {
                return;
            }
            this.popWindow.showAsDropDown(view2, i2, i3);
            return;
        }
        try {
            this.popWindow.showAsDropDown(view2, i2, i3);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public void showAtLocation(View view, View view2) {
        int[] calculatePopWindowPos = calculatePopWindowPos(view2, view);
        this.popWindow.showAtLocation(view2, 8388659, calculatePopWindowPos[0], calculatePopWindowPos[1]);
    }

    public void showAtLocationDelayed(View view, View view2, int i2) {
        int[] calculatePopWindowPos = calculatePopWindowPos(view2, view);
        PopupWindow popupWindow = this.popWindow;
        if (popupWindow != null) {
            popupWindow.showAtLocation(view2, 8388661, calculatePopWindowPos[0], calculatePopWindowPos[1]);
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jd.lib.productdetail.core.utils.PopWindowUtil.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what != 100 || PopWindowUtil.this.popWindow == null || !PopWindowUtil.this.popWindow.isShowing() || PopWindowUtil.this.mIsDestory) {
                        return;
                    }
                    PopWindowUtil.this.dismiss();
                }
            };
        }
        this.mHandler.sendEmptyMessageDelayed(100, i2);
    }

    public PopWindowUtil(View view) {
        if (this.popWindow == null) {
            PopupWindow popupWindow = new PopupWindow(view, -2, -2, true);
            this.popWindow = popupWindow;
            popupWindow.setFocusable(false);
            this.popWindow.setBackgroundDrawable(new ColorDrawable());
            this.popWindow.setOutsideTouchable(false);
        }
    }
}
