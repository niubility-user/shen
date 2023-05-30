package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDLVEntity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;

/* loaded from: classes15.dex */
public class PdLvSimpleProtocol extends PDLVProtocol {
    private PdLvProtocolListener mPdLvProtocol;

    /* loaded from: classes15.dex */
    public interface PdLvProtocolListener {
        void onEnd(PDLVEntity pDLVEntity);

        void parseError(HttpError httpError);
    }

    public PdLvSimpleProtocol(String str) {
        super(str);
    }

    public void addLvProtocolListener(PdLvProtocolListener pdLvProtocolListener) {
        this.mPdLvProtocol = pdLvProtocolListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDLVProtocol, com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    public void parseError(HttpError httpError) {
        PdLvProtocolListener pdLvProtocolListener = this.mPdLvProtocol;
        if (pdLvProtocolListener != null) {
            pdLvProtocolListener.parseError(httpError);
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDLVProtocol, com.jd.lib.productdetail.core.protocol.PdLVBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDLVEntity pDLVEntity = (PDLVEntity) JDJSON.parseObject(str, PDLVEntity.class);
        if (pDLVEntity != null) {
            if (TextUtils.isEmpty(pDLVEntity.comefrom)) {
                pDLVEntity.comefrom = getmComeFrom();
            }
            PdLvProtocolListener pdLvProtocolListener = this.mPdLvProtocol;
            if (pdLvProtocolListener != null) {
                pdLvProtocolListener.onEnd(pDLVEntity);
                return null;
            }
            return null;
        }
        return null;
    }

    public void removeLvProtocolListener() {
        this.mPdLvProtocol = null;
    }
}
