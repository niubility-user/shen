package com.jd.framework.retrofit.convertor;

import com.alibaba.fastjson.JSONWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/* loaded from: classes13.dex */
public class JdJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private Type type;

    public JdJsonRequestBodyConverter(Type type) {
        this.type = type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // retrofit2.Converter
    public /* bridge */ /* synthetic */ RequestBody convert(Object obj) throws IOException {
        return convert2((JdJsonRequestBodyConverter<T>) obj);
    }

    @Override // retrofit2.Converter
    /* renamed from: convert  reason: avoid collision after fix types in other method */
    public RequestBody convert2(T t) throws IOException {
        Buffer buffer = new Buffer();
        JSONWriter jSONWriter = new JSONWriter(new OutputStreamWriter(buffer.outputStream(), UTF_8));
        jSONWriter.writeValue(t);
        jSONWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteArray());
    }
}
