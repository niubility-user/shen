package com.jingdong.common.unification.statusbar;

import android.app.Activity;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.jd.lib.un.utils.UnAndroidUtils;

/* loaded from: classes6.dex */
public class AndroidWorkaround {
    private ViewGroup.LayoutParams frameLayoutParams;
    private View mChildOfContent;
    private int usableHeightPrevious;

    private AndroidWorkaround(View view, final Activity activity) {
        this.mChildOfContent = view;
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.unification.statusbar.AndroidWorkaround.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Activity activity2 = activity;
                if (activity2 != null) {
                    AndroidWorkaround.this.possiblyResizeChildOfContent(activity2);
                }
            }
        });
        this.frameLayoutParams = this.mChildOfContent.getLayoutParams();
    }

    public static void assistActivity(View view, Activity activity) {
        new AndroidWorkaround(view, activity);
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent(Activity activity) {
        int i2;
        int computeUsableHeight = computeUsableHeight();
        UnStatusBarTintUtil.computeUsableHeight = computeUsableHeight;
        if (UnStatusBarTintUtil.greaterOrEqualKitkat()) {
            if (UnAndroidUtils.isFullScreenModel(activity)) {
                UnStatusBarTintUtil.removeStatusView(activity);
            } else if (!UnAndroidUtils.checkDeviceHasNavigationBar(activity)) {
                return;
            }
        }
        if ((!UnStatusBarTintUtil.greaterM() || TextUtils.equals("com.jingdong.app.mall.faxianV2.view.activity.DiscoverArticleActivity", activity.getClass().getName())) && computeUsableHeight != (i2 = this.usableHeightPrevious)) {
            if (i2 == 0 || i2 - computeUsableHeight <= UnStatusBarTintUtil.getNavigationBarHeight(activity)) {
                this.frameLayoutParams.height = computeUsableHeight;
                this.mChildOfContent.requestLayout();
                this.usableHeightPrevious = computeUsableHeight;
            }
        }
    }
}
