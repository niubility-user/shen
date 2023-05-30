package com.jingdong.common.web.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class SysBug5497Workaround extends BaseWebComponent {
    private final String TAG;
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private View mChildOfContent;
    private Handler mHandler;
    private Runnable noSoftInputNotifier;
    private boolean softInputPopupOnce;
    private int usableHeightPrevious;

    public SysBug5497Workaround(IWebUiBinder iWebUiBinder, Activity activity) {
        super(iWebUiBinder);
        this.TAG = "WebView-" + getClass().getSimpleName();
        this.softInputPopupOnce = false;
        this.listener = null;
        this.mHandler = null;
        this.noSoftInputNotifier = null;
        try {
            this.mHandler = new Handler(Looper.getMainLooper());
            View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
            this.mChildOfContent = childAt;
            ViewTreeObserver viewTreeObserver = childAt.getViewTreeObserver();
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.web.util.SysBug5497Workaround.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    SysBug5497Workaround.this.possiblyResizeChildOfContent();
                }
            };
            this.listener = onGlobalLayoutListener;
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        } catch (Exception e2) {
            Log.e(this.TAG, e2.getMessage(), e2);
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWeb(int i2) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        int px2dip = DPIUtil.px2dip(i2);
        Log.d(this.TAG, "\u901a\u77e5H5\u9875\u9762\u65b0\u9ad8\u5ea6 = " + px2dip + "dp");
        this.webUiBinder.getJdWebView().injectJs("javascript:window.AndroidSystemViewNewHeight && AndroidSystemViewNewHeight(" + px2dip + ");");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005d A[Catch: Exception -> 0x00e7, TryCatch #0 {Exception -> 0x00e7, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x0019, B:8:0x001f, B:10:0x002b, B:12:0x0037, B:15:0x0059, B:17:0x005d, B:21:0x007b, B:25:0x00bc, B:34:0x00e4, B:26:0x00c1, B:28:0x00c5, B:30:0x00c9, B:32:0x00cd, B:33:0x00d4), top: B:39:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c1 A[Catch: Exception -> 0x00e7, TryCatch #0 {Exception -> 0x00e7, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x0019, B:8:0x001f, B:10:0x002b, B:12:0x0037, B:15:0x0059, B:17:0x005d, B:21:0x007b, B:25:0x00bc, B:34:0x00e4, B:26:0x00c1, B:28:0x00c5, B:30:0x00c9, B:32:0x00cd, B:33:0x00d4), top: B:39:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void possiblyResizeChildOfContent() {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.SysBug5497Workaround.possiblyResizeChildOfContent():void");
    }

    public void onDestroy() {
        View view;
        if (BaseInfo.getAndroidSDKVersion() >= 16 && this.listener != null && (view = this.mChildOfContent) != null && view.getViewTreeObserver() != null) {
            this.mChildOfContent.getViewTreeObserver().removeOnGlobalLayoutListener(this.listener);
        }
        if (this.noSoftInputNotifier != null) {
            this.noSoftInputNotifier = null;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    public void setSoftInputPopupOnce(boolean z) {
        this.softInputPopupOnce = z;
    }
}
