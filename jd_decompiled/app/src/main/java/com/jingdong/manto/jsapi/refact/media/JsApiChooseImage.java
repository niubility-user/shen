package com.jingdong.manto.jsapi.refact.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingdong.a;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.f1.b;
import com.jingdong.manto.sdk.api.AbsChooseMedia;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiChooseImage extends AbstractMantoModule {
    static final String CHOOSEIMAGE_NAME = "chooseImage";
    static final String MODULE_NAME = "ChooseImage";
    private static final int REQ_ALBUM = 1;
    private static final int REQ_CAMERA = 2;
    static final String TAG = "ChooseImage";
    String appUniqueId;
    boolean compressed;
    private AlertDialog dialog;
    String imageFileName;
    boolean original;

    /* JADX INFO: Access modifiers changed from: private */
    public static String checkAndCompressImage(Context context, String str) {
        return Build.VERSION.SDK_INT < 24 ? b.a(context, str) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFinalResult(ArrayList arrayList, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        if (arrayList == null || arrayList.size() == 0) {
            bundle.putString("message", "result list is null or nil");
            mantoResultCallBack.onFailed(bundle);
            return;
        }
        ArrayList<String> arrayList2 = new ArrayList<>(arrayList.size());
        ArrayList<String> arrayList3 = new ArrayList<>(arrayList.size());
        ArrayList arrayList4 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (dVar != null && !MantoStringUtils.isEmpty(dVar.a)) {
                arrayList2.add(dVar.a);
                arrayList3.add(Long.valueOf(dVar.f14211f));
                arrayList4.add(dVar.b);
            }
        }
        bundle.putStringArrayList("tempFilePaths", arrayList2);
        bundle.putStringArrayList("tempFileSizes", arrayList3);
        mantoResultCallBack.onSuccess(bundle);
    }

    public final void closeDialog() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.dialog = null;
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "ChooseImage";
    }

    public List<String> getPictureList(Intent intent) {
        return intent.getStringArrayListExtra("select_media_list");
    }

    public final void handleChooseImage(String str, final MantoCore mantoCore, Bundle bundle, final MantoResultCallBack mantoResultCallBack, boolean z, boolean z2, boolean z3) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        final Bundle bundle2 = new Bundle();
        this.appUniqueId = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        String optString = jSONObject.optString("sizeType");
        if (MantoStringUtils.isEmpty(optString)) {
            optString = "compressed";
        }
        this.compressed = optString.contains("compressed");
        this.original = optString.contains("original");
        int max = Math.max(1, Math.min(9, jSONObject.optInt("count")));
        boolean z4 = this.compressed;
        Intent intent = new Intent();
        intent.putExtra("manto_compressed", this.compressed);
        intent.putExtra("manto_media_type", 1);
        intent.putExtra("manto_count", max);
        intent.putExtra("manto_show_camera", false);
        intent.putExtra("manto_isClip", z3);
        if (z && z2) {
            intent.putExtra("manto_show_camera", true);
        } else if (!z2) {
            if (!z) {
                bundle2.putString("message", "camera or album type error");
                mantoResultCallBack.onFailed(bundle2);
                return;
            }
            final String str2 = "mantoMsg." + System.currentTimeMillis() + ".jpg";
            this.imageFileName = n.b + "/photo/" + str2;
            if (!MantoPermission.hasPermission("android.permission.CAMERA")) {
                MantoPermission.requestPermission(mantoCore.getActivity(), "android.permission.CAMERA", new IPermission.PermissionCallBack() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.2
                    @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                    public void onDenied() {
                        bundle2.putString("message", "camera permission denied");
                        mantoResultCallBack.onFailed(bundle2);
                    }

                    @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                    public void onGranted() {
                        JsApiChooseImage.this.takePhoto(mantoCore.getActivityResultImpl(), n.b + "/photo/" + str2, 2);
                    }
                });
                return;
            }
            takePhoto(mantoCore.getActivityResultImpl(), n.b + "/photo/" + str2, 2);
            return;
        }
        startGallery(mantoCore.getActivityResultImpl(), intent, 1);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(final String str, final MantoCore mantoCore, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
        final boolean z;
        final boolean z2;
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("json"));
            new Bundle();
            bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
            JSONArray optJSONArray = jSONObject.optJSONArray("sourceType");
            if (optJSONArray == null || optJSONArray.length() == 0) {
                z = true;
                z2 = true;
            } else {
                boolean contains = optJSONArray.toString().contains("camera");
                z2 = optJSONArray.toString().contains("album");
                z = contains;
            }
            jSONObject.optBoolean("isClip");
            MantoThreadUtils.runOnUIThread(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0051: INVOKE 
                  (wrap: java.lang.Runnable : 0x004e: CONSTRUCTOR 
                  (r11v0 'this' com.jingdong.manto.jsapi.refact.media.JsApiChooseImage A[IMMUTABLE_TYPE, THIS])
                  (r12v0 'str' java.lang.String A[DONT_INLINE])
                  (r13v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                  (r14v0 'bundle' android.os.Bundle A[DONT_INLINE])
                  (r15v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                  (r7v1 'z' boolean A[DONT_INLINE])
                  (r8v1 'z2' boolean A[DONT_INLINE])
                  (r9 I:boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jingdong.manto.jsapi.refact.media.JsApiChooseImage, java.lang.String, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack, boolean, boolean, boolean):void (m), WRAPPED] call: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.1.<init>(com.jingdong.manto.jsapi.refact.media.JsApiChooseImage, java.lang.String, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack, boolean, boolean, boolean):void type: CONSTRUCTOR)
                 type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] in method: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.handleMethod(java.lang.String, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes15.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                java.lang.String r1 = "json"
                java.lang.String r1 = r14.getString(r1)
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L55
                r2.<init>(r1)     // Catch: org.json.JSONException -> L55
                android.os.Bundle r1 = new android.os.Bundle
                r1.<init>()
                java.lang.String r1 = "appuniqueid"
                java.lang.String r3 = ""
                r14.getString(r1, r3)
                java.lang.String r1 = "sourceType"
                org.json.JSONArray r1 = r2.optJSONArray(r1)
                r3 = 1
                if (r1 == 0) goto L3e
                int r4 = r1.length()
                if (r4 != 0) goto L27
                goto L3e
            L27:
                java.lang.String r3 = r1.toString()
                java.lang.String r4 = "camera"
                boolean r3 = r3.contains(r4)
                java.lang.String r1 = r1.toString()
                java.lang.String r4 = "album"
                boolean r1 = r1.contains(r4)
                r8 = r1
                r7 = r3
                goto L40
            L3e:
                r7 = 1
                r8 = 1
            L40:
                java.lang.String r1 = "isClip"
                boolean r9 = r2.optBoolean(r1)
                com.jingdong.manto.jsapi.refact.media.JsApiChooseImage$1 r10 = new com.jingdong.manto.jsapi.refact.media.JsApiChooseImage$1
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r5 = r14
                r6 = r15
                r1.<init>()
                com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r10)
                return
            L55:
                r0 = move-exception
                r0.printStackTrace()
                r0 = 0
                r15.onFailed(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.handleMethod(java.lang.String, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
        }

        @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
        public final void handleResultWithCallback(String str, final MantoCore mantoCore, Intent intent, int i2, int i3, final MantoResultCallBack mantoResultCallBack) {
            Executor diskIO;
            Runnable runnable;
            boolean z;
            String str2;
            MantoLog.d("ChooseImage", "handleResult");
            Bundle bundle = new Bundle();
            if (i2 != 0) {
                final Activity activity = mantoCore.getActivity();
                boolean z2 = true;
                if (i3 == 1) {
                    final boolean z3 = this.compressed;
                    MantoLog.d("ChooseImage", "compre:" + this.compressed);
                    final List<String> pictureList = getPictureList(intent);
                    if (pictureList != null && pictureList.size() > 0) {
                        showProgressDialog(z3 ? R.string.manto_compressing : R.string.manto_capturing, activity);
                        new ArrayList(pictureList.size());
                        diskIO = com.jingdong.manto.b.d().diskIO();
                        runnable = new Runnable
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00d0: CONSTRUCTOR (r11v0 'runnable' java.lang.Runnable) = 
                              (r12v0 'this' com.jingdong.manto.jsapi.refact.media.JsApiChooseImage A[IMMUTABLE_TYPE, THIS])
                              (r9v2 'pictureList' java.util.List<java.lang.String> A[DONT_INLINE])
                              (r4v0 'activity' android.app.Activity A[DONT_INLINE])
                              (r6v1 'z3' boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                              (r5 I:java.util.ArrayList A[DONT_GENERATE, DONT_INLINE, REMOVE])
                              (r14v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                              (r18v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                             A[MD:(com.jingdong.manto.jsapi.refact.media.JsApiChooseImage, java.util.List, android.app.Activity, boolean, java.util.ArrayList, com.jingdong.manto.MantoCore, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m)] call: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.4.<init>(com.jingdong.manto.jsapi.refact.media.JsApiChooseImage, java.util.List, android.app.Activity, boolean, java.util.ArrayList, com.jingdong.manto.MantoCore, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR in method: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.handleResultWithCallback(java.lang.String, com.jingdong.manto.MantoCore, android.content.Intent, int, int, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes15.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
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
                            r8 = r12
                            r0 = r17
                            r7 = r18
                            java.lang.String r1 = "ChooseImage"
                            java.lang.String r2 = "handleResult"
                            com.jingdong.manto.utils.MantoLog.d(r1, r2)
                            android.os.Bundle r2 = new android.os.Bundle
                            r2.<init>()
                            java.lang.String r3 = "message"
                            if (r16 != 0) goto L1e
                            java.lang.String r0 = "result canceled"
                        L17:
                            r2.putString(r3, r0)
                        L1a:
                            r7.onFailed(r2)
                            return
                        L1e:
                            android.app.Activity r4 = r14.getActivity()
                            java.lang.String r5 = "result empty"
                            r6 = 1
                            if (r0 == r6) goto L86
                            r1 = 2
                            if (r0 == r1) goto L2d
                            java.lang.String r0 = "request code error"
                            goto L17
                        L2d:
                            if (r14 == 0) goto L85
                            boolean r0 = r14.isFinishing()
                            if (r0 == 0) goto L36
                            goto L85
                        L36:
                            java.lang.String r0 = r8.imageFileName
                            boolean r0 = android.text.TextUtils.isEmpty(r0)
                            if (r0 != 0) goto Ld6
                            java.io.File r0 = new java.io.File
                            java.lang.String r1 = r8.imageFileName
                            r0.<init>(r1)
                            boolean r0 = r0.exists()
                            if (r0 == 0) goto Ld6
                            java.lang.String r9 = r8.imageFileName
                            boolean r0 = r8.original
                            if (r0 != 0) goto L55
                            boolean r1 = r8.compressed
                            if (r1 != 0) goto L60
                        L55:
                            boolean r1 = r8.compressed
                            if (r1 == 0) goto L5e
                            if (r0 == 0) goto L5e
                            if (r1 == 0) goto L5e
                            goto L60
                        L5e:
                            r0 = 0
                            r6 = 0
                        L60:
                            boolean r0 = android.text.TextUtils.isEmpty(r9)
                            if (r0 != 0) goto L81
                            int r0 = com.jingdong.manto.R.string.manto_capturing
                            r12.showProgressDialog(r0, r4)
                            com.jingdong.manto.pkg.AppExecutors r0 = com.jingdong.manto.b.d()
                            java.util.concurrent.Executor r10 = r0.diskIO()
                            com.jingdong.manto.jsapi.refact.media.JsApiChooseImage$5 r11 = new com.jingdong.manto.jsapi.refact.media.JsApiChooseImage$5
                            r0 = r11
                            r1 = r12
                            r2 = r9
                            r3 = r4
                            r4 = r6
                            r5 = r14
                            r6 = r18
                            r0.<init>()
                            goto Ld3
                        L81:
                            r2.putString(r3, r5)
                            goto L1a
                        L85:
                            return
                        L86:
                            boolean r6 = r8.compressed
                            java.lang.StringBuilder r0 = new java.lang.StringBuilder
                            r0.<init>()
                            java.lang.String r9 = "compre:"
                            r0.append(r9)
                            boolean r9 = r8.compressed
                            r0.append(r9)
                            java.lang.String r0 = r0.toString()
                            com.jingdong.manto.utils.MantoLog.d(r1, r0)
                            r0 = r15
                            java.util.List r9 = r12.getPictureList(r15)
                            if (r9 == 0) goto L81
                            int r0 = r9.size()
                            if (r0 <= 0) goto L81
                            if (r6 == 0) goto Lb0
                            int r0 = com.jingdong.manto.R.string.manto_compressing
                            goto Lb2
                        Lb0:
                            int r0 = com.jingdong.manto.R.string.manto_capturing
                        Lb2:
                            r12.showProgressDialog(r0, r4)
                            java.util.ArrayList r5 = new java.util.ArrayList
                            int r0 = r9.size()
                            r5.<init>(r0)
                            com.jingdong.manto.pkg.AppExecutors r0 = com.jingdong.manto.b.d()
                            java.util.concurrent.Executor r10 = r0.diskIO()
                            com.jingdong.manto.jsapi.refact.media.JsApiChooseImage$4 r11 = new com.jingdong.manto.jsapi.refact.media.JsApiChooseImage$4
                            r0 = r11
                            r1 = r12
                            r2 = r9
                            r3 = r4
                            r4 = r6
                            r6 = r14
                            r7 = r18
                            r0.<init>()
                        Ld3:
                            r10.execute(r11)
                        Ld6:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.handleResultWithCallback(java.lang.String, com.jingdong.manto.MantoCore, android.content.Intent, int, int, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
                        boolean z;
                        boolean z2;
                        Bundle bundle = new Bundle();
                        bundle.putString("json", jSONObject.toString());
                        JSONArray optJSONArray = jSONObject.optJSONArray("sourceType");
                        if (optJSONArray == null || optJSONArray.length() == 0) {
                            z = true;
                            z2 = true;
                        } else {
                            z2 = optJSONArray.toString().contains("camera");
                            z = optJSONArray.toString().contains("album");
                        }
                        if (z) {
                            bundle.putInt("requestCode", 1);
                        } else if (z2) {
                            bundle.putInt("requestCode", 2);
                        }
                        bundle.putBoolean("isClip", jSONObject.optBoolean("isClip", false));
                        bundle.putBoolean(IMantoBaseModule.HANDLERESULT_WITHCALLBACK, true);
                        return bundle;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
                    protected final void injectJsApiMethod(List<JsApiMethod> list) {
                        list.add(new JsApiMethod(CHOOSEIMAGE_NAME, 2));
                    }

                    public void showProgressDialog(int i2, Activity activity) {
                        AlertDialog create = new AlertDialog.Builder(activity).create();
                        this.dialog = create;
                        create.setMessage(activity.getString(i2));
                        this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.manto.jsapi.refact.media.JsApiChooseImage.3
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

                    public void startGallery(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
                        AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                        if (absChooseMedia != null) {
                            absChooseMedia.onChooseImage(mantoActivityResult, intent, i2);
                        }
                    }

                    public void takePhoto(MantoActivityResult mantoActivityResult, String str, int i2) {
                        AbsChooseMedia absChooseMedia = (AbsChooseMedia) a.n(AbsChooseMedia.class);
                        if (absChooseMedia != null) {
                            absChooseMedia.onTakePhoto(mantoActivityResult, str, i2);
                        } else {
                            b.a(mantoActivityResult.getActivity(), str, i2);
                        }
                    }
                }
