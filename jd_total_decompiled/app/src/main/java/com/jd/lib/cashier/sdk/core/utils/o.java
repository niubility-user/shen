package com.jd.lib.cashier.sdk.core.utils;

import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser;

/* loaded from: classes14.dex */
public class o {
    private static final Object a = new Object();

    public static <T> T a(String str, Class<T> cls) {
        synchronized (a) {
            try {
                try {
                    IJsonParser jsonParser = DependInitializer.getJsonParser();
                    if (jsonParser != null) {
                        return (T) jsonParser.parseJsonToObject(str, cls);
                    }
                } catch (Exception e2) {
                    r.b("JDJSONParser", "parse String to object in exception \n" + e2.getMessage());
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String b(Object obj) {
        synchronized (a) {
            IJsonParser jsonParser = DependInitializer.getJsonParser();
            if (obj == null || jsonParser == null) {
                return "";
            }
            return jsonParser.toJsonString(obj);
        }
    }
}
