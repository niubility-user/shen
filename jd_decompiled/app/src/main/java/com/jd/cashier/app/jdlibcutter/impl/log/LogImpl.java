package com.jd.cashier.app.jdlibcutter.impl.log;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.log.ILog;
import com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes13.dex */
public class LogImpl implements ILog {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void d(String str, String str2) {
        OKLog.d(str, str2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void e(String str, String str2) {
        OKLog.e(str, str2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void i(String str, String str2) {
        OKLog.i(str, str2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void v(String str, String str2) {
        OKLog.v(str, str2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void w(String str, String str2) {
        OKLog.w(str, str2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void d(String str, String str2, Throwable th) {
        OKLog.d(str, str2, th);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void e(String str, String str2, Throwable th) {
        OKLog.e(str, str2, th);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void i(String str, String str2, Throwable th) {
        OKLog.i(str, str2, th);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void v(String str, String str2, Throwable th) {
        OKLog.v(str, str2, th);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void w(String str, Throwable th) {
        OKLog.w(str, th);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void d(String str, Object obj) {
        IJsonParser jsonParser = DependInitializer.getJsonParser();
        if (jsonParser == null || obj == null) {
            return;
        }
        OKLog.d(str, jsonParser.toJsonString(obj));
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void e(String str, Object obj) {
        IJsonParser jsonParser = DependInitializer.getJsonParser();
        if (jsonParser == null || obj == null) {
            return;
        }
        OKLog.e(str, jsonParser.toJsonString(obj));
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void i(String str, Object obj) {
        IJsonParser jsonParser = DependInitializer.getJsonParser();
        if (jsonParser == null || obj == null) {
            return;
        }
        OKLog.i(str, jsonParser.toJsonString(obj));
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void w(String str, String str2, Throwable th) {
        OKLog.w(str, str2, th);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.log.ILog
    public void w(String str, Object obj) {
        IJsonParser jsonParser = DependInitializer.getJsonParser();
        if (jsonParser == null || obj == null) {
            return;
        }
        OKLog.w(str, jsonParser.toJsonString(obj));
    }
}
