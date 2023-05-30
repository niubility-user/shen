package com.jingdong.common.web;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.util.MediaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.PermissionRequest;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;

/* loaded from: classes6.dex */
public class BaseWebChromeClient extends WebChromeClient {
    private static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
    public static final int FILECHOOSER_REQUESTCODE = 10;
    public static final int FILECHOOSER_REQUESTCODE_API21 = 12;
    public static final int REQUESTCODE_IMAGE = 291;
    public static final int REQUESTCODE_IMAGE_API21 = 293;
    public static final int REQUESTCODE_RECORD_VIDEO = 13;
    public static final int REQUESTCODE_TAKE_PHOTO = 17;
    public static final int REQUESTCODE_VIDEO = 295;
    public static final int REQUESTCODE_VIDEO_API21 = 297;
    final String TAG = BaseWebChromeClient.class.getSimpleName();
    private String finalUrl = "";
    private Context mContext;
    private View mCustomView;
    private IX5WebChromeClient.CustomViewCallback mCustomViewCallback;
    private FrameLayout mFullContainer;
    private FullScreenCallBack mFullScreenCallBack;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageApi21;

    /* loaded from: classes6.dex */
    public interface FullScreenCallBack {
        void enterFullScreen(View view);

        void exitFullScreen(View view);
    }

