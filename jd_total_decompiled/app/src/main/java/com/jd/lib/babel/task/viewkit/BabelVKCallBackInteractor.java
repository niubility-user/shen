package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.networkkit.BabelError;
import com.jd.lib.babel.servicekit.networkkit.BabelResponse;
import com.jd.lib.babel.task.common.FunctionIds;
import com.jd.lib.babel.task.servicekit.RequestInner;
import com.jd.viewkit.JDViewKit;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class BabelVKCallBackInteractor {
    private JDViewKitEventCallBack mCallBack;
    private Context mContext;
    private VKEventModelShell mEventModelShell;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public BabelVKCallBackInteractor(Context context, VKEventModelShell vKEventModelShell, JDViewKitEventCallBack jDViewKitEventCallBack) {
        this.mContext = context;
        this.mEventModelShell = vKEventModelShell;
        this.mCallBack = jDViewKitEventCallBack;
    }

    public void addRequest(Context context, RequestInner requestInner) {
        BabelServer.get().getNetWorkKitServer().addRequest(context, requestInner.request());
    }

    public void callBack() {
        if (this.mContext == null) {
            return;
        }
        RequestInner requestInner = new RequestInner();
        requestInner.setFunctionId(FunctionIds.qryViewkitCallbackResult);
        requestInner.setFunctionIdVersion(FunctionIds.qryViewkitCallbackResult4SDKVersion);
        requestInner.putJsonParam("vkVersion", JDViewKit.getVersion());
        VKEventModelShell vKEventModelShell = this.mEventModelShell;
        if (vKEventModelShell != null && vKEventModelShell.isValid()) {
            try {
                JSONObject jSONObject = new JSONObject(this.mEventModelShell.getJumpParams());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    requestInner.putJsonParam(next, jSONObject.opt(next));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        requestInner.setListener(new RequestInner.Listener() { // from class: com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.1
            {
                BabelVKCallBackInteractor.this = this;
            }

            @Override // com.jd.lib.babel.servicekit.networkkit.Request.Listener
            public void onEnd(BabelResponse babelResponse) {
                try {
                    if (BabelVKCallBackInteractor.this.mCallBack == null || babelResponse == null || BabelVKCallBackInteractor.this.mHandler == null) {
                        return;
                    }
                    babelResponse.getResultJson();
                    BabelVKCallBackInteractor.this.mHandler.post(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0021: INVOKE 
                          (wrap: android.os.Handler : 0x0018: IGET 
                          (wrap: com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor : 0x0016: IGET (r2v0 'this' com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor$1 A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0024, WRAPPED] (LINE:3) com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.1.this$0 com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor)
                         A[Catch: Exception -> 0x0024, MD:(com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor):android.os.Handler (m), WRAPPED] (LINE:1) com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.mHandler android.os.Handler)
                          (wrap: java.lang.Runnable : 0x001e: CONSTRUCTOR 
                          (r2v0 'this' com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor$1 A[IMMUTABLE_TYPE, THIS])
                          (r3 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[Catch: Exception -> 0x0024, MD:(com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor$1, java.lang.String):void (m), WRAPPED] call: com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.1.1.<init>(com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor$1, java.lang.String):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[Catch: Exception -> 0x0024, MD:(java.lang.Runnable):boolean (c), TRY_LEAVE] (LINE:1) in method: com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.1.onEnd(com.jd.lib.babel.servicekit.networkkit.BabelResponse):void, file: classes14.dex
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
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 25 more
                        */
                    /*
                        this = this;
                        com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor r0 = com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.this     // Catch: java.lang.Exception -> L24
                        com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack r0 = com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.access$000(r0)     // Catch: java.lang.Exception -> L24
                        if (r0 == 0) goto L24
                        if (r3 == 0) goto L24
                        com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor r0 = com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.this     // Catch: java.lang.Exception -> L24
                        android.os.Handler r0 = com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.access$100(r0)     // Catch: java.lang.Exception -> L24
                        if (r0 == 0) goto L24
                        java.lang.String r3 = r3.getResultJson()     // Catch: java.lang.Exception -> L24
                        com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor r0 = com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.this     // Catch: java.lang.Exception -> L24
                        android.os.Handler r0 = com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.access$100(r0)     // Catch: java.lang.Exception -> L24
                        com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor$1$1 r1 = new com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor$1$1     // Catch: java.lang.Exception -> L24
                        r1.<init>()     // Catch: java.lang.Exception -> L24
                        r0.post(r1)     // Catch: java.lang.Exception -> L24
                    L24:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.AnonymousClass1.onEnd(com.jd.lib.babel.servicekit.networkkit.BabelResponse):void");
                }

                @Override // com.jd.lib.babel.servicekit.networkkit.Request.Listener
                public void onError(BabelError babelError) {
                    if (BabelVKCallBackInteractor.this.mCallBack == null || BabelVKCallBackInteractor.this.mHandler == null) {
                        return;
                    }
                    BabelVKCallBackInteractor.this.mHandler.post(new Runnable() { // from class: com.jd.lib.babel.task.viewkit.BabelVKCallBackInteractor.1.2
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            BabelVKCallBackInteractor.this.mCallBack.failCallBack(0, null, null);
                        }
                    });
                }

                @Override // com.jd.lib.babel.servicekit.networkkit.Request.Listener
                public void onStart() {
                }
            });
            addRequest(this.mContext, requestInner);
        }
    }
