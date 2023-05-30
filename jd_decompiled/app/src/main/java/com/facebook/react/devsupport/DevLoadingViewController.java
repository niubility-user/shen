package com.facebook.react.devsupport;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class DevLoadingViewController {
    private static boolean sEnabled = true;
    @Nullable
    private PopupWindow mDevLoadingPopup;
    @Nullable
    private TextView mDevLoadingView;
    private final ReactInstanceManagerDevHelper mReactInstanceManagerHelper;

    public DevLoadingViewController(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper) {
        this.mReactInstanceManagerHelper = reactInstanceManagerDevHelper;
    }

    @Nullable
    private Context getContext() {
        return this.mReactInstanceManagerHelper.getCurrentActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideInternal() {
        PopupWindow popupWindow = this.mDevLoadingPopup;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.mDevLoadingPopup.dismiss();
        this.mDevLoadingPopup = null;
        this.mDevLoadingView = null;
    }

    public static void setDevLoadingEnabled(boolean z) {
        sEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInternal(String str) {
        PopupWindow popupWindow = this.mDevLoadingPopup;
        if (popupWindow == null || !popupWindow.isShowing()) {
            Activity currentActivity = this.mReactInstanceManagerHelper.getCurrentActivity();
            if (currentActivity == null) {
                FLog.e(ReactConstants.TAG, "Unable to display loading message because react activity isn't available");
                return;
            }
            Rect rect = new Rect();
            currentActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int i2 = rect.top;
            TextView textView = (TextView) ((LayoutInflater) currentActivity.getSystemService("layout_inflater")).inflate(R.layout.dev_loading_view, (ViewGroup) null);
            this.mDevLoadingView = textView;
            textView.setText(str);
            PopupWindow popupWindow2 = new PopupWindow(this.mDevLoadingView, -1, -2);
            this.mDevLoadingPopup = popupWindow2;
            popupWindow2.setTouchable(false);
            this.mDevLoadingPopup.showAtLocation(currentActivity.getWindow().getDecorView(), 0, 0, i2);
        }
    }

    public void hide() {
        if (sEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevLoadingViewController.3
                @Override // java.lang.Runnable
                public void run() {
                    DevLoadingViewController.this.hideInternal();
                }
            });
        }
    }

    public void showForRemoteJSEnabled() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        showMessage(context.getString(R.string.catalyst_remotedbg_message));
    }

    public void showForUrl(String str) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        try {
            URL url = new URL(str);
            showMessage(context.getString(R.string.catalyst_loading_from_url, url.getHost() + ":" + url.getPort()));
        } catch (MalformedURLException e2) {
            FLog.e(ReactConstants.TAG, "Bundle url format is invalid. \n\n" + e2.toString());
        }
    }

    public void showMessage(final String str) {
        if (sEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevLoadingViewController.1
                @Override // java.lang.Runnable
                public void run() {
                    DevLoadingViewController.this.showInternal(str);
                }
            });
        }
    }

    public void updateProgress(@Nullable final String str, @Nullable final Integer num, @Nullable final Integer num2) {
        if (sEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DevLoadingViewController.2
                @Override // java.lang.Runnable
                public void run() {
                    Integer num3;
                    StringBuilder sb = new StringBuilder();
                    String str2 = str;
                    if (str2 == null) {
                        str2 = "Loading";
                    }
                    sb.append(str2);
                    if (num != null && (num3 = num2) != null && num3.intValue() > 0) {
                        sb.append(String.format(Locale.getDefault(), " %.1f%% (%d/%d)", Float.valueOf((num.intValue() / num2.intValue()) * 100.0f), num, num2));
                    }
                    sb.append("\u2026");
                    if (DevLoadingViewController.this.mDevLoadingView != null) {
                        DevLoadingViewController.this.mDevLoadingView.setText(sb);
                    }
                }
            });
        }
    }
}
