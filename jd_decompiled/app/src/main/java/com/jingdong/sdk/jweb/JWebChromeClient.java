package com.jingdong.sdk.jweb;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import androidx.annotation.Keep;

/* loaded from: classes7.dex */
public class JWebChromeClient {

    /* loaded from: classes7.dex */
    public interface CustomViewCallback {
        void onCustomViewHidden();
    }

    @Keep
    /* loaded from: classes7.dex */
    public static abstract class FileChooserParams {
        public static final int MODE_OPEN = 0;
        public static final int MODE_OPEN_MULTIPLE = 1;
        public static final int MODE_SAVE = 3;

        public abstract Intent createIntent();

        public abstract String[] getAcceptTypes();

        public abstract String getFilenameHint();

        public abstract int getMode();

        public abstract CharSequence getTitle();

        public abstract boolean isCaptureEnabled();
    }

    public View getVideoLoadingProgressView() {
        return null;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
    }

    public void onHideCustomView() {
    }

    public boolean onJsAlert(JDWebView jDWebView, String str, String str2, d dVar) {
        return false;
    }

    public boolean onJsConfirm(JDWebView jDWebView, String str, String str2, d dVar) {
        return false;
    }

    public boolean onJsPrompt(JDWebView jDWebView, String str, String str2, String str3, c cVar) {
        return false;
    }

    public void onPermissionRequest(JWebPermissionRequest jWebPermissionRequest) {
    }

    public void onProgressChanged(JDWebView jDWebView, int i2) {
    }

    public void onReceivedTitle(JDWebView jDWebView, String str) {
    }

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
    }

    public boolean onShowFileChooser(JWebView jWebView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        return false;
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
    }
}
