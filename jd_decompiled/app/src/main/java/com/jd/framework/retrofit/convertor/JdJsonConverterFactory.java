package com.jd.framework.retrofit.convertor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* loaded from: classes13.dex */
public class JdJsonConverterFactory extends Converter.Factory {
    public static final String TAG = "JdJsonConverterFactory";

    public static JdJsonConverterFactory create() {
        return new JdJsonConverterFactory();
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new JdJsonRequestBodyConverter(type);
    }

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new JdJsonResponseBodyConverter(type);
    }
}
