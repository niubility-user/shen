package com.jd.cashier.app.jdlibcutter.protocol.parser;

import java.util.List;

/* loaded from: classes13.dex */
public interface IJsonParser {
    <T> List<T> parseJsonToArray(String str, Class<T> cls);

    <T> T parseJsonToObject(String str, Class<T> cls);

    String toJsonString(Object obj);
}
