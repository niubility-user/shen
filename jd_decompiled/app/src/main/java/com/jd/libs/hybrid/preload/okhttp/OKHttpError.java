package com.jd.libs.hybrid.preload.okhttp;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.Locale;

/* loaded from: classes16.dex */
public class OKHttpError {
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private HttpError f6141c;

    public OKHttpError(int i2, String str, HttpError httpError) {
        this.a = i2;
        this.f6141c = httpError;
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
        this.b = getErrorCodeStr();
    }

    public int getErrorCode() {
        return this.a;
    }

    public String getErrorCodeStr() {
        int i2 = this.a;
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? "UNKNOWN" : "JSON_CODE" : "RESPONSE_CODE" : "TIME_OUT" : "EXCEPTION";
    }

    public HttpError getHttpError() {
        return this.f6141c;
    }

    public String getMessage() {
        return this.b;
    }

    public void setErrorCode(int i2) {
        this.a = i2;
    }

    public void setHttpError(HttpError httpError) {
        this.f6141c = httpError;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    @NonNull
    public String toString() {
        Locale locale = Locale.getDefault();
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(this.a);
        objArr[1] = this.b;
        HttpError httpError = this.f6141c;
        objArr[2] = httpError != null ? httpError.toString() : DYConstants.DY_NULL_STR;
        return String.format(locale, "OKHttpError [code = %d, msg = %s, HttpError = %s ]", objArr);
    }
}
