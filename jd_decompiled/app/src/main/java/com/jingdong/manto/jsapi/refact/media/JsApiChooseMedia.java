package com.jingdong.manto.jsapi.refact.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingdong.a;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity;
import com.jingdong.manto.jsapi.refact.rec.VideoParam;
import com.jingdong.manto.m.f1.b;
import com.jingdong.manto.sdk.api.AbsChooseMedia;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.t.e;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.n;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiChooseMedia extends AbstractMantoModule {
    static final String CHOOSE_MEDIA = "chooseMedia";
    static final int CHOOS_INDEX = 29;
    static final String MODULE_NAME = "m_chooseMedia";
    private static final int REQ_ALBUM = 4;
    private static final int REQ_PHOTO_CAMERA = 5;
    private static final int REQ_VIDEO_CAMERA = 6;
    static final String TAG = "chooseMedia";
    String appUniqueId;
    int chooseMediaType = 1;
    boolean compressed;
    private AlertDialog dialog;
    String imageFileName;
    String videoFileName;

    /* JADX INFO: Access modifiers changed from: private */
    public String checkAndCompressImage(Context context, String str) {
        return Build.VERSION.SDK_INT < 24 ? b.a(context, str) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleChooseMedia(String str, final MantoActivityResult mantoActivityResult, Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
        final Bundle bundle2 = new Bundle();
        this.appUniqueId = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        this.compressed = bundle.getBoolean("compressed", true);
        int i2 = bundle.getInt("maxDuration", 60);
        boolean z = bundle.getBoolean("camera", false);
        boolean z2 = bundle.getBoolean("album", true);
        int i3 = bundle.getInt("mediaType", 1);
        this.chooseMediaType = i3;
        final String string = bundle.getString("whichCamera", ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
        Intent intent = new Intent();
        intent.putExtra("manto_media_type", i3);
        intent.putExtra("manto_video_time_max", i2);
        intent.putExtra("manto_count", bundle.getInt("count"));
        intent.putExtra("manto_which_camera", string);
        if (z2) {
            intent.putExtra("manto_show_camera", z);
            intent.putExtra("manto_record_path", this.videoFileName);
            startMediaPicker(mantoActivityResult, 4, intent);
        } else if (!z || i3 != 1) {
            if (!z || i3 != 2) {
                bundle2.putString("message", "camera or album type error");
                mantoResultCallBack.onFailed(bundle2);
                return;
            }
            String str2 = n.b + "/tmp_manto." + System.currentTimeMillis() + PreDownLoadManager.VIDEO_SKU_SUFFIX;
            this.videoFileName = str2;
            intent.putExtra("manto_record_path", str2);
            startRecord(mantoActivityResult, intent, 6);
        } else {
            final String str3 = "mantoMsg." + System.currentTimeMillis() + ".jpg";
            this.imageFileName = n.b + "/photo/" + str3;
            if (!MantoPermission.hasPermission("android.permission.CAMERA")) {
                MantoPermission.requestPermission(mantoActivityResult.getActivity(), "android.permission.CAMERA", new IPermission.PermissionCallBack() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.2
                    @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                    public void onDenied() {
                        bundle2.putString("message", "camera permission denied");
                        mantoResultCallBack.onFailed(bundle2);
                    }

                    @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                    public void onGranted() {
                        JsApiChooseMedia.this.startCapture(mantoActivityResult, string, n.b + "/photo/" + str3, 5);
                    }
                });
                return;
            }
            startCapture(mantoActivityResult, string, n.b + "/photo/" + str3, 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFinalResult(ArrayList arrayList, String str, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        if (arrayList == null || arrayList.size() == 0) {
            bundle.putString("message", "result list is null");
            mantoResultCallBack.onFailed(bundle);
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                if (dVar != null && !MantoStringUtils.isEmpty(dVar.a)) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("tempFilePath", dVar.a);
                    jSONObject.put(ApkDownloadTable.FIELD_SIZE, Long.valueOf(dVar.f14211f));
                    if ("video".equalsIgnoreCase(str) && (dVar instanceof e)) {
                        setVideoObject((e) dVar, jSONObject);
                    }
                    jSONArray.put(jSONObject);
                }
            }
            bundle.putString("type", str);
            bundle.putString("tempFiles", jSONArray.toString());
            mantoResultCallBack.onSuccess(bundle);
        } catch (JSONException unused) {
            mantoResultCallBack.onFailed(null);
        }
    }

    private void handleFromAlbum(final Activity activity, Intent intent, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
        String format;
        if (activity != null) {
            if (!activity.isFinishing() || intent == null) {
                final ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_media_list");
                int i2 = this.chooseMediaType;
                if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
                    format = String.format("result is empty", new Object[0]);
                } else if (2 == i2) {
                    showProgressDialog(R.string.manto_capturing, activity);
                    com.jingdong.manto.b.d().diskIO().execute(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.3
                        @Override // java.lang.Runnable
                        public void run() {
                            e b = c.b(JsApiChooseMedia.this.appUniqueId, com.jingdong.manto.m.f1.c.a(com.jingdong.manto.c.a(), (String) stringArrayListExtra.get(0)), true);
                            if (b == null) {
                                bundle.putString("message", "video file handler error");
                                mantoResultCallBack.onFailed(bundle);
                                return;
                            }
                            final ArrayList arrayList = new ArrayList(1);
                            arrayList.add(b);
                            Activity activity2 = activity;
                            if (activity2 == null || activity2.isFinishing()) {
                                return;
                            }
                            activity.runOnUiThread(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    JsApiChooseMedia.this.closeDialog();
                                    AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                    JsApiChooseMedia.this.handleFinalResult(arrayList, "video", mantoResultCallBack);
                                }
                            });
                        }
                    });
                    return;
                } else if (1 == i2) {
                    MantoLog.d("chooseMedia", "compress:" + this.compressed);
                    showProgressDialog(this.compressed ? R.string.manto_compressing : R.string.manto_capturing, activity);
                    new ArrayList(stringArrayListExtra.size());
                    com.jingdong.manto.b.d().diskIO().execute(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x007e: INVOKE 
                          (wrap: java.util.concurrent.Executor : 0x0071: INVOKE 
                          (wrap: com.jingdong.manto.pkg.AppExecutors : 0x006d: INVOKE  type: STATIC call: com.jingdong.manto.b.d():com.jingdong.manto.pkg.AppExecutors A[MD:():com.jingdong.manto.pkg.AppExecutors (m), WRAPPED])
                         type: VIRTUAL call: com.jingdong.manto.pkg.AppExecutors.diskIO():java.util.concurrent.Executor A[MD:():java.util.concurrent.Executor (m), WRAPPED])
                          (wrap: java.lang.Runnable : 0x007b: CONSTRUCTOR 
                          (r7v0 'this' com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia A[IMMUTABLE_TYPE, THIS])
                          (r3v0 'stringArrayListExtra' java.util.ArrayList<java.lang.String> A[DONT_INLINE])
                          (r8v0 'activity' android.app.Activity A[DONT_INLINE])
                          (r5 I:java.util.ArrayList A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r11v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                         A[MD:(com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia, java.util.List, android.app.Activity, java.util.ArrayList, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] call: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.4.<init>(com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia, java.util.List, android.app.Activity, java.util.ArrayList, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                         type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] in method: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.handleFromAlbum(android.app.Activity, android.content.Intent, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes15.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:156)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:133)
                        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:156)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:133)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        Caused by: java.lang.NullPointerException
                        */
                    /*
                        this = this;
                        if (r8 == 0) goto L9f
                        boolean r0 = r8.isFinishing()
                        if (r0 == 0) goto Lc
                        if (r9 == 0) goto Lc
                        goto L9f
                    Lc:
                        java.lang.String r0 = "select_media_list"
                        java.util.ArrayList r3 = r9.getStringArrayListExtra(r0)
                        int r9 = r7.chooseMediaType
                        java.lang.String r0 = "message"
                        r1 = 0
                        if (r3 == 0) goto L91
                        int r2 = r3.size()
                        if (r2 <= 0) goto L91
                        r2 = 2
                        if (r2 != r9) goto L3d
                        int r9 = com.jingdong.manto.R.string.manto_capturing
                        r7.showProgressDialog(r9, r8)
                        com.jingdong.manto.pkg.AppExecutors r9 = com.jingdong.manto.b.d()
                        java.util.concurrent.Executor r9 = r9.diskIO()
                        com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia$3 r0 = new com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia$3
                        r1 = r0
                        r2 = r7
                        r4 = r8
                        r5 = r11
                        r6 = r10
                        r1.<init>()
                        r9.execute(r0)
                        goto L9f
                    L3d:
                        r2 = 1
                        if (r2 != r9) goto L82
                        java.lang.StringBuilder r9 = new java.lang.StringBuilder
                        r9.<init>()
                        java.lang.String r10 = "compress:"
                        r9.append(r10)
                        boolean r10 = r7.compressed
                        r9.append(r10)
                        java.lang.String r9 = r9.toString()
                        java.lang.String r10 = "chooseMedia"
                        com.jingdong.manto.utils.MantoLog.d(r10, r9)
                        boolean r9 = r7.compressed
                        if (r9 == 0) goto L5f
                        int r9 = com.jingdong.manto.R.string.manto_compressing
                        goto L61
                    L5f:
                        int r9 = com.jingdong.manto.R.string.manto_capturing
                    L61:
                        r7.showProgressDialog(r9, r8)
                        java.util.ArrayList r5 = new java.util.ArrayList
                        int r9 = r3.size()
                        r5.<init>(r9)
                        com.jingdong.manto.pkg.AppExecutors r9 = com.jingdong.manto.b.d()
                        java.util.concurrent.Executor r9 = r9.diskIO()
                        com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia$4 r10 = new com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia$4
                        r1 = r10
                        r2 = r7
                        r4 = r8
                        r6 = r11
                        r1.<init>()
                        r9.execute(r10)
                        goto L9f
                    L82:
                        java.lang.Object[] r8 = new java.lang.Object[r2]
                        java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                        r8[r1] = r9
                        java.lang.String r9 = "mediaType:%s is missed or wrong"
                        java.lang.String r8 = java.lang.String.format(r9, r8)
                        goto L99
                    L91:
                        java.lang.Object[] r8 = new java.lang.Object[r1]
                        java.lang.String r9 = "result is empty"
                        java.lang.String r8 = java.lang.String.format(r9, r8)
                    L99:
                        r10.putString(r0, r8)
                        r11.onFailed(r10)
                    L9f:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.handleFromAlbum(android.app.Activity, android.content.Intent, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
                }

                private void handleImage(final Activity activity, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
                    if (activity == null || activity.isFinishing()) {
                        return;
                    }
                    if (TextUtils.isEmpty(this.imageFileName) || !new File(this.imageFileName).exists()) {
                        bundle.putString("message", "result empty");
                        mantoResultCallBack.onFailed(bundle);
                        return;
                    }
                    showProgressDialog(R.string.manto_capturing, activity);
                    com.jingdong.manto.b.d().diskIO().execute(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.5
                        @Override // java.lang.Runnable
                        public void run() {
                            String str = JsApiChooseMedia.this.imageFileName;
                            if (b.a(str) != 0) {
                                str = b.c(activity, str);
                            }
                            JsApiChooseMedia jsApiChooseMedia = JsApiChooseMedia.this;
                            d a = c.a(JsApiChooseMedia.this.appUniqueId, jsApiChooseMedia.compressed ? b.a(activity, str) : jsApiChooseMedia.checkAndCompressImage(activity, str), true);
                            if (a == null) {
                                bundle.putString("message", "capture handler error");
                                mantoResultCallBack.onFailed(bundle);
                                return;
                            }
                            final ArrayList arrayList = new ArrayList(1);
                            arrayList.add(a);
                            Activity activity2 = activity;
                            if (activity2 != null) {
                                activity2.runOnUiThread(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.5.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JsApiChooseMedia.this.closeDialog();
                                        AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                                        JsApiChooseMedia.this.handleFinalResult(arrayList, "image", mantoResultCallBack);
                                    }
                                });
                            }
                        }
                    });
                }

                private void setVideoObject(e eVar, JSONObject jSONObject) {
                    try {
                        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                        mediaMetadataRetriever.setDataSource(eVar.b);
                        mediaMetadataRetriever.extractMetadata(24);
                        String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
                        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                        String extractMetadata3 = mediaMetadataRetriever.extractMetadata(9);
                        eVar.f14215j = MantoUtils.getInt(extractMetadata, 0);
                        eVar.f14214i = MantoUtils.getInt(extractMetadata2, 0);
                        eVar.f14213h = MantoUtils.getInt(extractMetadata3, 0);
                        mediaMetadataRetriever.release();
                        jSONObject.put("duration", MantoUtils.getInt(extractMetadata3, 0));
                        jSONObject.put("width", MantoUtils.getInt(extractMetadata, 0));
                        jSONObject.put("height", MantoUtils.getInt(extractMetadata2, 0));
                        String a = com.jingdong.manto.m.f1.c.a(eVar.b);
                        if (TextUtils.isEmpty(a)) {
                            return;
                        }
                        jSONObject.put("thumbTempFilePath", c.a(this.appUniqueId, a, true).a);
                    } catch (Exception unused) {
                    }
                }

                private void showProgressDialog(int i2, Activity activity) {
                    AlertDialog create = new AlertDialog.Builder(activity).create();
                    this.dialog = create;
                    create.setMessage(activity.getString(i2));
                    this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.6
                        @Override // android.content.DialogInterface.OnCancelListener
                        public void onCancel(DialogInterface dialogInterface) {
                        }
                    });
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

                public final void closeDialog() {
                    AlertDialog alertDialog = this.dialog;
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                        this.dialog = null;
                    }
                }

                @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                public String getModuleName() {
                    return MODULE_NAME;
                }

                @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                public void handleMethod(final String str, final MantoCore mantoCore, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
                    new Bundle();
                    MantoThreadUtils.runOnUIThread(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JsApiChooseMedia.this.handleChooseMedia(str, mantoCore.getActivityResultImpl(), bundle, mantoResultCallBack);
                        }
                    });
                }

                @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                public void handleResultWithCallback(String str, MantoCore mantoCore, Intent intent, int i2, int i3, MantoResultCallBack mantoResultCallBack) {
                    String str2;
                    MantoLog.d("chooseMedia", "handleResult");
                    Bundle bundle = new Bundle();
                    if (i2 != 0) {
                        Activity activity = mantoCore.getActivity();
                        if (i3 != 4) {
                            if (i3 == 5) {
                                handleImage(activity, bundle, mantoResultCallBack);
                                return;
                            }
                            str2 = i3 != 6 ? "request code error" : "result canceled";
                        }
                        handleFromAlbum(activity, intent, bundle, mantoResultCallBack);
                        return;
                    }
                    bundle.putString("message", str2);
                    mantoResultCallBack.onFailed(bundle);
                }

                /* JADX WARN: Removed duplicated region for block: B:38:0x00b8  */
                @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public android.os.Bundle initData(java.lang.String r18, com.jingdong.manto.MantoCore r19, org.json.JSONObject r20) {
                    /*
                        r17 = this;
                        r0 = r20
                        android.os.Bundle r1 = new android.os.Bundle
                        r1.<init>()
                        java.lang.String r2 = r20.toString()
                        java.lang.String r3 = "json"
                        r1.putString(r3, r2)
                        java.lang.String r2 = "sourceType"
                        org.json.JSONArray r2 = r0.optJSONArray(r2)
                        java.lang.String r3 = "mediaType"
                        org.json.JSONArray r4 = r0.optJSONArray(r3)
                        java.lang.String r5 = "sizeType"
                        org.json.JSONArray r5 = r0.optJSONArray(r5)
                        java.lang.String r6 = "maxDuration"
                        r7 = 10
                        int r7 = r0.optInt(r6, r7)
                        java.lang.String r8 = "camera"
                        java.lang.String r9 = "back"
                        java.lang.String r9 = r0.optString(r8, r9)
                        java.lang.String r10 = "count"
                        r11 = 9
                        int r0 = r0.optInt(r10, r11)
                        int r0 = java.lang.Math.min(r0, r11)
                        java.lang.String r11 = "album"
                        if (r2 == 0) goto L5a
                        int r13 = r2.length()
                        if (r13 != 0) goto L49
                        goto L5a
                    L49:
                        java.lang.String r13 = r2.toString()
                        boolean r13 = r13.contains(r8)
                        java.lang.String r2 = r2.toString()
                        boolean r2 = r2.contains(r11)
                        goto L5c
                    L5a:
                        r2 = 1
                        r13 = 1
                    L5c:
                        java.lang.String r14 = "compressed"
                        if (r5 == 0) goto L70
                        int r15 = r5.length()
                        if (r15 != 0) goto L67
                        goto L70
                    L67:
                        java.lang.String r5 = r5.toString()
                        boolean r5 = r5.contains(r14)
                        goto L71
                    L70:
                        r5 = 1
                    L71:
                        if (r4 == 0) goto L88
                        int r16 = r4.length()
                        if (r16 != 0) goto L7a
                        goto L88
                    L7a:
                        java.lang.String r4 = r4.toString()
                        java.lang.String r15 = "video"
                        boolean r4 = r4.contains(r15)
                        if (r4 == 0) goto L88
                        r4 = 2
                        goto L89
                    L88:
                        r4 = 1
                    L89:
                        java.lang.String r15 = "requestCode"
                        if (r2 == 0) goto L92
                        r12 = 4
                    L8e:
                        r1.putInt(r15, r12)
                        goto La0
                    L92:
                        if (r13 == 0) goto L99
                        r12 = 1
                        if (r4 != r12) goto L99
                        r12 = 5
                        goto L8e
                    L99:
                        if (r13 == 0) goto La0
                        r12 = 2
                        if (r4 != r12) goto La0
                        r12 = 6
                        goto L8e
                    La0:
                        r1.putInt(r10, r0)
                        java.lang.String r0 = "whichCamera"
                        r1.putString(r0, r9)
                        r1.putInt(r3, r4)
                        r1.putBoolean(r8, r13)
                        r1.putBoolean(r11, r2)
                        r1.putBoolean(r14, r5)
                        r0 = 30
                        if (r7 <= r0) goto Lba
                        r7 = 30
                    Lba:
                        r1.putInt(r6, r7)
                        java.lang.String r0 = "handleResultWithCallBack"
                        r2 = 1
                        r1.putBoolean(r0, r2)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiChooseMedia.initData(java.lang.String, com.jingdong.manto.MantoCore, org.json.JSONObject):android.os.Bundle");
                }

                @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
                protected void injectJsApiMethod(List<JsApiMethod> list) {
                    list.add(new JsApiMethod("chooseMedia", 29, 2));
                }

                public void startCapture(MantoActivityResult mantoActivityResult, String str, String str2, int i2) {
                    AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                    if (absChooseMedia != null) {
                        absChooseMedia.onTakePhoto(mantoActivityResult, str2, i2);
                    } else {
                        b.a(mantoActivityResult.getActivity(), str2, i2);
                    }
                }

                protected void startMediaPicker(MantoActivityResult mantoActivityResult, int i2, Intent intent) {
                    AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                    if (absChooseMedia != null) {
                        absChooseMedia.onChooseMedia(mantoActivityResult, intent, i2);
                    }
                }

                public void startRecord(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
                    AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                    if (absChooseMedia != null) {
                        absChooseMedia.onRecordVideo(mantoActivityResult, intent, i2);
                        return;
                    }
                    VideoParam videoParam = new VideoParam();
                    videoParam.camera = "front".equals(intent.getStringExtra("manto_which_camera")) ? 1 : 0;
                    videoParam.maxTime = intent.getIntExtra("manto_video_time_max", 60);
                    videoParam.recordFilePath = intent.getStringExtra("manto_record_path");
                    Intent intent2 = new Intent(mantoActivityResult.getActivity(), MantoVideoRecorderActivity.class);
                    intent2.putExtra(MantoVideoRecorderActivity.VIDEO_PARAM, videoParam);
                    mantoActivityResult.getActivity().startActivityForResult(intent2, i2);
                }
            }
