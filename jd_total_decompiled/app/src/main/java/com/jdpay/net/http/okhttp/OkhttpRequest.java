package com.jdpay.net.http.okhttp;

import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.net.http.HttpRequest;
import com.jdpay.net.http.HttpResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;

/* loaded from: classes18.dex */
public class OkhttpRequest extends HttpRequest {
    protected Call call;
    protected Request rawRequest;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class ContentTypeOverridingRequestBody extends RequestBody {
        private final MediaType contentType;
        private final RequestBody delegate;

        ContentTypeOverridingRequestBody(RequestBody requestBody, MediaType mediaType) {
            this.delegate = requestBody;
            this.contentType = mediaType;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.delegate.writeTo(bufferedSink);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public static class OkhttpBuilder extends HttpRequest.Builder<OkhttpRequest, Converter<HttpResponse, ?>> {
        private RequestBody body;
        private MediaType contentType;
        private FormBody.Builder formBuilder;
        private HttpUrl httpUrl;
        private MultipartBody.Builder multipartBuilder;
        private Request.Builder requestBuilder = new Request.Builder();
        private HttpUrl.Builder urlBuilder;

        private MultipartBody.Builder createMultipartBuilder() {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            return builder;
        }

        @Override // com.jdpay.net.http.HttpRequest.Builder
        public HttpRequest.Builder setEntry(@NonNull String str, @NonNull Object obj) {
            MediaType parse = MediaType.parse(str);
            if (obj instanceof byte[]) {
                this.body = RequestBody.create(parse, (byte[]) obj);
            } else if (obj instanceof String) {
                this.body = RequestBody.create(parse, (String) obj);
            } else {
                this.body = RequestBody.create(parse, obj.toString());
            }
            return this;
        }

        @Override // com.jdpay.net.http.HttpRequest.Builder
        public HttpRequest.Builder setUrl(@NonNull String str) {
            this.httpUrl = HttpUrl.parse(str);
            return super.setUrl(str);
        }

        @Override // com.jdpay.net.Request.Builder
        public OkhttpRequest build() {
            if (TextUtils.isEmpty(this.method)) {
                this.method = "GET";
            }
            Iterator<HttpRequest.Param> it = this.headers.iterator();
            while (it.hasNext()) {
                HttpRequest.Param next = it.next();
                if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(next.key)) {
                    MediaType parse = MediaType.parse(next.value);
                    if (parse != null) {
                        this.contentType = parse;
                    } else {
                        throw new IllegalArgumentException("Malformed content type: " + next.value);
                    }
                } else {
                    this.requestBuilder.addHeader(next.key, next.value);
                }
            }
            Iterator<HttpRequest.Param> it2 = this.params.iterator();
            while (it2.hasNext()) {
                HttpRequest.Param next2 = it2.next();
                if (next2.isUrlQueryParam) {
                    if (this.urlBuilder == null) {
                        this.urlBuilder = this.httpUrl.newBuilder();
                    }
                    if (next2.isEncoded) {
                        this.urlBuilder.addEncodedQueryParameter(next2.key, next2.value);
                    } else {
                        this.urlBuilder.addQueryParameter(next2.key, next2.value);
                    }
                } else {
                    MultipartBody.Builder builder = this.multipartBuilder;
                    if (builder != null) {
                        builder.addFormDataPart(next2.key, next2.value);
                    } else {
                        if (this.formBuilder == null) {
                            this.formBuilder = new FormBody.Builder();
                        }
                        if (next2.isEncoded) {
                            this.formBuilder.addEncoded(next2.key, next2.value);
                        } else {
                            this.formBuilder.add(next2.key, next2.value);
                        }
                    }
                }
            }
            HttpUrl.Builder builder2 = this.urlBuilder;
            HttpUrl build = builder2 == null ? this.httpUrl : builder2.build();
            RequestBody requestBody = this.body;
            if (requestBody == null) {
                MultipartBody.Builder builder3 = this.multipartBuilder;
                if (builder3 != null) {
                    requestBody = builder3.build();
                } else {
                    FormBody.Builder builder4 = this.formBuilder;
                    if (builder4 != null) {
                        requestBody = builder4.build();
                    } else if (HttpRequest.hasBody(this.method)) {
                        requestBody = RequestBody.create((MediaType) null, new byte[0]);
                    }
                }
            }
            MediaType mediaType = this.contentType;
            ContentTypeOverridingRequestBody contentTypeOverridingRequestBody = requestBody;
            if (mediaType != null) {
                if (requestBody != null) {
                    contentTypeOverridingRequestBody = new ContentTypeOverridingRequestBody(requestBody, mediaType);
                } else {
                    this.requestBuilder.addHeader(HttpHeaders.CONTENT_TYPE, mediaType.toString());
                    contentTypeOverridingRequestBody = requestBody;
                }
            }
            StringBuilder sb = new StringBuilder(this.method);
            sb.append(' ');
            if (contentTypeOverridingRequestBody != null) {
                Buffer buffer = new Buffer();
                sb.append(this.url);
                sb.append('\n');
                try {
                    contentTypeOverridingRequestBody.writeTo(buffer);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                sb.append(buffer.readUtf8());
            } else {
                sb.append(build.url().toString());
            }
            JDPayLog.w(sb.toString());
            SparseArray<Object> sparseArray = this.extras;
            int size = sparseArray != null ? sparseArray.size() : 0;
            OkhttpRequest okhttpRequest = new OkhttpRequest(size, this.url, this.method);
            ((com.jdpay.net.Request) okhttpRequest).responseConverter = this.responseConverter;
            ((com.jdpay.net.Request) okhttpRequest).encrypt = this.encrypt;
            okhttpRequest.rawRequest = this.requestBuilder.url(build).method(this.method, contentTypeOverridingRequestBody).build();
            for (int i2 = 0; i2 < size; i2++) {
                ((com.jdpay.net.Request) okhttpRequest).extras.put(this.extras.keyAt(i2), this.extras.valueAt(i2));
            }
            return okhttpRequest;
        }

        @Override // com.jdpay.net.http.HttpRequest.Builder
        public OkhttpBuilder addPart(@NonNull String str, @NonNull File file) {
            addPart(str, file, "application/octet-stream");
            return this;
        }

        @Override // com.jdpay.net.http.HttpRequest.Builder
        public OkhttpBuilder addPart(@NonNull String str, @NonNull File file, @NonNull String str2) {
            if (this.multipartBuilder == null) {
                this.multipartBuilder = createMultipartBuilder();
            }
            this.multipartBuilder.addFormDataPart(str, file.getName(), RequestBody.create(MediaType.parse(str2), file));
            return this;
        }

        @Override // com.jdpay.net.http.HttpRequest.Builder
        public OkhttpBuilder addPart(@NonNull String str, @NonNull Object obj) {
            if (this.multipartBuilder == null) {
                this.multipartBuilder = createMultipartBuilder();
            }
            MediaType parse = MediaType.parse(str);
            if (obj instanceof byte[]) {
                this.multipartBuilder.addPart(RequestBody.create(parse, (byte[]) obj));
            } else if (obj instanceof String) {
                this.multipartBuilder.addPart(RequestBody.create(parse, (String) obj));
            } else {
                this.multipartBuilder.addPart(RequestBody.create(parse, obj.toString()));
            }
            return this;
        }
    }

    public OkhttpRequest(String str, String str2) {
        super(str, str2);
    }

    @Override // com.jdpay.net.http.HttpRequest
    public void cancel() {
        Call call = this.call;
        if (call == null || call.isCanceled()) {
            return;
        }
        this.call.cancel();
    }

    @Override // com.jdpay.net.http.HttpRequest
    public boolean isCanceled() {
        Call call = this.call;
        return call != null && call.isCanceled();
    }

    public OkhttpRequest(int i2, String str, String str2) {
        super(i2, str, str2);
    }

    public OkhttpRequest(int i2, int i3, String str, String str2) {
        super(i2, i3, str, str2);
    }
}
