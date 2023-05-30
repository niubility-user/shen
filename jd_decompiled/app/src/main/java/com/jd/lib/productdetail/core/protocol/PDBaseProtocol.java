package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class PDBaseProtocol {
    protected static final String CODE_0 = "0";
    protected String mEventKey;
    protected Object[] mInputParams;

    public PDBaseProtocol(String str) {
        this.mEventKey = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getCacheTime() {
        return 0L;
    }

    protected int getEffect() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    protected abstract String getFunctionId();

    protected String getHost() {
        return Configuration.getWareHost();
    }

    protected JDJSONObject getJDRequestParam(JDJSONObject jDJSONObject, Object[] objArr) {
        return null;
    }

    protected abstract JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isCache() {
        return false;
    }

    protected boolean isNeedRetryOnBusinessLayer() {
        return true;
    }

    protected boolean isNotifyUser() {
        return false;
    }

    protected boolean isPost() {
        return false;
    }

    protected boolean isUseJDJSONObject() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void parseError(HttpError httpError) {
    }

    protected abstract Object parseResponse(String str, ExceptionReporter exceptionReporter);

    public HttpSetting request() {
        JSONObject jSONObject = new JSONObject();
        new ExceptionReporter(null);
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: CONSTRUCTOR (r2v1 'onAllListener' com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener) = 
              (r4v0 'this' com.jd.lib.productdetail.core.protocol.PDBaseProtocol A[IMMUTABLE_TYPE, THIS])
              (r1 I:com.jingdong.jdsdk.network.toolbox.ExceptionReporter A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.jd.lib.productdetail.core.protocol.PDBaseProtocol, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void (m)] (LINE:3) call: com.jd.lib.productdetail.core.protocol.PDBaseProtocol.1.<init>(com.jd.lib.productdetail.core.protocol.PDBaseProtocol, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void type: CONSTRUCTOR in method: com.jd.lib.productdetail.core.protocol.PDBaseProtocol.request():com.jingdong.jdsdk.network.toolbox.HttpSetting, file: classes15.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter r1 = new com.jingdong.jdsdk.network.toolbox.ExceptionReporter
            r2 = 0
            r1.<init>(r2)
            com.jd.lib.productdetail.core.protocol.PDBaseProtocol$1 r2 = new com.jd.lib.productdetail.core.protocol.PDBaseProtocol$1
            r2.<init>()
            com.jingdong.jdsdk.network.toolbox.HttpSetting r1 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r1.<init>()
            r1.setListener(r2)
            java.lang.String r2 = "mode_product_detail"
            r1.setModeId(r2)
            java.lang.String r2 = "com.jd.lib.productdetail.ProductDetailActivity"
            java.lang.String r2 = com.jingdong.common.helper.PDHelper.getPageId(r2)
            r1.setPageId(r2)
            java.lang.String r2 = r4.getFunctionId()
            r1.setFunctionId(r2)
            java.lang.String r2 = r4.getHost()
            r1.setHost(r2)
            int r2 = r4.getEffect()
            r1.setEffect(r2)
            boolean r2 = r4.isNotifyUser()
            r1.setNotifyUser(r2)
            boolean r2 = r4.isCache()
            r1.setLocalFileCache(r2)
            long r2 = r4.getCacheTime()
            r1.setLocalFileCacheTime(r2)
            r2 = 1
            r1.setUseFastJsonParser(r2)
            boolean r2 = r4.isNeedRetryOnBusinessLayer()
            r1.setNeedRetryOnBusinessLayer(r2)
            boolean r2 = r4.isUseJDJSONObject()
            if (r2 == 0) goto L70
            com.jd.framework.json.JDJSONObject r0 = new com.jd.framework.json.JDJSONObject
            r0.<init>()
            java.lang.Object[] r2 = r4.mInputParams
            com.jd.framework.json.JDJSONObject r0 = r4.getJDRequestParam(r0, r2)
            r1.setJsonParams(r0)
            goto L79
        L70:
            java.lang.Object[] r2 = r4.mInputParams
            org.json.JSONObject r0 = r4.getRequestParam(r0, r2)
            r1.setJsonParams(r0)
        L79:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.core.protocol.PDBaseProtocol.request():com.jingdong.jdsdk.network.toolbox.HttpSetting");
    }

    public void setInputParams(Object... objArr) {
        this.mInputParams = objArr;
    }
}
