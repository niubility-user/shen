package com.jd.lib.babel.servicekit.impl;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.babel.servicekit.iservice.IParser;
import java.util.List;

/* loaded from: classes13.dex */
public class ParserImpl implements IParser {
    @Override // com.jd.lib.babel.servicekit.iservice.IParser
    public <T> List<T> parseArray(String str, Class<T> cls) {
        return JDJSON.parseArray(str, cls);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IParser
    public <T> T parseObject(String str, Class<T> cls) {
        return (T) JDJSON.parseObject(str, cls);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IParser
    public Object string2Object(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return JDJSON.parse(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
