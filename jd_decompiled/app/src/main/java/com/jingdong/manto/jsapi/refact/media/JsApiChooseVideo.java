package com.jingdong.manto.jsapi.refact.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
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
import com.jingdong.manto.b;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.AbsChooseMedia;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.e;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.o;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiChooseVideo extends AbstractMantoModule {
    static final String CHOOSEVIDEO_NAME = "chooseVideo";
    static final String MODULE_NAME = "ChooseVideo";
    private static final int REQ_VIDEO_ALBUM = 4;
    private static final int REQ_VIDEO_CAMERA = 5;
    static final String TAG = "ChooseVideo";
    String appUniqueId;
    boolean compressed;
    private AlertDialog dialog;
    String fileName;

    /* JADX INFO: Access modifiers changed from: private */
    public void handleChooseVideo(String str, MantoActivityResult mantoActivityResult, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle2 = new Bundle();
        this.appUniqueId = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        this.compressed = bundle.getBoolean("compressed", true);
        int i2 = bundle.getInt("maxDuration", 60);
        int i3 = i2 <= 60 ? i2 : 60;
        boolean z = bundle.getBoolean("camera", false);
        boolean z2 = bundle.getBoolean("album", true);
        Intent intent = new Intent();
        intent.putExtra("query_media_type", 2);
        intent.putExtra("manto_video_time_max", i3);
        String str2 = o.d + "Manto." + System.currentTimeMillis() + PreDownLoadManager.VIDEO_SKU_SUFFIX;
        this.fileName = str2;
        intent.putExtra("manto_record_path", str2);
        intent.putExtra("manto_count", 1);
        intent.putExtra("manto_media_type", 2);
        if (z && z2) {
            intent.putExtra("manto_show_camera", true);
        } else if (z) {
            intent.putExtra("manto_which_camera", ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
            startRecord(mantoActivityResult, intent, 5);
            return;
        } else if (!z2) {
            bundle2.putString("message", "camera or album type error");
            mantoResultCallBack.onFailed(bundle2);
            return;
        } else {
            intent.putExtra("manto_show_camera", false);
        }
        startVideoPicker(mantoActivityResult, 4, intent);
    }

    private void handleFinalResult(Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        mantoResultCallBack.onSuccess(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFinalResult(e eVar, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        bundle.putString("tempFilePath", eVar.a);
        bundle.putInt("duration", Integer.valueOf(eVar.f14213h).intValue());
        bundle.putInt(ApkDownloadTable.FIELD_SIZE, Integer.valueOf(eVar.f14216k).intValue());
        bundle.putInt("height", Integer.valueOf(eVar.f14214i).intValue());
        bundle.putInt("width", Integer.valueOf(eVar.f14215j).intValue());
        mantoResultCallBack.onSuccess(bundle);
    }

    private void handleVideoBack(final Activity activity, Intent intent, final MantoResultCallBack mantoResultCallBack, final Bundle bundle) {
        if (activity != null) {
            if (!activity.isFinishing() || intent == null) {
                final ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_media_list");
                if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
                    bundle.putString("message", String.format("result is empty", new Object[0]));
                    mantoResultCallBack.onFailed(bundle);
                    return;
                }
                showProgressDialog(R.string.manto_capturing, activity);
                b.d().diskIO().execute(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.3
                    @Override // java.lang.Runnable
                    public void run() {
                        final e b = c.b(JsApiChooseVideo.this.appUniqueId, com.jingdong.manto.m.f1.c.a(com.jingdong.manto.c.a(), (String) stringArrayListExtra.get(0)), true);
                        if (b == null) {
                            bundle.putString("message", "video file handler error");
                            mantoResultCallBack.onFailed(bundle);
                            return;
                        }
                        new ArrayList(1).add(b);
                        Activity activity2 = activity;
                        if (activity2 == null || activity2.isFinishing()) {
                            return;
                        }
                        activity.runOnUiThread(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                JsApiChooseVideo.this.closeDialog();
                                AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                                JsApiChooseVideo.this.handleFinalResult(b, mantoResultCallBack);
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoObject(e eVar, String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            mediaMetadataRetriever.extractMetadata(24);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
            String extractMetadata3 = mediaMetadataRetriever.extractMetadata(9);
            eVar.f14215j = MantoUtils.getInt(extractMetadata, 0);
            eVar.f14214i = MantoUtils.getInt(extractMetadata2, 0);
            eVar.f14213h = MantoUtils.getInt(extractMetadata3, 0);
            eVar.f14216k = (int) new File(str).length();
            mediaMetadataRetriever.release();
        } catch (Exception unused) {
        }
    }

    private void showProgressDialog(int i2, Activity activity) {
        AlertDialog create = new AlertDialog.Builder(activity).create();
        this.dialog = create;
        create.setMessage(activity.getString(i2));
        this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.4
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
        return "ChooseVideo";
    }

    public List<String> getVideoList(Intent intent) {
        return intent.getStringArrayListExtra("select_media_list");
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(final String str, final MantoCore mantoCore, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
        new Bundle();
        MantoThreadUtils.runOnUIThread(new Runnable() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.1
            @Override // java.lang.Runnable
            public void run() {
                JsApiChooseVideo.this.handleChooseVideo(str, mantoCore.getActivityResultImpl(), bundle, mantoResultCallBack);
            }
        });
    }

    public Bundle handleResult(String str) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleResultWithCallback(String str, final MantoCore mantoCore, Intent intent, int i2, int i3, final MantoResultCallBack mantoResultCallBack) {
        String str2;
        MantoLog.d("ChooseVideo", "handleResult");
        Bundle bundle = new Bundle();
        if (i2 == 0) {
            str2 = "result canceled";
        } else {
            Activity activity = mantoCore.getActivity();
            if (i3 == 4) {
                List<String> videoList = getVideoList(intent);
                if (videoList == null || videoList.size() <= 0) {
                    return;
                }
                videoList.get(0);
                showProgressDialog(R.string.manto_capturing, activity);
                b.d().diskIO().execute(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004f: INVOKE 
                      (wrap: java.util.concurrent.Executor : 0x0046: INVOKE 
                      (wrap: com.jingdong.manto.pkg.AppExecutors : 0x0042: INVOKE  type: STATIC call: com.jingdong.manto.b.d():com.jingdong.manto.pkg.AppExecutors A[MD:():com.jingdong.manto.pkg.AppExecutors (m), WRAPPED])
                     type: VIRTUAL call: com.jingdong.manto.pkg.AppExecutors.diskIO():java.util.concurrent.Executor A[MD:():java.util.concurrent.Executor (m), WRAPPED])
                      (wrap: java.lang.Runnable : 0x004c: CONSTRUCTOR 
                      (r2v0 'this' com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo A[IMMUTABLE_TYPE, THIS])
                      (r3 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r8v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                      (r4v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                     A[MD:(com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo, java.lang.String, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack, com.jingdong.manto.MantoCore):void (m), WRAPPED] call: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.2.<init>(com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo, java.lang.String, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack, com.jingdong.manto.MantoCore):void type: CONSTRUCTOR)
                     type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] in method: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.handleResultWithCallback(java.lang.String, com.jingdong.manto.MantoCore, android.content.Intent, int, int, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes15.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                    java.lang.String r3 = "ChooseVideo"
                    java.lang.String r0 = "handleResult"
                    com.jingdong.manto.utils.MantoLog.d(r3, r0)
                    android.os.Bundle r3 = new android.os.Bundle
                    r3.<init>()
                    java.lang.String r0 = "message"
                    if (r6 != 0) goto L19
                    java.lang.String r4 = "result canceled"
                L12:
                    r3.putString(r0, r4)
                    r8.onFailed(r3)
                    return
                L19:
                    android.app.Activity r6 = r4.getActivity()
                    r1 = 4
                    if (r7 == r1) goto L2a
                    r4 = 5
                    if (r7 == r4) goto L26
                    java.lang.String r4 = "request code error"
                    goto L12
                L26:
                    r2.handleVideoBack(r6, r5, r8, r3)
                    goto L52
                L2a:
                    java.util.List r3 = r2.getVideoList(r5)
                    if (r3 == 0) goto L52
                    int r5 = r3.size()
                    if (r5 <= 0) goto L52
                    r5 = 0
                    java.lang.Object r3 = r3.get(r5)
                    java.lang.String r3 = (java.lang.String) r3
                    int r5 = com.jingdong.manto.R.string.manto_capturing
                    r2.showProgressDialog(r5, r6)
                    com.jingdong.manto.pkg.AppExecutors r5 = com.jingdong.manto.b.d()
                    java.util.concurrent.Executor r5 = r5.diskIO()
                    com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo$2 r6 = new com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo$2
                    r6.<init>()
                    r5.execute(r6)
                L52:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiChooseVideo.handleResultWithCallback(java.lang.String, com.jingdong.manto.MantoCore, android.content.Intent, int, int, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
            }

            @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
            public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
                boolean z;
                boolean z2;
                int i2;
                Bundle bundle = new Bundle();
                bundle.putString("json", jSONObject.toString());
                JSONArray optJSONArray = jSONObject.optJSONArray("sourceType");
                int optInt = jSONObject.optInt("maxDuration", 60);
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    z = true;
                    z2 = true;
                } else {
                    z2 = optJSONArray.toString().contains("camera");
                    z = optJSONArray.toString().contains("album");
                }
                if (!z) {
                    i2 = z2 ? 5 : 4;
                    bundle.putBoolean("camera", z2);
                    bundle.putBoolean("album", z);
                    bundle.putInt("maxDuration", optInt);
                    bundle.putBoolean("compressed", jSONObject.optBoolean("compressed", true));
                    bundle.putBoolean(IMantoBaseModule.HANDLERESULT_WITHCALLBACK, true);
                    return bundle;
                }
                bundle.putInt("requestCode", i2);
                bundle.putBoolean("camera", z2);
                bundle.putBoolean("album", z);
                bundle.putInt("maxDuration", optInt);
                bundle.putBoolean("compressed", jSONObject.optBoolean("compressed", true));
                bundle.putBoolean(IMantoBaseModule.HANDLERESULT_WITHCALLBACK, true);
                return bundle;
            }

            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
            protected void injectJsApiMethod(List<JsApiMethod> list) {
                list.add(new JsApiMethod(CHOOSEVIDEO_NAME, 2));
            }

            public void startRecord(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
                AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                if (absChooseMedia != null) {
                    absChooseMedia.onRecordVideo(mantoActivityResult, intent, i2);
                }
            }

            protected void startVideoPicker(MantoActivityResult mantoActivityResult, int i2, Intent intent) {
                AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                if (absChooseMedia != null) {
                    absChooseMedia.onChooseVideo(mantoActivityResult, intent, i2);
                }
            }
        }
