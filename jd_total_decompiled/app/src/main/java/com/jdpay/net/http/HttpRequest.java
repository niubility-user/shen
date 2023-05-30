package com.jdpay.net.http;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.jd.jdcache.util.UrlHelper;
import com.jdpay.lib.converter.Converter;
import com.jdpay.net.Request;
import java.io.File;
import java.lang.reflect.Type;
import java.util.LinkedList;

/* loaded from: classes18.dex */
public abstract class HttpRequest extends Request<Converter> {
    private String method;
    private Type resultType;
    private String url;

    /* loaded from: classes18.dex */
    public static abstract class Builder<T extends HttpRequest, RPC extends Converter<HttpResponse, ?>> implements Request.Builder<T, RPC> {
        protected boolean encrypt;
        protected SparseArray<Object> extras;
        protected RPC responseConverter;
        protected String url;
        protected String method = "GET";
        protected final LinkedList<Param> headers = new LinkedList<>();
        protected final LinkedList<Param> params = new LinkedList<>();

        public Builder addHeader(@NonNull Param param) {
            this.headers.add(param);
            return this;
        }

        public Builder addParam(@NonNull Param param) {
            this.params.add(param);
            return this;
        }

        public abstract Builder addPart(@NonNull String str, @NonNull File file);

        public abstract Builder addPart(@NonNull String str, @NonNull File file, @NonNull String str2);

        public abstract Builder addPart(@NonNull String str, @NonNull Object obj);

        public abstract Builder setEntry(@NonNull String str, @NonNull Object obj);

        @Override // com.jdpay.net.Request.Builder
        public /* bridge */ /* synthetic */ Request.Builder setExtras(SparseArray sparseArray) {
            return setExtras((SparseArray<Object>) sparseArray);
        }

        public Builder setMethod(@NonNull String str) {
            this.method = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.jdpay.net.Request.Builder
        public /* bridge */ /* synthetic */ Request.Builder setResponseConverter(@NonNull Converter converter) {
            return setResponseConverter((Builder<T, RPC>) converter);
        }

        public Builder setUrl(@NonNull String str) {
            this.url = str;
            return this;
        }

        @Override // com.jdpay.net.Request.Builder
        public Builder setEncrypt(boolean z) {
            this.encrypt = z;
            return this;
        }

        @Override // com.jdpay.net.Request.Builder
        public Builder setExtras(SparseArray<Object> sparseArray) {
            this.extras = sparseArray;
            return this;
        }

        @Override // com.jdpay.net.Request.Builder
        public Builder setResponseConverter(@NonNull RPC rpc) {
            this.responseConverter = rpc;
            return this;
        }
    }

    /* loaded from: classes18.dex */
    public static class Param {
        public boolean isEncoded;
        public boolean isUrlQueryParam;
        public String key;
        public String value;

        public Param(String str, String str2) {
            this(str, str2, false, false);
        }

        public Param(String str, String str2, boolean z) {
            this(str, str2, z, false);
        }

        public Param(String str, String str2, boolean z, boolean z2) {
            this.key = str;
            this.value = str2;
            this.isUrlQueryParam = z;
            this.isEncoded = z2;
        }
    }

    public HttpRequest(String str, String str2) {
        this.url = str;
        this.method = str2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean hasBody(String str) {
        char c2;
        switch (str.hashCode()) {
            case -531492226:
                if (str.equals(UrlHelper.METHOD_OPTIONS)) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            case 70454:
                if (str.equals("GET")) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case 79599:
                if (str.equals(UrlHelper.METHOD_PUT)) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case 2213344:
                if (str.equals(UrlHelper.METHOD_HEAD)) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            case 2461856:
                if (str.equals("POST")) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case 75900968:
                if (str.equals(UrlHelper.METHOD_PATCH)) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case 2012838315:
                if (str.equals(UrlHelper.METHOD_DELETE)) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        return c2 == 0 || c2 == 1 || c2 == 2;
    }

    public abstract void cancel();

    public String getMethod() {
        return this.method;
    }

    public Type getResultType() {
        return this.resultType;
    }

    public String getUrl() {
        return this.url;
    }

    public abstract boolean isCanceled();

    public void setResultType(Type type) {
        this.resultType = type;
    }

    public HttpRequest(int i2, String str, String str2) {
        super(i2);
        this.url = str;
        this.method = str2;
    }

    public HttpRequest(int i2, int i3, String str, String str2) {
        super(i2, i3);
        this.url = str;
        this.method = str2;
    }
}
