package com.jd.framework.retrofit.convertor;

import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/* loaded from: classes13.dex */
public class JdJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Type type;

    public JdJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override // retrofit2.Converter
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            return (T) JDJSON.parseObject(responseBody.string(), this.type, new Feature[0]);
        } finally {
            responseBody.close();
        }
    }
}
