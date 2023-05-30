package com.jd.jdcache.service.impl.net;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015JS\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00032\u001e\u0010\u0007\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00028\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/jd/jdcache/service/impl/net/HttpRequest;", "Lcom/jd/jdcache/service/impl/net/BaseRequest;", "", "", "responseCode", "", "", "responseHeaders", "", "contentLength", "Ljava/io/InputStream;", "inputStream", "Lcom/jd/jdcache/service/base/NetState;", "parseData", "(ILjava/util/Map;JLjava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "url", "<init>", "(Ljava/lang/String;)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class HttpRequest extends BaseRequest<String> {
    @NotNull
    private final String TAG;

    public HttpRequest(@NotNull String str) {
        super(str, null, null, null, null, null, null, false, null, 0, 0, R2.attr.textRightColor, null);
        this.TAG = "HttpRequest";
    }

    @Override // com.jd.jdcache.service.impl.net.BaseRequest
    @NotNull
    public String getTAG() {
        return this.TAG;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.String] */
    @Override // com.jd.jdcache.service.impl.net.BaseRequest
    @Nullable
    protected Object parseData(int i2, @Nullable Map<String, ? extends List<String>> map, long j2, @Nullable InputStream inputStream, @NotNull Continuation<? super NetState<String>> continuation) {
        BufferedReader bufferedReader;
        Object error;
        String bufferedReader2;
        String sb;
        StringBuilder sb2 = null;
        if (!(!Intrinsics.areEqual(getMethod(), UrlHelper.METHOD_HEAD)) == true || inputStream == null) {
            bufferedReader = null;
        } else {
            sb2 = new StringBuilder();
            BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(inputStream));
            try {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                while (true) {
                    ?? readLine = bufferedReader3.readLine();
                    objectRef.element = readLine;
                    if (readLine == 0) {
                        break;
                    }
                    sb2.append((String) readLine);
                    sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                bufferedReader = bufferedReader3;
            } catch (Exception e2) {
                return new NetState.Error(-1, e2);
            }
        }
        String str = "";
        if (i2 == 200) {
            error = new NetState.Complete(i2, map, j2, (sb2 == null || (sb = sb2.toString()) == null) ? "" : sb);
        } else {
            if (bufferedReader != null && (bufferedReader2 = bufferedReader.toString()) != null) {
                str = bufferedReader2;
            }
            error = new NetState.Error(i2, new Exception(str));
        }
        try {
        } catch (IOException e3) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getTAG(), e3);
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        } else {
            if (inputStream != null) {
                inputStream.close();
            }
            disconnect();
            return error;
        }
        disconnect();
        return error;
    }
}
