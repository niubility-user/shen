package com.jd.cashier.app.jdlibcutter.impl.parser;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.log.ILog;
import com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser;
import com.jd.framework.json.JDJSON;
import java.util.List;

/* loaded from: classes13.dex */
public class JDJsonParser implements IJsonParser {
    private static final String TAG = "JDJsonParser";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser
    public <T> List<T> parseJsonToArray(String str, Class<T> cls) {
        try {
            return JDJSON.parseArray(str, cls);
        } catch (Exception e2) {
            ILog log = DependInitializer.getLog();
            if (log != null) {
                log.d(TAG, "parse string to List in exception \n" + e2.getMessage());
                return null;
            }
            return null;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser
    public <T> T parseJsonToObject(String str, Class<T> cls) {
        try {
            return (T) JDJSON.parseObject(str, cls);
        } catch (Exception e2) {
            ILog log = DependInitializer.getLog();
            if (log != null) {
                log.d(TAG, "parse String to object in exception \n" + e2.getMessage());
                return null;
            }
            return null;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser
    public String toJsonString(Object obj) {
        return obj != null ? JDJSON.toJSONString(obj) : "";
    }
}
