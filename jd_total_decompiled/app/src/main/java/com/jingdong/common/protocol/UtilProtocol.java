package com.jingdong.common.protocol;

import com.jingdong.common.protocol.encrypt.IEncrypt;
import com.jingdong.common.protocol.eventbus.IEventBus;
import com.jingdong.common.protocol.http.IHttpSetting;
import com.jingdong.common.protocol.imageloader.ImageLoaderProtocol;
import com.jingdong.common.protocol.log.ILog;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.common.protocol.tools.IMta;
import com.jingdong.common.protocol.tools.IToast;

/* loaded from: classes5.dex */
public interface UtilProtocol {
    IEncrypt getEncrypt();

    IEventBus getEventBus();

    IHttpSetting getHttpSetting();

    ImageLoaderProtocol getImageLoader();

    IJsonParse getJsonParser(ParserModule parserModule);

    ILog getLogger();

    IMta getMta();

    IToast getToast();
}
