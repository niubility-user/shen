package com.jd.cashier.app.jdlibcutter.impl.http;

import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpCreator;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;

/* loaded from: classes13.dex */
public class JDHttpCreator implements IHttpCreator {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.IHttpCreator
    public IHttpSetting createHttp() {
        return new JDHttpImpl();
    }
}
