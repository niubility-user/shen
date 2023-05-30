package com.jdpay.net.http;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.lib.util.Utils;
import com.jdpay.net.Provider;
import com.jdpay.net.Request;
import com.jdpay.net.RequestAdapter;
import com.jdpay.net.ServiceFactory;
import com.jdpay.net.http.HttpRequest;
import com.jdpay.net.http.annotation.Entry;
import com.jdpay.net.http.annotation.Extras;
import com.jdpay.net.http.annotation.File;
import com.jdpay.net.http.annotation.HttpService;
import com.jdpay.net.http.annotation.Query;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes18.dex */
public class HttpRequestAdapter extends RequestAdapter {
    protected Object[] args;
    protected ServiceFactory factory;
    protected Method method;
    private Converter<Object, String> requestConverter;
    private Converter<HttpResponse, ?> responseConverter;

    @Override // com.jdpay.net.RequestAdapter
    public Object build(@NonNull Method method, @NonNull Object[] objArr, @NonNull ServiceFactory serviceFactory) {
        this.method = method;
        this.args = objArr;
        this.factory = serviceFactory;
        return this;
    }

    protected void handleParameterAnnotation(@NonNull Object[] objArr, int i2, @NonNull Annotation[] annotationArr, @NonNull HttpRequest.Builder builder) {
        for (Annotation annotation : annotationArr) {
            if (annotation instanceof Query) {
                Query query = (Query) annotation;
                builder.addParam(new HttpRequest.Param(query.value(), objArr[i2].toString(), true, query.encoded()));
            } else if (annotation instanceof Entry) {
                Entry entry = (Entry) annotation;
                Object obj = objArr[i2];
                Converter<Object, String> converter = this.requestConverter;
                String str = null;
                if (converter != null) {
                    try {
                        str = converter.convert(obj);
                    } catch (Throwable th) {
                        JDPayLog.e(th);
                    }
                } else if (obj != null) {
                    str = obj.toString();
                }
                if (str != null) {
                    if (entry.isMultipart()) {
                        builder.addPart(entry.contentType(), str);
                    } else {
                        builder.setEntry(entry.contentType(), str);
                    }
                }
            } else if (annotation instanceof File) {
                File file = (File) annotation;
                builder.addPart(file.value(), (java.io.File) objArr[i2], file.contentType());
            } else if (annotation instanceof Extras) {
                Object obj2 = objArr[i2];
                if (obj2 instanceof SparseArray) {
                    builder.setExtras((SparseArray) obj2);
                } else if (obj2 instanceof Map) {
                    Map map = (Map) obj2;
                    SparseArray<Object> sparseArray = new SparseArray<>(map.size());
                    for (Map.Entry entry2 : map.entrySet()) {
                        int intValue = entry2.getKey() instanceof Integer ? ((Integer) entry2.getKey()).intValue() : entry2.getKey().hashCode();
                        Object value = entry2.getValue();
                        if (value != null) {
                            sparseArray.put(intValue, value);
                        }
                    }
                    builder.setExtras(sparseArray);
                }
            }
        }
    }

    @Override // com.jdpay.net.RequestAdapter
    public Request<Converter> request() {
        Provider provider = this.factory.getProvider();
        if (provider != null) {
            HttpRequest.Builder builder = (HttpRequest.Builder) provider.obtainBuilder();
            Annotation[] annotations = this.method.getAnnotations();
            int length = annotations.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                Annotation annotation = annotations[i2];
                if (annotation instanceof HttpService) {
                    HttpService httpService = (HttpService) annotation;
                    builder.setUrl(httpService.url()).setMethod(httpService.method()).setEncrypt(httpService.isEncrypt());
                    this.requestConverter = (Converter) Utils.newInstance(this.factory.getRequestConverter(httpService.requestConverter()));
                    this.responseConverter = (Converter) Utils.newInstance(this.factory.getResponseConverter(httpService.responseConverter()));
                    break;
                }
                i2++;
            }
            Annotation[][] parameterAnnotations = this.method.getParameterAnnotations();
            int length2 = parameterAnnotations.length;
            for (int i3 = 0; i3 < length2; i3++) {
                Annotation[] annotationArr = parameterAnnotations[i3];
                if (annotationArr != null && annotationArr.length != 0) {
                    handleParameterAnnotation(this.args, i3, annotationArr, builder);
                }
            }
            builder.setResponseConverter((HttpRequest.Builder) this.responseConverter);
            HttpRequest httpRequest = (HttpRequest) builder.build();
            httpRequest.setResultType(this.resultType);
            if (this.extras != null) {
                for (int i4 = 0; i4 < this.extras.size(); i4++) {
                    httpRequest.putExtra(this.extras.keyAt(i4), this.extras.valueAt(i4));
                }
            }
            return httpRequest;
        }
        throw new IllegalArgumentException("No provider was found.");
    }
}
