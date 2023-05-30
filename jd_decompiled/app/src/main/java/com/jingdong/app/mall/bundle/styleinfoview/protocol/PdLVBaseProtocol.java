package com.jingdong.app.mall.bundle.styleinfoview.protocol;

import com.jingdong.app.mall.bundle.styleinfoview.utils.PDManager;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class PdLVBaseProtocol {
    protected static final String CODE_0 = "0";
    protected String mEventKey;
    protected Object mInputParams;

    public PdLVBaseProtocol(String str) {
        this.mEventKey = str;
    }

    protected long getCacheTime() {
        return 0L;
    }

    protected int getEffect() {
        return 1;
    }

    protected EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    protected abstract String getFunctionId();

    protected String getHost() {
        return Configuration.getWareHost();
    }

    protected abstract Object getRequestParam(Object obj);

    protected boolean isCache() {
        return false;
    }

    protected boolean isNotifyUser() {
        return false;
    }

    protected boolean isPost() {
        return false;
    }

    protected void parseError(HttpError httpError) {
    }

    protected abstract Object parseResponse(String str, ExceptionReporter exceptionReporter);

    public HttpSetting request() {
        new JSONObject();
        new ExceptionReporter(null);
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: CONSTRUCTOR (r1v1 'onAllListener' com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener) = 
              (r3v0 'this' com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol A[IMMUTABLE_TYPE, THIS])
              (r0 I:com.jingdong.jdsdk.network.toolbox.ExceptionReporter A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void (m)] (LINE:3) call: com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol.1.<init>(com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void type: CONSTRUCTOR in method: com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol.request():com.jingdong.jdsdk.network.toolbox.HttpSetting, file: classes3.dex
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
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter r0 = new com.jingdong.jdsdk.network.toolbox.ExceptionReporter
            r1 = 0
            r0.<init>(r1)
            com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol$1 r1 = new com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol$1
            r1.<init>()
            com.jingdong.jdsdk.network.toolbox.HttpSetting r0 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r0.<init>()
            r0.setListener(r1)
            java.lang.String r1 = "mode_product_detail"
            r0.setModeId(r1)
            java.lang.String r1 = "com.jd.lib.productdetail.ProductDetailActivity"
            java.lang.String r1 = com.jingdong.common.helper.PDHelper.getPageId(r1)
            r0.setPageId(r1)
            java.lang.String r1 = r3.getFunctionId()
            r0.setFunctionId(r1)
            java.lang.String r1 = r3.getHost()
            r0.setHost(r1)
            int r1 = r3.getEffect()
            r0.setEffect(r1)
            boolean r1 = r3.isNotifyUser()
            r0.setNotifyUser(r1)
            boolean r1 = r3.isCache()
            r0.setLocalFileCache(r1)
            long r1 = r3.getCacheTime()
            r0.setLocalFileCacheTime(r1)
            r1 = 1
            r0.setUseFastJsonParser(r1)
            java.lang.Object r1 = r3.mInputParams
            java.lang.Object r1 = r3.getRequestParam(r1)
            r0.putJsonParam(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBaseProtocol.request():com.jingdong.jdsdk.network.toolbox.HttpSetting");
    }

    public void setInputParams(Object obj) {
        this.mInputParams = obj;
    }
}
