package com.jingdong.common.impl;

import com.jingdong.common.protocol.ParserModule;
import com.jingdong.common.protocol.UtilProtocol;
import com.jingdong.common.protocol.encrypt.IEncrypt;
import com.jingdong.common.protocol.eventbus.IEventBus;
import com.jingdong.common.protocol.http.IHttpSetting;
import com.jingdong.common.protocol.imageloader.ImageLoaderProtocol;
import com.jingdong.common.protocol.log.ILog;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.common.protocol.tools.IMta;
import com.jingdong.common.protocol.tools.IToast;

/* loaded from: classes5.dex */
public class UtilFactory implements UtilProtocol {
    private static volatile UtilFactory mInstance;
    private UtilProtocol mUtilProxy;

    private UtilFactory() {
    }

    public static UtilFactory getInstance() {
        if (mInstance == null) {
            synchronized (UtilFactory.class) {
                if (mInstance == null) {
                    mInstance = new UtilFactory();
                    mInstance.setProtocolProxy(new UtilProtocolProxy());
                }
            }
        }
        return mInstance;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IEncrypt getEncrypt() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getEncrypt();
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IEventBus getEventBus() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getEventBus();
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IHttpSetting getHttpSetting() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getHttpSetting();
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public ImageLoaderProtocol getImageLoader() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getImageLoader();
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IJsonParse getJsonParser(ParserModule parserModule) {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getJsonParser(parserModule);
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public ILog getLogger() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getLogger();
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IMta getMta() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getMta();
        }
        return null;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IToast getToast() {
        UtilProtocol utilProtocol = this.mUtilProxy;
        if (utilProtocol != null) {
            return utilProtocol.getToast();
        }
        return null;
    }

    public void setProtocolProxy(UtilProtocol utilProtocol) {
        this.mUtilProxy = utilProtocol;
    }

    public IJsonParse getJsonParser() {
        return getJsonParser(ParserModule.PARSER_FASTJSON);
    }
}