    public BaseWebChromeClient(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(JsResult jsResult, JDDialog jDDialog, View view) {
        jsResult.confirm();
        jDDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(JsResult jsResult, JDDialog jDDialog, View view) {
        jsResult.cancel();
        jDDialog.dismiss();
    }

    private boolean checkImageLimit(String str) throws Exception {
        if (!TextUtils.isEmpty(str) && ((float) new File(str).length()) / 1048576.0f > 30) {
            try {
                ToastUtils.shortToast(String.format(this.mContext.getResources().getString(R.string.uni_photo_size_max), 30));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private void configFullScreenVideoView(View view) {
        if (QbSdk.getTbsVersion(this.mContext) >= 45600 || !QbSdk.canLoadX5(this.mContext) || QbSdk.getIsSysWebViewForcedByOuter()) {
            try {
                setStatusBarVisibility(false);
                FrameLayout frameLayout = new FrameLayout(this.mContext);
                this.mFullContainer = frameLayout;
                frameLayout.setBackgroundColor(-16777216);
                FrameLayout frameLayout2 = this.mFullContainer;
                View view2 = this.mCustomView;
                FrameLayout.LayoutParams layoutParams = COVER_SCREEN_PARAMS;
                frameLayout2.addView(view2, layoutParams);
                ((RelativeLayout) ((Activity) this.mContext).findViewById(R.id.root_layout)).addView(this.mFullContainer, layoutParams);
            } catch (Exception e2) {
                Log.e(this.TAG, e2.getMessage(), e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void d(JsResult jsResult, JDDialog jDDialog, View view) {
        jsResult.confirm();
        jDDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void f(JsPromptResult jsPromptResult, JDDialog jDDialog, View view) {
        jsPromptResult.cancel();
        jDDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void g(JDDialog jDDialog, JsPromptResult jsPromptResult, View view) {
        String obj = jDDialog.editText.getText().toString();
        jDDialog.dismiss();
        jsPromptResult.confirm(obj);
    }

    private String getPermissionDesc(String str, String str2, String str3) {
        if (ContextCompat.checkSelfPermission(this.mContext, str3) == -1) {
            if (TextUtils.isEmpty(str)) {
                return str2;
            }
            return str + "\u548c" + str2;
        }
        return str;
    }

    private void getPhotoFile(String str, boolean z, boolean z2) {
        if (z) {
            MediaUtils.jumpToGetPhoto(this.mContext, true, 17);
        } else {
            MediaUtils.getPhotoWithDialog(this.mContext, z2 ? 293 : 291, 17, new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.web.BaseWebChromeClient.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    OKLog.d(BaseWebChromeClient.this.TAG, "getPhotoFile ondialog cancel");
                    BaseWebChromeClient.this.destroyUploadMessage();
                }
            });
        }
    }

    private void getVideoFile(String str, boolean z, boolean z2) {
        if (z) {
            MediaUtils.jumpToGetVideo(this.mContext, true, 2, 60, 13);
        } else {
            MediaUtils.getVideoFileWithDialog(this.mContext, str, 2, 60, z2 ? 297 : 295, 13, new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.web.BaseWebChromeClient.2
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    OKLog.d(BaseWebChromeClient.this.TAG, "getVideoFile ondialog cancel");
                    BaseWebChromeClient.this.destroyUploadMessage();
                }
            });
        }
    }

    private void gotoFileChooser(String str, boolean z, boolean z2, int i2) {
        String str2;
        Log.d(this.TAG, "gotoFileChooser,acceptType:" + str);
        if (TextUtils.isEmpty(str)) {
            str = "video/*;image/*;audio/*";
        }
        if (isOnlyImageAccepted(str)) {
            getPhotoFile(str, z, z2);
            str2 = "getPhotoFile";
        } else if (isOnlyVideoAccepted(str)) {
            getVideoFile(str, z, z2);
            str2 = "getVideoFile";
        } else {
            Context context = this.mContext;
            if (context instanceof Activity) {
                MediaUtils.fileChoose((Activity) context, str, z2 ? 12 : 10);
            } else {
                destroyUploadMessage();
            }
            str2 = "fileChoose";
        }
        ExceptionReporter.reportWebViewCommonError("openFileChooser", TextUtils.isEmpty(this.finalUrl) ? "" : this.finalUrl, str, str2 + "--" + i2);
    }

    private void hide() {
        if (this.mFullContainer != null) {
            try {
                setStatusBarVisibility(true);
                ((ViewGroup) ((Activity) this.mContext).findViewById(R.id.root_layout)).removeView(this.mFullContainer);
                this.mFullContainer = null;
                this.mCustomView = null;
            } catch (Exception e2) {
                Log.e(this.TAG, e2.getMessage(), e2);
            }
        }
        FullScreenCallBack fullScreenCallBack = this.mFullScreenCallBack;
        if (fullScreenCallBack != null) {
            fullScreenCallBack.exitFullScreen(this.mCustomView);
        }
        IX5WebChromeClient.CustomViewCallback customViewCallback = this.mCustomViewCallback;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
        }
        this.mCustomViewCallback = null;
    }

    private boolean isOnlyImageAccepted(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(";|,")) == null || split.length <= 0) {
            return false;
        }
        for (String str2 : split) {
            if (!str2.contains("image/")) {
                return false;
            }
        }
        return true;
    }

    private boolean isOnlyVideoAccepted(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(";|,")) == null || split.length <= 0) {
            return false;
        }
        for (String str2 : split) {
            if (!str2.contains(MediaUtil.TRACK_VIDEO)) {
                return false;
            }
        }
        return true;
    }

    private void onScreenSwitch(boolean z) {
        if (z) {
            ((Activity) this.mContext).setRequestedOrientation(1);
        } else {
            ((Activity) this.mContext).setRequestedOrientation(0);
        }
    }

    private synchronized void selectMediaBack(Intent intent, boolean z, int i2, int i3, boolean z2) {
        Uri uri;
        List<String> processVideoSelectResult;
        Uri[] uriArr;
        List<String> processVideoSelectResult2;
        try {
            if (z2) {
                ValueCallback<Uri[]> valueCallback = this.mUploadMessageApi21;
                if (valueCallback == null) {
                    return;
                }
                if (i2 == i3 && intent != null) {
                    if (z) {
                        processVideoSelectResult2 = MediaUtils.processPhotoSelectResult(intent);
                    } else {
                        processVideoSelectResult2 = MediaUtils.processVideoSelectResult(intent);
                    }
                    if (processVideoSelectResult2 != null && !processVideoSelectResult2.isEmpty()) {
                        ArrayList arrayList = new ArrayList(processVideoSelectResult2.size());
                        for (String str : processVideoSelectResult2) {
                            if (!TextUtils.isEmpty(str)) {
                                if (z && checkImageLimit(str)) {
                                    destroyUploadMessage();
                                    return;
                                }
                                arrayList.add(Uri.fromFile(new File(str)));
                            }
                        }
                        uriArr = (Uri[]) arrayList.toArray(new Uri[0]);
                        this.mUploadMessageApi21 = null;
                        valueCallback.onReceiveValue(uriArr);
                    }
                }
                uriArr = null;
                this.mUploadMessageApi21 = null;
                valueCallback.onReceiveValue(uriArr);
            } else {
                ValueCallback<Uri> valueCallback2 = this.mUploadMessage;
                if (valueCallback2 == null) {
                    return;
                }
                if (i2 == i3 && intent != null) {
                    if (z) {
                        processVideoSelectResult = MediaUtils.processPhotoSelectResult(intent);
                    } else {
                        processVideoSelectResult = MediaUtils.processVideoSelectResult(intent);
                    }
                    if (processVideoSelectResult != null && !processVideoSelectResult.isEmpty()) {
                        String str2 = processVideoSelectResult.get(0);
                        if (!TextUtils.isEmpty(str2)) {
                            if (z && checkImageLimit(str2)) {
                                destroyUploadMessage();
                                return;
                            }
                            uri = Uri.fromFile(new File(str2));
                            this.mUploadMessage = null;
                            valueCallback2.onReceiveValue(uri);
                        }
                    }
                }
                uri = null;
                this.mUploadMessage = null;
                valueCallback2.onReceiveValue(uri);
            }
        } catch (Exception e2) {
            Log.e(this.TAG, "selectMediaBack", e2);
            destroyUploadMessage();
        }
    }

    private void setStatusBarVisibility(boolean z) {
        if (z) {
            ((Activity) this.mContext).getWindow().clearFlags(1024);
        } else {
            ((Activity) this.mContext).getWindow().setFlags(1024, 1024);
        }
    }

    private void show(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        IX5WebChromeClient.CustomViewCallback customViewCallback2;
        if (this.mCustomView != null && (customViewCallback2 = this.mCustomViewCallback) != null) {
            customViewCallback2.onCustomViewHidden();
        } else if (view == null || customViewCallback == null) {
        } else {
            this.mCustomView = view;
            this.mCustomViewCallback = customViewCallback;
            configFullScreenVideoView(view);
            FullScreenCallBack fullScreenCallBack = this.mFullScreenCallBack;
            if (fullScreenCallBack != null) {
                fullScreenCallBack.enterFullScreen(view);
            }
        }
    }

    private synchronized void uploadFile(ValueCallback<Uri> valueCallback, String str, String str2) {
        Log.d(this.TAG, "uploadFile called by webviewcore capture==" + str2);
        this.mUploadMessage = valueCallback;
        if (str2 != null) {
            str2 = str2.trim();
        }
        gotoFileChooser(str, (TextUtils.isEmpty(str2) || "filesystem".equalsIgnoreCase(str2)) ? false : true, false, 1);
    }

    private synchronized void uploadFileApi21(ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Log.d(this.TAG, "uploadFileApi21 called by webviewcore");
        this.mUploadMessageApi21 = valueCallback;
        String str = "";
        int i2 = fileChooserParams.getMode() == 0 ? 1 : 9;
        if (fileChooserParams != null && fileChooserParams.getAcceptTypes() != null) {
            if (fileChooserParams.getAcceptTypes().length == 1) {
                str = fileChooserParams.getAcceptTypes()[0];
            } else if (fileChooserParams.getAcceptTypes().length > 1) {
                for (String str2 : fileChooserParams.getAcceptTypes()) {
                    if (!TextUtils.isEmpty(str2)) {
                        str = TextUtils.isEmpty(str) ? str2 : str + DYConstants.DY_REGEX_COMMA + str2;
                    }
                }
            }
        }
        gotoFileChooser(str, fileChooserParams.isCaptureEnabled(), true, i2);
    }

    public synchronized void destroyUploadMessage() {
        ValueCallback<Uri> valueCallback = this.mUploadMessage;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
            this.mUploadMessage = null;
        }
        ValueCallback<Uri[]> valueCallback2 = this.mUploadMessageApi21;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(null);
            this.mUploadMessageApi21 = null;
        }
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public View getVideoLoadingProgressView() {
        return ImageUtil.inflate(this.mContext, R.layout.jd_webview_fullscreen_load, (ViewGroup) null);
    }

    public boolean onBack() {
        if (this.mCustomView == null || this.mCustomViewCallback == null) {
            return false;
        }
        hide();
        return true;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void onExceededDatabaseQuota(String str, String str2, long j2, long j3, long j4, WebStorage.QuotaUpdater quotaUpdater) {
        Log.d(this.TAG, "onExceededDatabaseQuota,currentQuota:" + j2 + "   estimatedSize:" + j3 + "   totalUsedQuota:" + j4);
        quotaUpdater.updateQuota(j3);
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void onHideCustomView() {
        super.onHideCustomView();
        Log.d(this.TAG, "onHideCustomView()");
        hide();
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
        if ((BaseInfo.getAndroidSDKVersion() == 21 || BaseInfo.getAndroidSDKVersion() == 22) && this.mContext != null) {
            JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
            Context context = this.mContext;
            final JDDialog createJdDialogWithStyle1 = jDDialogFactory.createJdDialogWithStyle1(context, str2, context.getString(R.string.dialog_confirm));
            createJdDialogWithStyle1.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseWebChromeClient.a(JsResult.this, createJdDialogWithStyle1, view);
                }
            });
            createJdDialogWithStyle1.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.web.h
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    JsResult.this.cancel();
                }
            });
            createJdDialogWithStyle1.show();
            return true;
        }
        return super.onJsAlert(webView, str, str2, jsResult);
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
        if ((BaseInfo.getAndroidSDKVersion() == 21 || BaseInfo.getAndroidSDKVersion() == 22) && this.mContext != null) {
            JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
            Context context = this.mContext;
            final JDDialog createJdDialogWithStyle2 = jDDialogFactory.createJdDialogWithStyle2(context, str2, context.getString(R.string.cancel), this.mContext.getString(R.string.dialog_confirm));
            createJdDialogWithStyle2.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseWebChromeClient.c(JsResult.this, createJdDialogWithStyle2, view);
                }
            });
            createJdDialogWithStyle2.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseWebChromeClient.d(JsResult.this, createJdDialogWithStyle2, view);
                }
            });
            createJdDialogWithStyle2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.web.d
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    JsResult.this.cancel();
                }
            });
            createJdDialogWithStyle2.show();
            return true;
        }
        return super.onJsConfirm(webView, str, str2, jsResult);
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
        if ((BaseInfo.getAndroidSDKVersion() == 21 || BaseInfo.getAndroidSDKVersion() == 22) && this.mContext != null) {
            JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
            Context context = this.mContext;
            final JDDialog createJdDialogWithStyle3 = jDDialogFactory.createJdDialogWithStyle3(context, str2, str3, "", context.getString(R.string.cancel), this.mContext.getString(R.string.dialog_confirm));
            createJdDialogWithStyle3.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseWebChromeClient.f(JsPromptResult.this, createJdDialogWithStyle3, view);
                }
            });
            createJdDialogWithStyle3.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseWebChromeClient.g(JDDialog.this, jsPromptResult, view);
                }
            });
            createJdDialogWithStyle3.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.web.b
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    JsPromptResult.this.cancel();
                }
            });
            createJdDialogWithStyle3.editText.getLayoutParams().width = -1;
            createJdDialogWithStyle3.imageView.setVisibility(8);
            createJdDialogWithStyle3.show();
            return true;
        }
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        if (SwitchQueryFetcher.getSwitchBooleanValue("permissionDisable", false)) {
            return;
        }
        String[] resources = permissionRequest.getResources();
        permissionRequest.grant(resources);
        String str = "";
        for (String str2 : resources) {
            str2.hashCode();
            if (str2.equals("android.webkit.resource.VIDEO_CAPTURE")) {
                str = getPermissionDesc(str, "\u76f8\u673a", "android.permission.CAMERA");
            } else if (str2.equals("android.webkit.resource.AUDIO_CAPTURE")) {
                str = getPermissionDesc(str, "\u5f55\u97f3", "android.permission.RECORD_AUDIO");
            }
        }
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this.mContext, "\u8bf7\u5728\u7cfb\u7edf\u8bbe\u7f6e\u91cc\u7ed9\u4eac\u4e1c\u8bbe\u7f6e" + str + "\u6743\u9650", 0).show();
        }
        Log.d(this.TAG, "onPermissionRequest: " + Arrays.toString(resources));
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        super.onShowCustomView(view, customViewCallback);
        Log.d(this.TAG, "onShowCustomView():" + view + "  " + customViewCallback);
        show(view, customViewCallback);
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        uploadFileApi21(valueCallback, fileChooserParams);
        return true;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        uploadFile(valueCallback, str, str2);
    }

    public synchronized void recordMediaBack(Intent intent, boolean z, int i2, int i3) {
        String processVideoCaptureResult;
        String processVideoCaptureResult2;
        try {
        } catch (Exception e2) {
            Log.e(this.TAG, "recordMediaBack", e2);
            destroyUploadMessage();
        }
        if (BaseInfo.getAndroidSDKVersion() < 21 && !QbSdk.canLoadX5(this.mContext)) {
            ValueCallback<Uri> valueCallback = this.mUploadMessage;
            if (valueCallback == null) {
                return;
            }
            if (intent == null) {
                this.mUploadMessage = null;
                valueCallback.onReceiveValue(null);
                return;
            }
            if (z) {
                processVideoCaptureResult2 = MediaUtils.processPhotoCaptureResult(intent);
            } else {
                processVideoCaptureResult2 = MediaUtils.processVideoCaptureResult(intent);
            }
            if (!TextUtils.isEmpty(processVideoCaptureResult2)) {
                Uri fromFile = Uri.fromFile(new File(processVideoCaptureResult2));
                this.mUploadMessage = null;
                valueCallback.onReceiveValue(fromFile);
            } else {
                this.mUploadMessage = null;
                valueCallback.onReceiveValue(null);
            }
        }
        ValueCallback<Uri[]> valueCallback2 = this.mUploadMessageApi21;
        if (valueCallback2 == null) {
            return;
        }
        if (intent == null) {
            this.mUploadMessageApi21 = null;
            valueCallback2.onReceiveValue(null);
            return;
        }
        if (z) {
            processVideoCaptureResult = MediaUtils.processPhotoCaptureResult(intent);
        } else {
            processVideoCaptureResult = MediaUtils.processVideoCaptureResult(intent);
        }
        if (!TextUtils.isEmpty(processVideoCaptureResult)) {
            Uri[] uriArr = {Uri.fromFile(new File(processVideoCaptureResult))};
            this.mUploadMessageApi21 = null;
            valueCallback2.onReceiveValue(uriArr);
        } else {
            this.mUploadMessageApi21 = null;
            valueCallback2.onReceiveValue(null);
        }
    }

    public synchronized void selectFileBack(Intent intent, int i2, int i3, boolean z) {
        try {
            if (z) {
                ValueCallback<Uri[]> valueCallback = this.mUploadMessageApi21;
                if (valueCallback == null) {
                    return;
                }
                Uri data = (intent == null || i2 != i3) ? null : intent.getData();
                this.mUploadMessageApi21 = null;
                if (data != null) {
                    Log.d(this.TAG, "selectFileBack-URI: " + data);
                    valueCallback.onReceiveValue(new Uri[]{data});
                } else {
                    valueCallback.onReceiveValue(null);
                }
            } else {
                ValueCallback<Uri> valueCallback2 = this.mUploadMessage;
                if (valueCallback2 == null) {
                    return;
                }
                Uri data2 = (intent == null || i2 != i3) ? null : intent.getData();
                this.mUploadMessage = null;
                if (data2 != null) {
                    Log.d(this.TAG, "selectFileBack-URI: " + data2);
                    valueCallback2.onReceiveValue(data2);
                } else {
                    valueCallback2.onReceiveValue(null);
                }
            }
        } catch (Exception e2) {
            Log.e(this.TAG, "selectFileBack", e2);
            destroyUploadMessage();
        }
    }

    public synchronized void selectImageBack(Intent intent, int i2, int i3, boolean z) {
        selectMediaBack(intent, true, i2, i3, z);
    }

    public synchronized void selectVideoBack(Intent intent, int i2, int i3, boolean z) {
        selectMediaBack(intent, false, i2, i3, z);
    }

    public void setFullScreenCallBack(FullScreenCallBack fullScreenCallBack) {
        this.mFullScreenCallBack = fullScreenCallBack;
    }

    public void setUrl(String str) {
        this.finalUrl = str;
    }

    @Override // com.tencent.smtt.sdk.WebChromeClient
    public void onShowCustomView(View view, int i2, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        super.onShowCustomView(view, i2, customViewCallback);
        Log.d(this.TAG, "onShowCustomView():" + view + "   " + i2 + "  " + customViewCallback);
        show(view, customViewCallback);
    }
}
