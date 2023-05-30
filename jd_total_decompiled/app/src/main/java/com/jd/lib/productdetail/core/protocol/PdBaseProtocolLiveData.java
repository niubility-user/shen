package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes15.dex */
public abstract class PdBaseProtocolLiveData<T> {
    protected static final String CODE_0 = "0";
    public static final String PRODUCT_DETAIL_CLASS_NAME = "com.jd.lib.productdetail.ProductDetailActivity";
    protected boolean mBodyEncrypt = true;
    public MutableLiveData<Result<T>> mResult = new MutableLiveData<>();

    /* loaded from: classes15.dex */
    public static class Result<T> {
        public T mData;
        public String mResponseString;
        public DataStatus mStatus;

        /* loaded from: classes15.dex */
        public enum DataStatus {
            NONE,
            FETCHING,
            SUCCESS,
            FAIL,
            DIRTY
        }

        public Result(DataStatus dataStatus, T t, String str) {
            this.mStatus = DataStatus.NONE;
            this.mStatus = dataStatus;
            this.mData = t;
            this.mResponseString = str;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void dealJsonData(HttpResponse httpResponse, ExceptionReporter exceptionReporter) {
        Class genericClass;
        String string = httpResponse.getString();
        if (!TextUtils.isEmpty(string) && (genericClass = getGenericClass()) != null) {
            Object parseObject = JDJSON.parseObject(string, genericClass);
            if (onAfterParseData(parseObject)) {
                this.mResult.postValue(new Result<>(Result.DataStatus.SUCCESS, parseObject, httpResponse.getString()));
                return;
            } else {
                this.mResult.postValue(new Result<>(Result.DataStatus.FAIL, null, null));
                return;
            }
        }
        exceptionReporter.reportHttpBusinessException(httpResponse);
        this.mResult.postValue(new Result<>(Result.DataStatus.FAIL, null, null));
    }

    protected long getCacheTime() {
        return 0L;
    }

    protected int getEffect() {
        return 1;
    }

    protected abstract String getFunctionId();

    public Class<T> getGenericClass() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            if (type instanceof ParameterizedType) {
                return (Class) ((ParameterizedType) type).getRawType();
            }
            return (Class) type;
        }
        return null;
    }

    protected String getHost() {
        return Configuration.getWareHost();
    }

    protected boolean isCache() {
        return false;
    }

    protected boolean isNotifyUser() {
        return false;
    }

    protected boolean isPost() {
        return false;
    }

    public boolean onAfterParseData(T t) {
        return true;
    }

    protected abstract void onBeforeRequest(@NonNull HttpSetting httpSetting);

    public void request(BaseActivity baseActivity) {
        new ExceptionReporter(null);
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR (r1v1 'onAllListener' com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener) = 
              (r6v0 'this' com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData<T> A[IMMUTABLE_TYPE, THIS])
              (r0 I:com.jingdong.jdsdk.network.toolbox.ExceptionReporter A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[DECLARE_VAR, MD:(com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void (m)] (LINE:2) call: com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData.1.<init>(com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void type: CONSTRUCTOR in method: com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData.request(com.jingdong.common.BaseActivity):void, file: classes15.dex
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter r0 = new com.jingdong.jdsdk.network.toolbox.ExceptionReporter
            r1 = 0
            r0.<init>(r1)
            com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData$1 r1 = new com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData$1
            r1.<init>()
            com.jingdong.jdsdk.network.toolbox.HttpSetting r0 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r0.<init>()
            r0.setListener(r1)
            java.lang.String r1 = "mode_product_detail"
            r0.setModeId(r1)
            java.lang.String r1 = "com.jd.lib.productdetail.ProductDetailActivity"
            java.lang.String r1 = com.jingdong.common.helper.PDHelper.getPageId(r1)
            r0.setPageId(r1)
            java.lang.String r1 = r6.getFunctionId()
            r0.setFunctionId(r1)
            java.lang.String r1 = r6.getHost()
            r0.setHost(r1)
            int r1 = r6.getEffect()
            r0.setEffect(r1)
            boolean r1 = r6.isNotifyUser()
            r0.setNotifyUser(r1)
            boolean r1 = r6.isCache()
            r0.setLocalFileCache(r1)
            long r1 = r6.getCacheTime()
            r0.setLocalFileCacheTime(r1)
            r1 = 1
            r0.setUseFastJsonParser(r1)
            r6.onBeforeRequest(r0)
            com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig r2 = com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig.getInstance()
            java.lang.String r3 = "JDProductdetail"
            java.lang.String r4 = "bodyEncrypt"
            java.lang.String r5 = "pdBodyEncrypt"
            java.lang.String r2 = r2.getConfig(r3, r4, r5)
            java.lang.String r3 = "true"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L70
            boolean r2 = r6.mBodyEncrypt
            if (r2 == 0) goto L70
            r0.setEncryptBody(r1)
            goto L74
        L70:
            r1 = 0
            r0.setEncryptBody(r1)
        L74:
            if (r7 == 0) goto L79
            r7.addHttpGroupWithNPSSetting(r0)
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData.request(com.jingdong.common.BaseActivity):void");
    }
}
