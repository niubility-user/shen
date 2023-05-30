package com.jd.jdcache.entity;

import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import com.jd.jdcache.util.JDCacheLog;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\u001a!\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0007\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0007\u00a2\u0006\u0004\b\u0007\u0010\b\u001a!\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0007\u00a2\u0006\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/jd/jdcache/entity/JDCacheLocalResp;", "", "fileDirPath", "Landroid/webkit/WebResourceResponse;", "createResponse", "(Lcom/jd/jdcache/entity/JDCacheLocalResp;Ljava/lang/String;)Landroid/webkit/WebResourceResponse;", "jsonString", "jsonParse", "(Ljava/lang/String;)Lcom/jd/jdcache/entity/JDCacheLocalResp;", "", "jsonArrayParse", "(Ljava/lang/String;)Ljava/util/List;", "JDCache_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class JDCacheLocalRespKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00df, code lost:
        if (r1 != null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0123  */
    @androidx.annotation.Keep
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final android.webkit.WebResourceResponse createResponse(@org.jetbrains.annotations.NotNull com.jd.jdcache.entity.JDCacheLocalResp r18, @org.jetbrains.annotations.Nullable java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.entity.JDCacheLocalRespKt.createResponse(com.jd.jdcache.entity.JDCacheLocalResp, java.lang.String):android.webkit.WebResourceResponse");
    }

    public static /* synthetic */ WebResourceResponse createResponse$default(JDCacheLocalResp jDCacheLocalResp, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        return createResponse(jDCacheLocalResp, str);
    }

    @Keep
    @Nullable
    public static final List<JDCacheLocalResp> jsonArrayParse(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() > 0) {
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JDCacheLocalResp jsonParse = jsonParse(jSONArray.get(i2).toString());
                    if (jsonParse != null) {
                        arrayList.add(jsonParse);
                    }
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e("JDCacheLocalResp", e2);
            }
            return null;
        }
    }

    @Keep
    @Nullable
    public static final JDCacheLocalResp jsonParse(@Nullable String str) {
        HashMap hashMap;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("url");
            Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"url\")");
            String string2 = jSONObject.getString("type");
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"type\")");
            JSONObject optJSONObject = jSONObject.optJSONObject("header");
            if (optJSONObject != null) {
                HashMap hashMap2 = new HashMap();
                Iterator<String> keys = optJSONObject.keys();
                Intrinsics.checkExpressionValueIsNotNull(keys, "it.keys()");
                while (keys.hasNext()) {
                    String next = keys.next();
                    String string3 = optJSONObject.getString(next);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "it.getString(key)");
                    hashMap2.put(next, string3);
                }
                hashMap = hashMap2;
            } else {
                hashMap = null;
            }
            return new JDCacheLocalResp(string, string2, hashMap, jSONObject.getString(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME), null, false, 48, null);
        } catch (JSONException e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e("JDCacheLocalResp", e2);
            }
            return null;
        }
    }
}
