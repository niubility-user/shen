package com.jd.jdcache.entity;

import androidx.annotation.Keep;
import com.jd.dynamic.DYConstants;
import com.jd.jdcache.util.IUsefulCheck;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010!\u001a\u00020\u0005\u0012\u0006\u0010\"\u001a\u00020\u0005\u0012\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001d\u0012\b\b\u0002\u0010%\u001a\u00020\u0011\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ%\u0010\r\u001a\u00020\u00022\u0016\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0015J\u0010\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0015J \u0010\u001b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0010J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u0015J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0011H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u0013J`\u0010&\u001a\u00020\u00002\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010%\u001a\u00020\u0011H\u00c6\u0001\u00a2\u0006\u0004\b&\u0010'J\u0010\u0010)\u001a\u00020(H\u00d6\u0001\u00a2\u0006\u0004\b)\u0010*J\u001a\u0010-\u001a\u00020\u00112\b\u0010,\u001a\u0004\u0018\u00010+H\u00d6\u0003\u00a2\u0006\u0004\b-\u0010.R$\u0010#\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010/\u001a\u0004\b0\u0010\u0015\"\u0004\b1\u00102R$\u0010$\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u00103\u001a\u0004\b4\u0010\u001f\"\u0004\b5\u00106R\u0019\u0010%\u001a\u00020\u00118\u0006@\u0006\u00a2\u0006\f\n\u0004\b%\u00107\u001a\u0004\b8\u0010\u0013R&\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\f\u00109R\u0019\u0010\"\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\"\u0010/\u001a\u0004\b:\u0010\u0015R\u0019\u0010!\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b!\u0010/\u001a\u0004\b;\u0010\u0015\u00a8\u0006>"}, d2 = {"Lcom/jd/jdcache/entity/JDCacheLocalResp;", "Lcom/jd/jdcache/util/IUsefulCheck;", "", "safeChangeHeader", "()V", "", "text", "", "delimiter", "toUpperCamelCase", "(Ljava/lang/String;C)Ljava/lang/String;", "", "header", "setHeader", "(Ljava/util/Map;)V", "getHeader", "()Ljava/util/Map;", "", "useful", "()Z", "toString", "()Ljava/lang/String;", "Lorg/json/JSONObject;", "toJson", "()Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "Ljava/io/InputStream;", "component5", "()Ljava/io/InputStream;", "component6", "url", "type", TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, "fileStream", "needSafeChangeHeader", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/io/InputStream;Z)Lcom/jd/jdcache/entity/JDCacheLocalResp;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getFilename", "setFilename", "(Ljava/lang/String;)V", "Ljava/io/InputStream;", "getFileStream", "setFileStream", "(Ljava/io/InputStream;)V", "Z", "getNeedSafeChangeHeader", "Ljava/util/Map;", "getType", "getUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/io/InputStream;Z)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final /* data */ class JDCacheLocalResp implements IUsefulCheck {
    @Nullable
    private transient InputStream fileStream;
    @Nullable
    private String filename;
    @JvmField
    @Nullable
    public Map<String, String> header;
    private final transient boolean needSafeChangeHeader;
    @NotNull
    private final String type;
    @NotNull
    private final String url;

    public JDCacheLocalResp(@NotNull String str, @NotNull String str2, @Nullable Map<String, String> map, @Nullable String str3, @Nullable InputStream inputStream, boolean z) {
        this.url = str;
        this.type = str2;
        this.header = map;
        this.filename = str3;
        this.fileStream = inputStream;
        this.needSafeChangeHeader = z;
        if (z) {
            safeChangeHeader();
        }
    }

    public static /* synthetic */ JDCacheLocalResp copy$default(JDCacheLocalResp jDCacheLocalResp, String str, String str2, Map map, String str3, InputStream inputStream, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = jDCacheLocalResp.url;
        }
        if ((i2 & 2) != 0) {
            str2 = jDCacheLocalResp.type;
        }
        String str4 = str2;
        Map<String, String> map2 = map;
        if ((i2 & 4) != 0) {
            map2 = jDCacheLocalResp.header;
        }
        Map map3 = map2;
        if ((i2 & 8) != 0) {
            str3 = jDCacheLocalResp.filename;
        }
        String str5 = str3;
        if ((i2 & 16) != 0) {
            inputStream = jDCacheLocalResp.fileStream;
        }
        InputStream inputStream2 = inputStream;
        if ((i2 & 32) != 0) {
            z = jDCacheLocalResp.needSafeChangeHeader;
        }
        return jDCacheLocalResp.copy(str, str4, map3, str5, inputStream2, z);
    }

    private final void safeChangeHeader() {
        int mapCapacity;
        Map<String, String> map = this.header;
        LinkedHashMap linkedHashMap = null;
        if (map != null) {
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(map.size());
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(mapCapacity);
            Iterator<T> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                linkedHashMap2.put(str != null ? toUpperCamelCase(str, '-') : null, entry.getValue());
            }
            linkedHashMap = linkedHashMap2;
        }
        this.header = TypeIntrinsics.asMutableMap(linkedHashMap);
    }

    private final String toUpperCamelCase(String text, char delimiter) {
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        boolean z = true;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = text.charAt(i2);
            if (charAt == delimiter) {
                sb.append(charAt);
                z = true;
            } else if (z) {
                sb.append(Character.toUpperCase(charAt));
                z = false;
            } else {
                sb.append(Character.toLowerCase(charAt));
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    @NotNull
    /* renamed from: component2  reason: from getter */
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final Map<String, String> component3() {
        return this.header;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final InputStream getFileStream() {
        return this.fileStream;
    }

    /* renamed from: component6  reason: from getter */
    public final boolean getNeedSafeChangeHeader() {
        return this.needSafeChangeHeader;
    }

    @NotNull
    public final JDCacheLocalResp copy(@NotNull String url, @NotNull String type, @Nullable Map<String, String> header, @Nullable String filename, @Nullable InputStream fileStream, boolean needSafeChangeHeader) {
        return new JDCacheLocalResp(url, type, header, filename, fileStream, needSafeChangeHeader);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof JDCacheLocalResp) {
                JDCacheLocalResp jDCacheLocalResp = (JDCacheLocalResp) other;
                return Intrinsics.areEqual(this.url, jDCacheLocalResp.url) && Intrinsics.areEqual(this.type, jDCacheLocalResp.type) && Intrinsics.areEqual(this.header, jDCacheLocalResp.header) && Intrinsics.areEqual(this.filename, jDCacheLocalResp.filename) && Intrinsics.areEqual(this.fileStream, jDCacheLocalResp.fileStream) && this.needSafeChangeHeader == jDCacheLocalResp.needSafeChangeHeader;
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final InputStream getFileStream() {
        return this.fileStream;
    }

    @Nullable
    public final String getFilename() {
        return this.filename;
    }

    @Nullable
    public final Map<String, String> getHeader() {
        return this.header;
    }

    public final boolean getNeedSafeChangeHeader() {
        return this.needSafeChangeHeader;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.url;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.type;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Map<String, String> map = this.header;
        int hashCode3 = (hashCode2 + (map != null ? map.hashCode() : 0)) * 31;
        String str3 = this.filename;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        InputStream inputStream = this.fileStream;
        int hashCode5 = (hashCode4 + (inputStream != null ? inputStream.hashCode() : 0)) * 31;
        boolean z = this.needSafeChangeHeader;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return hashCode5 + i2;
    }

    public final void setFileStream(@Nullable InputStream inputStream) {
        this.fileStream = inputStream;
    }

    public final void setFilename(@Nullable String str) {
        this.filename = str;
    }

    public final void setHeader(@Nullable Map<String, String> header) {
        this.header = header;
        if (this.needSafeChangeHeader) {
            safeChangeHeader();
        }
    }

    @NotNull
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("type", this.type);
        String str = this.filename;
        if (str != null) {
            jSONObject.put(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, str);
        }
        InputStream inputStream = this.fileStream;
        if (inputStream != null) {
            jSONObject.put("fileStream", inputStream);
        }
        Map<String, String> map = this.header;
        if (map != null) {
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    key = DYConstants.DY_NULL_STR;
                }
                jSONObject2.put(key, entry.getValue());
            }
            jSONObject.put("header", jSONObject2);
        }
        return jSONObject;
    }

    @NotNull
    public String toString() {
        String jSONObject = toJson().toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject, "toJson().toString()");
        return jSONObject;
    }

    @Override // com.jd.jdcache.util.IUsefulCheck
    public boolean useful() {
        String str = this.filename;
        return ((str == null || str.length() == 0) && this.fileStream == null) ? false : true;
    }

    public /* synthetic */ JDCacheLocalResp(String str, String str2, Map map, String str3, InputStream inputStream, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : inputStream, (i2 & 32) != 0 ? true : z);
    }
}
