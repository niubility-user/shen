package com.jd.libs.hybrid.requestpreload.network;

import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jd.libs.xwin.http.b;
import com.jd.libs.xwin.http.c;
import com.jingdong.jdsdk.a.a;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation for ;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006JI\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\"\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJI\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\"\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u00048\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/network/RequestHelper;", "", "", "code", "", "getPreloadStatusStr", "(I)Ljava/lang/String;", "url", "method", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "paramsMap", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "doRequestAsync", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", a.a, "TAG", "Ljava/lang/String;", "CODE_PRELOAD_CANCEL", "I", "CODE_PRELOAD_PARAMS_ERROR", "CODE_PRELOAD_NULL", "CODE_PRELOAD_NET_FAIL", "CODE_PRELOAD_SUCCESS", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestHelper {
    public static final int CODE_PRELOAD_CANCEL = -1;
    public static final int CODE_PRELOAD_NET_FAIL = -1;
    public static final int CODE_PRELOAD_NULL = -2;
    public static final int CODE_PRELOAD_PARAMS_ERROR = -3;
    public static final int CODE_PRELOAD_SUCCESS = 200;
    public static final RequestHelper INSTANCE = new RequestHelper();
    @NotNull
    public static final String TAG = "RequestHelper";

    private RequestHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object a(@NotNull String str, @NotNull String str2, @NotNull HashMap<String, String> hashMap, @NotNull Continuation<? super CacheItem> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        final SafeContinuation for  r0 = new SafeContinuation for (intercepted);
        b bVar = new b(str);
        bVar.setUserAgent(HybridBase.getInstance().getSetting("userAgent"));
        bVar.setCookies(HybridBase.getInstance().getCookieString(str));
        if (Intrinsics.areEqual(str2, IMantoServerRequester.GET)) {
            bVar.isEncodeGetParam = false;
            bVar.setMethod(257);
            bVar.setParams(hashMap);
        } else {
            bVar.setMethod(258);
            bVar.setBody(hashMap);
        }
        bVar.addHeader("Request-From", "jdhybrid-preFetch");
        bVar.addHeader(HttpHeaders.ReferrerPolicyValues.ORIGIN, CommonUtil.INSTANCE.getUrlWithoutPath(str));
        bVar.a(new b.a() { // from class: com.jd.libs.hybrid.requestpreload.network.RequestHelper$doRequest$2$1
            @Override // com.jd.libs.xwin.http.b.a
            public void onError(int i2, @NotNull Map<String, ? extends List<String>> map, @NotNull String s) {
                Continuation continuation2 = Continuation.this;
                Result.Companion companion = Result.INSTANCE;
                continuation2.resumeWith(Result.m200constructorimpl(null));
            }

            @Override // com.jd.libs.xwin.http.b.a
            public void onRedirect(int i2, @NotNull Map<String, ? extends List<String>> map, @NotNull String s) {
            }

            @Override // com.jd.libs.xwin.http.b.a
            public void onStart() {
            }

            @Override // com.jd.libs.xwin.http.b.a
            public void onSusses(int i2, @NotNull Map<String, ? extends List<String>> map, @NotNull String result) {
                try {
                    CacheItem cacheItem = new CacheItem(result, new JSONObject(result));
                    Continuation continuation2 = Continuation.this;
                    Result.Companion companion = Result.INSTANCE;
                    continuation2.resumeWith(Result.m200constructorimpl(cacheItem));
                } catch (JSONException unused) {
                    Continuation continuation3 = Continuation.this;
                    Result.Companion companion2 = Result.INSTANCE;
                    continuation3.resumeWith(Result.m200constructorimpl(null));
                }
            }
        });
        c.a(bVar);
        Object orThrow = r0.getOrThrow();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (orThrow == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    @Nullable
    public final Object doRequestAsync(@NotNull String str, @NotNull String str2, @NotNull HashMap<String, String> hashMap, @NotNull Continuation<? super CacheItem> continuation) {
        Deferred async$default;
        async$default = BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RequestHelper$doRequestAsync$2(str, str2, hashMap, null), 2, null);
        return async$default.await(continuation);
    }

    @Nullable
    public final String getPreloadStatusStr(int code) {
        return code != -3 ? code != -2 ? code != -1 ? code != 200 ? "" : "\u8bf7\u6c42\u6210\u529f" : "\u7f51\u7edc\u5931\u8d25" : "\u672a\u914d\u7f6e\u9884\u52a0\u8f7d" : "\u53c2\u6570\u6a21\u677f\u89e3\u6790\u9519\u8bef";
    }
}
