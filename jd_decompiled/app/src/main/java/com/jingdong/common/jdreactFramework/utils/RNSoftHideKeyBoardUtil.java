package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;

/* loaded from: classes5.dex */
public class RNSoftHideKeyBoardUtil {
    private int contentHeight;
    private FrameLayout.LayoutParams frameLayoutParams;
    private boolean isfirst = true;
    private View mChildOfContent;
    private int statusBarHeight;
    private int usableHeightPrevious;

    private RNSoftHideKeyBoardUtil(final Activity activity) {
        View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.mChildOfContent = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.jdreactFramework.utils.RNSoftHideKeyBoardUtil.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (RNSoftHideKeyBoardUtil.this.isfirst) {
                    RNSoftHideKeyBoardUtil rNSoftHideKeyBoardUtil = RNSoftHideKeyBoardUtil.this;
                    rNSoftHideKeyBoardUtil.contentHeight = rNSoftHideKeyBoardUtil.mChildOfContent.getHeight();
                    RNSoftHideKeyBoardUtil.this.isfirst = false;
                }
                RNSoftHideKeyBoardUtil.this.possiblyResizeChildOfContent(activity);
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) this.mChildOfContent.getLayoutParams();
    }

    public static void assistActivity(Activity activity) {
        if (isSoftHideKeyBoard(activity)) {
            activity.getWindow().setSoftInputMode(16);
            new RNSoftHideKeyBoardUtil(activity);
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }

    private int getStatusBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return (int) resources.getDimension(identifier);
        }
        return 0;
    }

    public static boolean isSoftHideKeyBoard(Activity activity) {
        JDReactModuleEntity reactEntity;
        if (!(activity instanceof JDReactNativeBaseCommonActivity) || (reactEntity = ((JDReactNativeBaseCommonActivity) activity).getReactEntity()) == null) {
            return false;
        }
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDReact", "JDReactAdjustResize", reactEntity.getmModuleName(), ""));
    }

    private boolean isTransparentEnable(Activity activity) {
        if (activity instanceof JDReactNativeBaseCommonActivity) {
            JDReactNativeBaseCommonActivity jDReactNativeBaseCommonActivity = (JDReactNativeBaseCommonActivity) activity;
            if (jDReactNativeBaseCommonActivity.getReactEntity() != null) {
                return jDReactNativeBaseCommonActivity.getReactEntity().getTransparentEnable();
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent(Activity activity) {
        int computeUsableHeight = computeUsableHeight();
        if (computeUsableHeight != this.usableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            int i2 = height - computeUsableHeight;
            int statusBarHeight = isTransparentEnable(activity) ? getStatusBarHeight(activity) : 0;
            this.statusBarHeight = statusBarHeight;
            if (i2 > height / 4) {
                if (Build.VERSION.SDK_INT >= 19) {
                    this.frameLayoutParams.height = (height - i2) + statusBarHeight;
                } else {
                    this.frameLayoutParams.height = height - i2;
                }
            } else {
                this.frameLayoutParams.height = this.contentHeight;
            }
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = computeUsableHeight;
        }
    }
}
