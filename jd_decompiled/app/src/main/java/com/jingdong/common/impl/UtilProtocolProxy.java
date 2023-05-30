package com.jingdong.common.impl;

import com.jingdong.common.impl.encrypt.Encrypt;
import com.jingdong.common.impl.eventbus.GreenRobotBus;
import com.jingdong.common.impl.http.JDHttpImpl;
import com.jingdong.common.impl.imageloader.JDImageLoader;
import com.jingdong.common.impl.log.JDLog;
import com.jingdong.common.impl.parse.GsonParser;
import com.jingdong.common.impl.parse.JDJSONParser;
import com.jingdong.common.impl.tools.Mta;
import com.jingdong.common.impl.tools.ToastUtil;
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
public class UtilProtocolProxy implements UtilProtocol {
    private IEncrypt mEncrypt;
    private IEventBus mEventBus;
    private ImageLoaderProtocol mImageLoader;
    private IJsonParse mJsonParser;
    private ILog mLogger;
    private IMta mMta;
    private IToast mToast;

    /* renamed from: com.jingdong.common.impl.UtilProtocolProxy$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$protocol$ParserModule;

        static {
            int[] iArr = new int[ParserModule.values().length];
            $SwitchMap$com$jingdong$common$protocol$ParserModule = iArr;
            try {
                iArr[ParserModule.PARSER_GSON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$protocol$ParserModule[ParserModule.PARSER_FASTJSON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IEncrypt getEncrypt() {
        if (this.mEncrypt == null) {
            this.mEncrypt = new Encrypt();
        }
        return this.mEncrypt;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IEventBus getEventBus() {
        if (this.mEventBus == null) {
            this.mEventBus = new GreenRobotBus();
        }
        return this.mEventBus;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IHttpSetting getHttpSetting() {
        return new JDHttpImpl();
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public ImageLoaderProtocol getImageLoader() {
        if (this.mImageLoader == null) {
            this.mImageLoader = new JDImageLoader();
        }
        return this.mImageLoader;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IJsonParse getJsonParser(ParserModule parserModule) {
        if (AnonymousClass1.$SwitchMap$com$jingdong$common$protocol$ParserModule[parserModule.ordinal()] != 1) {
            this.mJsonParser = new JDJSONParser();
        } else {
            this.mJsonParser = new GsonParser();
        }
        return this.mJsonParser;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public ILog getLogger() {
        if (this.mLogger == null) {
            this.mLogger = new JDLog();
        }
        return this.mLogger;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IMta getMta() {
        if (this.mMta == null) {
            this.mMta = new Mta();
        }
        return this.mMta;
    }

    @Override // com.jingdong.common.protocol.UtilProtocol
    public IToast getToast() {
        if (this.mToast == null) {
            this.mToast = new ToastUtil();
        }
        return this.mToast;
    }
}
