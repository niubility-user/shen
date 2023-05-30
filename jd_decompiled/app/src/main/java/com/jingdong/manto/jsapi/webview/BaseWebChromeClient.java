package com.jingdong.manto.jsapi.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.s;
import com.jingdong.sdk.jweb.JWebChromeClient;
import com.jingdong.sdk.jweb.JWebPermissionRequest;
import com.jingdong.sdk.jweb.JWebView;
import com.tencent.smtt.sdk.QbSdk;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes15.dex */
public class BaseWebChromeClient extends JWebChromeClient {
    private static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
    public static final int FILECHOOSER_REQUESTCODE = 10;
    public static final int FILECHOOSER_REQUESTCODE_API21 = 6444;
    public static final int REQUESTCODE_IMAGE = 291;
    public static final int REQUESTCODE_IMAGE_API21 = 293;
    private String captureFilePath = "";
    private AlertDialog dialog;
    private View mCustomView;
    private JWebChromeClient.CustomViewCallback mCustomViewCallback;
    private ViewGroup mFullContainer;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageApi21;
    private WeakReference<MantoActivityResult> resultWeakReference;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements MantoActivityResult.ResultCallback {
        a() {
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            BaseWebChromeClient.this.selectFileBack(intent, i3, -1, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ Activity b;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            final /* synthetic */ List a;

            a(List list) {
                this.a = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseWebChromeClient.this.closeDialog();
                if (this.a.isEmpty()) {
                    BaseWebChromeClient.this.mUploadMessageApi21.onReceiveValue(new Uri[0]);
                } else {
                    Uri[] uriArr = new Uri[this.a.size()];
                    this.a.toArray(uriArr);
                    BaseWebChromeClient.this.mUploadMessageApi21.onReceiveValue(uriArr);
                }
                BaseWebChromeClient.this.mUploadMessageApi21 = null;
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.webview.BaseWebChromeClient$b$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0529b implements Runnable {
            RunnableC0529b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(com.jingdong.manto.c.a(), "\u5a92\u4f53\u89e3\u6790\u5931\u8d25", 0).show();
                if (BaseWebChromeClient.this.mUploadMessageApi21 != null) {
                    BaseWebChromeClient.this.mUploadMessageApi21.onReceiveValue(new Uri[0]);
                    BaseWebChromeClient.this.mUploadMessageApi21 = null;
                }
            }
        }

        /* loaded from: classes15.dex */
        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseWebChromeClient.this.closeDialog();
            }
        }

        b(List list, Activity activity) {
            this.a = list;
            this.b = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            Executor mainThread;
            c cVar;
            try {
                try {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = this.a.iterator();
                    while (it.hasNext()) {
                        String c2 = h.c(this.b, (Uri) it.next());
                        String a2 = h.a(this.b, Uri.parse(c2));
                        MantoLog.d("webchrome", a2);
                        if (a2 != null) {
                            if (a2.contains("image")) {
                                c2 = com.jingdong.manto.m.f1.b.a(this.b, com.jingdong.manto.m.f1.b.b(this.b, c2));
                            } else if (a2.contains("video")) {
                                c2 = com.jingdong.manto.m.f1.c.a(this.b, c2);
                            }
                            if (!TextUtils.isEmpty(c2)) {
                                arrayList.add(Uri.fromFile(new File(c2)));
                            }
                        }
                    }
                    com.jingdong.manto.b.d().mainThread().execute(new a(arrayList));
                    mainThread = com.jingdong.manto.b.d().mainThread();
                    cVar = new c();
                } catch (Exception unused) {
                    com.jingdong.manto.b.d().mainThread().execute(new RunnableC0529b());
                    mainThread = com.jingdong.manto.b.d().mainThread();
                    cVar = new c();
                }
                mainThread.execute(cVar);
                BaseWebChromeClient.this.captureFilePath = null;
            } catch (Throwable th) {
                com.jingdong.manto.b.d().mainThread().execute(new c());
                BaseWebChromeClient.this.captureFilePath = null;
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements DialogInterface.OnCancelListener {
        c(BaseWebChromeClient baseWebChromeClient) {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
        }
    }

    /* loaded from: classes15.dex */
    class d implements IPermission.PermissionCallBack {
        final /* synthetic */ JWebPermissionRequest a;
        final /* synthetic */ String[] b;

        d(BaseWebChromeClient baseWebChromeClient, JWebPermissionRequest jWebPermissionRequest, String[] strArr) {
            this.a = jWebPermissionRequest;
            this.b = strArr;
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onDenied() {
            this.a.deny();
        }

        @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
        public void onGranted() {
            this.a.grant(this.b);
        }
    }

    /* loaded from: classes15.dex */
    class e implements DialogInterface.OnClickListener {
        final /* synthetic */ GeolocationPermissions.Callback a;
        final /* synthetic */ String b;

        e(BaseWebChromeClient baseWebChromeClient, GeolocationPermissions.Callback callback, String str) {
            this.a = callback;
            this.b = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                dialogInterface.dismiss();
                this.a.invoke(this.b, true, true);
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes15.dex */
    class f implements DialogInterface.OnClickListener {
        final /* synthetic */ GeolocationPermissions.Callback a;
        final /* synthetic */ String b;

        f(BaseWebChromeClient baseWebChromeClient, GeolocationPermissions.Callback callback, String str) {
            this.a = callback;
            this.b = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                dialogInterface.dismiss();
                this.a.invoke(this.b, false, false);
            } catch (Exception unused) {
            }
        }
    }

    public BaseWebChromeClient(MantoActivityResult mantoActivityResult) {
        this.resultWeakReference = new WeakReference<>(mantoActivityResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeDialog() {
        try {
            AlertDialog alertDialog = this.dialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
                this.dialog = null;
            }
        } catch (Exception unused) {
        }
    }

    private void configFullScreenVideoView() {
        if (QbSdk.getTbsVersion(com.jingdong.a.g()) >= 45600 || !QbSdk.canLoadX5(com.jingdong.a.g()) || QbSdk.getIsSysWebViewForcedByOuter()) {
            try {
                Activity activity = getActivity();
                if (activity == null) {
                    return;
                }
                onScreenSwitch(activity, true);
                setStatusBarVisibility(activity, false);
                FrameLayout frameLayout = new FrameLayout(activity);
                this.mFullContainer = frameLayout;
                frameLayout.setBackgroundColor(-16777216);
                ViewGroup viewGroup = this.mFullContainer;
                View view = this.mCustomView;
                FrameLayout.LayoutParams layoutParams = COVER_SCREEN_PARAMS;
                viewGroup.addView(view, layoutParams);
                ((ViewGroup) activity.findViewById(R.id.manto_ui_root)).addView(this.mFullContainer, layoutParams);
                setDisplayCutoutVisibility(activity, true);
            } catch (Exception e2) {
                MantoLog.e(e2.getMessage(), new Object[0]);
            }
        }
    }

    private Activity getActivity() {
        Activity activity;
        WeakReference<MantoActivityResult> weakReference = this.resultWeakReference;
        if (weakReference == null || weakReference.get() == null || (activity = this.resultWeakReference.get().getActivity()) == null || activity.isFinishing()) {
            return null;
        }
        return activity;
    }

    private void gotoFileChooser(String str, boolean z, int i2) {
        if (TextUtils.isEmpty(str)) {
            str = "image/*;video/*;audio/*";
        }
        fileChoose(this.resultWeakReference.get().getActivity(), str);
    }

    private boolean isMediaAccepted(String str, String str2) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(";|,")) == null || split.length <= 0) {
            return false;
        }
        for (String str3 : split) {
            if (!str3.contains(str2)) {
                return false;
            }
        }
        return true;
    }

    private void onScreenSwitch(Activity activity, boolean z) {
        activity.setRequestedOrientation(z ? 0 : 1);
    }

    private void setDisplayCutoutVisibility(Activity activity, boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = z ? 1 : 0;
            activity.getWindow().setAttributes(attributes);
        }
    }

    private void setStatusBarVisibility(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (z) {
            window.clearFlags(1024);
        } else {
            window.setFlags(1024, 1024);
        }
    }

    private void show(View view, JWebChromeClient.CustomViewCallback customViewCallback) {
        JWebChromeClient.CustomViewCallback customViewCallback2;
        if (this.mCustomView != null && (customViewCallback2 = this.mCustomViewCallback) != null) {
            customViewCallback2.onCustomViewHidden();
        } else if (view == null || customViewCallback == null) {
        } else {
            this.mCustomView = view;
            this.mCustomViewCallback = customViewCallback;
            configFullScreenVideoView();
        }
    }

    private void showProgressDialog(int i2, Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        AlertDialog create = new AlertDialog.Builder(activity).create();
        this.dialog = create;
        create.setMessage(activity.getString(i2));
        this.dialog.setOnCancelListener(new c(this));
        this.dialog.show();
        try {
            View inflate = LayoutInflater.from(activity).inflate(R.layout.manto_toast, (ViewGroup) null);
            inflate.findViewById(R.id.ll_loading).setVisibility(0);
            ((TextView) inflate.findViewById(R.id.toast_loading_title)).setText(activity.getString(i2));
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.dialog.getWindow().setContentView(inflate);
        } catch (Exception unused) {
        }
    }

    private final void toChooseFile(MantoActivityResult mantoActivityResult, ValueCallback<Uri[]> valueCallback, JWebChromeClient.FileChooserParams fileChooserParams) {
        uploadFileWithActivity(mantoActivityResult, valueCallback, fileChooserParams);
        mantoActivityResult.setResultCallback(new a());
    }

    private void uploadFileNormal(ValueCallback<Uri[]> valueCallback, JWebChromeClient.FileChooserParams fileChooserParams) {
        this.mUploadMessageApi21 = valueCallback;
        gotoFileChooser((fileChooserParams != null || fileChooserParams.getAcceptTypes() == null) ? "" : fileChooserParams.getAcceptTypes()[0], true, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void uploadFileWithActivity(com.jingdong.manto.MantoActivityResult r11, android.webkit.ValueCallback<android.net.Uri[]> r12, com.jingdong.sdk.jweb.JWebChromeClient.FileChooserParams r13) {
        /*
            r10 = this;
            r10.mUploadMessageApi21 = r12
            r12 = 0
            r0 = 1
            java.lang.String r1 = ""
            if (r13 == 0) goto L52
            boolean r2 = r13.isCaptureEnabled()
            int r3 = r13.getMode()
            if (r3 != 0) goto L14
            r3 = 1
            goto L16
        L14:
            r3 = 9
        L16:
            java.lang.String[] r4 = r13.getAcceptTypes()
            if (r4 == 0) goto L54
            java.lang.String[] r4 = r13.getAcceptTypes()
            int r4 = r4.length
            if (r4 <= 0) goto L54
            r5 = r1
            r1 = 0
            r4 = 0
        L26:
            java.lang.String[] r6 = r13.getAcceptTypes()
            int r6 = r6.length
            if (r12 >= r6) goto L4f
            java.lang.String[] r5 = r13.getAcceptTypes()
            r5 = r5[r12]
            java.lang.String r6 = "image/"
            boolean r6 = r10.isMediaAccepted(r5, r6)
            if (r6 == 0) goto L3d
            r1 = 1
            goto L4c
        L3d:
            java.lang.String r6 = "video/"
            boolean r6 = r10.isMediaAccepted(r5, r6)
            if (r6 == 0) goto L47
            r4 = 1
            goto L4c
        L47:
            java.lang.String r6 = "audio/"
            r10.isMediaAccepted(r5, r6)
        L4c:
            int r12 = r12 + 1
            goto L26
        L4f:
            r12 = r1
            r1 = r5
            goto L55
        L52:
            r2 = 0
            r3 = 1
        L54:
            r4 = 0
        L55:
            java.lang.Class<com.jingdong.manto.sdk.api.AbsChooseMedia> r13 = com.jingdong.manto.sdk.api.AbsChooseMedia.class
            com.jingdong.manto.sdk.IMantoSdkBase r13 = com.jingdong.a.n(r13)
            com.jingdong.manto.sdk.api.AbsChooseMedia r13 = (com.jingdong.manto.sdk.api.AbsChooseMedia) r13
            android.content.Intent r5 = new android.content.Intent
            r5.<init>()
            java.lang.String r6 = "manto_show_camera"
            java.lang.String r7 = "manto_count"
            java.lang.String r8 = "manto_media_type"
            if (r12 == 0) goto L73
            r5.putExtra(r8, r0)
            r5.putExtra(r7, r3)
            r5.putExtra(r6, r2)
        L73:
            if (r4 == 0) goto L86
            r9 = 2
            r5.putExtra(r8, r9)
            r5.putExtra(r7, r0)
            r5.putExtra(r6, r2)
            r6 = 60
            java.lang.String r7 = "manto_video_time_max"
            r5.putExtra(r7, r6)
        L86:
            if (r13 == 0) goto Ld0
            r6 = 6444(0x192c, float:9.03E-42)
            if (r2 == 0) goto Lc8
            if (r4 == 0) goto L92
            r13.onRecordVideo(r11, r5, r6)
            goto Lc7
        L92:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "mantoMsg."
            r12.append(r0)
            long r0 = java.lang.System.currentTimeMillis()
            r12.append(r0)
            java.lang.String r0 = ".jpg"
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = com.jingdong.manto.utils.n.b
            r0.append(r1)
            java.lang.String r1 = "/photo/"
            r0.append(r1)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r10.captureFilePath = r12
            r13.onTakePhoto(r11, r12, r6)
        Lc7:
            return
        Lc8:
            if (r12 != 0) goto Lcc
            if (r4 == 0) goto Ld0
        Lcc:
            r13.onChooseMedia(r11, r5, r6)
            return
        Ld0:
            r10.gotoFileChooser(r1, r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.webview.BaseWebChromeClient.uploadFileWithActivity(com.jingdong.manto.MantoActivityResult, android.webkit.ValueCallback, com.jingdong.sdk.jweb.JWebChromeClient$FileChooserParams):void");
    }

    public void fileChoose(Activity activity, String str) {
        if (activity == null || !activity.isFinishing()) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT >= 21 ? 6444 : 10;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (TextUtils.isEmpty(str)) {
            str = "*/*";
        }
        intent.setType(str);
        try {
            activity.startActivityForResult(Intent.createChooser(intent, "\u9009\u62e9\u6587\u4ef6"), i2);
        } catch (Exception unused) {
        }
    }

    public void hide() {
        if (this.mCustomView != null) {
            try {
                Activity activity = getActivity();
                if (activity == null) {
                    return;
                }
                onScreenSwitch(activity, false);
                setStatusBarVisibility(activity, true);
                setDisplayCutoutVisibility(activity, false);
                ((ViewGroup) activity.findViewById(R.id.manto_ui_root)).removeView(this.mFullContainer);
                this.mCustomView = null;
            } catch (Exception e2) {
                MantoLog.e(e2.getMessage(), new Object[0]);
            }
        }
        JWebChromeClient.CustomViewCallback customViewCallback = this.mCustomViewCallback;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
        }
        this.mCustomViewCallback = null;
    }

    public boolean isFullScreen() {
        return this.mCustomView != null;
    }

    @Override // com.jingdong.sdk.jweb.JWebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        Activity activity;
        try {
            WeakReference<MantoActivityResult> weakReference = this.resultWeakReference;
            if (weakReference == null || weakReference.get() == null || (activity = this.resultWeakReference.get().getActivity()) == null || activity.isFinishing()) {
                return;
            }
            com.jingdong.manto.widget.dialog.a.a(activity, null, str + "\u5141\u8bb8\u83b7\u53d6\u60a8\u7684\u5730\u7406\u4f4d\u7f6e\u4fe1\u606f\u5417\uff1f", "\u786e\u5b9a", "\u53d6\u6d88", new e(this, callback, str), new f(this, callback, str), null, null, null).show();
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebChromeClient
    public void onHideCustomView() {
        super.onHideCustomView();
        hide();
    }

    @Override // com.jingdong.sdk.jweb.JWebChromeClient
    public void onPermissionRequest(JWebPermissionRequest jWebPermissionRequest) {
        WeakReference<MantoActivityResult> weakReference;
        try {
            String[] resources = jWebPermissionRequest.getResources();
            boolean z = false;
            for (String str : resources) {
                char c2 = '\uffff';
                if (str.hashCode() == -1660821873 && str.equals("android.webkit.resource.VIDEO_CAPTURE")) {
                    c2 = 0;
                }
                if (!MantoPermission.hasPermission("android.permission.CAMERA")) {
                    z = true;
                }
            }
            if (!z || (weakReference = this.resultWeakReference) == null || weakReference.get() == null) {
                jWebPermissionRequest.grant(resources);
            } else {
                MantoPermission.requestPermission(this.resultWeakReference.get().getActivity(), "android.permission.CAMERA", new d(this, jWebPermissionRequest, resources));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JWebChromeClient
    public void onShowCustomView(View view, JWebChromeClient.CustomViewCallback customViewCallback) {
        super.onShowCustomView(view, customViewCallback);
        show(view, customViewCallback);
    }

    @Override // com.jingdong.sdk.jweb.JWebChromeClient
    public boolean onShowFileChooser(JWebView jWebView, ValueCallback<Uri[]> valueCallback, JWebChromeClient.FileChooserParams fileChooserParams) {
        WeakReference<MantoActivityResult> weakReference = this.resultWeakReference;
        if (weakReference == null || weakReference.get() == null) {
            uploadFileNormal(valueCallback, fileChooserParams);
            return true;
        }
        toChooseFile(this.resultWeakReference.get(), valueCallback, fileChooserParams);
        return true;
    }

    public void selectFileBack(Intent intent, int i2, int i3, boolean z) {
        if (!z) {
            if (this.mUploadMessage == null) {
                return;
            }
            Uri data = (intent == null || i2 != i3) ? null : intent.getData();
            if (data != null && this.resultWeakReference.get() != null) {
                String c2 = h.c(this.resultWeakReference.get().getActivity(), data);
                if (!TextUtils.isEmpty(c2)) {
                    this.mUploadMessage.onReceiveValue(Uri.fromFile(new File(c2)));
                    this.mUploadMessage = null;
                }
            }
            this.mUploadMessage.onReceiveValue(null);
            this.mUploadMessage = null;
        } else if (this.mUploadMessageApi21 == null) {
        } else {
            ArrayList arrayList = new ArrayList();
            if (i2 == i3) {
                if (intent != null) {
                    ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_media_list");
                    if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
                        arrayList.add(intent.getData());
                    } else {
                        Iterator<String> it = stringArrayListExtra.iterator();
                        while (it.hasNext()) {
                            arrayList.add(Uri.parse(it.next()));
                        }
                    }
                }
                if (arrayList.isEmpty() && s.d(this.captureFilePath)) {
                    arrayList.add(Uri.parse(this.captureFilePath));
                }
            }
            if (arrayList.size() <= 0 || this.resultWeakReference.get() == null) {
                this.mUploadMessageApi21.onReceiveValue(new Uri[0]);
                this.mUploadMessageApi21 = null;
                return;
            }
            Activity activity = this.resultWeakReference.get().getActivity();
            showProgressDialog(R.string.manto_capturing, activity);
            com.jingdong.manto.b.d().diskIO().execute(new b(arrayList, activity));
        }
    }
}
