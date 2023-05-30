package com.jd.lib.babel.servicekit.iservice;

import java.util.List;

/* loaded from: classes13.dex */
public interface IParser {
    <T> List<T> parseArray(String str, Class<T> cls);

    <T> T parseObject(String str, Class<T> cls);

    Object string2Object(String str);
}
