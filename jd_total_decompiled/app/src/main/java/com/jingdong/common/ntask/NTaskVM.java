package com.jingdong.common.ntask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public final class NTaskVM extends ViewModel {
    private WeakReference<BaseActivity> mReference;
    public MutableLiveData<NTaskEvent> taskLiveData;

    public NTaskVM(BaseActivity baseActivity) {
        if (baseActivity != null) {
            this.mReference = new WeakReference<>(baseActivity);
        }
        this.taskLiveData = new MutableLiveData<>();
    }

    public void dealError() {
        this.taskLiveData.postValue(new NTaskEvent(false, null));
    }

    public void dealSuccess(JDJSONObject jDJSONObject, String str, int i2) {
        this.taskLiveData.postValue(new NTaskEvent(true, NTaskModel.parse(jDJSONObject, str, i2)));
    }

    public void taskReq(JDJSONObject jDJSONObject) {
        WeakReference<BaseActivity> weakReference;
        if (jDJSONObject == null || (weakReference = this.mReference) == null || weakReference.get() == null) {
            return;
        }
        jDJSONObject.getString("contentId");
        jDJSONObject.getIntValue("contentType");
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("getContentShareTaskInfo");
        httpSetting.putJsonParam(jDJSONObject);
        httpSetting.setEffect(0);
        httpSetting.setPost(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003d: INVOKE 
              (r2v0 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
              (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener : 0x003a: CONSTRUCTOR 
              (r4v0 'this' com.jingdong.common.ntask.NTaskVM A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r1 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.ntask.NTaskVM, java.lang.String, int):void (m), WRAPPED] (LINE:11) call: com.jingdong.common.ntask.NTaskVM.1.<init>(com.jingdong.common.ntask.NTaskVM, java.lang.String, int):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:11) in method: com.jingdong.common.ntask.NTaskVM.taskReq(com.jd.framework.json.JDJSONObject):void, file: classes5.dex
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
            if (r5 == 0) goto L4f
            java.lang.ref.WeakReference<com.jingdong.common.BaseActivity> r0 = r4.mReference
            if (r0 == 0) goto L4f
            java.lang.Object r0 = r0.get()
            if (r0 != 0) goto Ld
            goto L4f
        Ld:
            java.lang.String r0 = "contentId"
            java.lang.String r0 = r5.getString(r0)
            java.lang.String r1 = "contentType"
            int r1 = r5.getIntValue(r1)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r2 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r2.<init>()
            java.lang.String r3 = "getContentShareTaskInfo"
            r2.setFunctionId(r3)
            r2.putJsonParam(r5)
            r5 = 0
            r2.setEffect(r5)
            r5 = 1
            r2.setPost(r5)
            java.lang.String r3 = com.jingdong.jdsdk.config.Configuration.getPortalHost()
            r2.setHost(r3)
            r2.setUseFastJsonParser(r5)
            com.jingdong.common.ntask.NTaskVM$1 r5 = new com.jingdong.common.ntask.NTaskVM$1
            r5.<init>()
            r2.setListener(r5)
            java.lang.ref.WeakReference<com.jingdong.common.BaseActivity> r5 = r4.mReference
            java.lang.Object r5 = r5.get()
            com.jingdong.common.BaseActivity r5 = (com.jingdong.common.BaseActivity) r5
            com.jingdong.jdsdk.network.toolbox.HttpGroup r5 = r5.getHttpGroupaAsynPool()
            r5.add(r2)
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.ntask.NTaskVM.taskReq(com.jd.framework.json.JDJSONObject):void");
    }
}
