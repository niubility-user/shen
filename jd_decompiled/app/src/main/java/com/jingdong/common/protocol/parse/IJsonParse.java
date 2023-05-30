package com.jingdong.common.protocol.parse;

import java.util.List;

/* loaded from: classes5.dex */
public interface IJsonParse {
    boolean optBoolean(String str, String str2);

    int optInt(String str, String str2);

    Object optJsonArray(String str, String str2);

    String optString(String str, String str2);

    <T> List<T> parseJsonToArray(String str, Class<T> cls);

    <T> T parseJsonToObject(String str, Class<T> cls);

    Object toJsonObject(String str);

    String toJsonString(Object obj);
}
