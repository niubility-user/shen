package com.jd.libs.hybrid.offlineload.entity;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class LocalFileEntity implements IJsonfy<LocalFileEntity>, IInterfaceCheck {
    public LocalFileType configType;
    public String fileName;
    public int funcType;
    public Map<String, String> header;
    public InputStream inputStream;
    public String type;
    public String url;

    /* renamed from: com.jd.libs.hybrid.offlineload.entity.LocalFileEntity$1  reason: invalid class name */
    /* loaded from: classes16.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[LocalFileType.values().length];
            a = iArr;
            try {
                iArr[LocalFileType.FILE_TYPE_PKG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[LocalFileType.FILE_TYPE_PKG_SHARED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[LocalFileType.FILE_TYPE_GLOBAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[LocalFileType.FILE_TYPE_GLOBAL_BUILD_IN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private String a(String str, char c2) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == c2) {
                sb.append(charAt);
                z = true;
            } else if (z) {
                sb.append(Character.toUpperCase(charAt));
                z = false;
            } else {
                sb.append(Character.toLowerCase(charAt));
            }
        }
        return sb.toString();
    }

    public Pair<String, String> getConfigTypeDesc() {
        int i2 = AnonymousClass1.a[this.configType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return new Pair<>("\u672a\u77e5", "unknown");
                    }
                    return new Pair<>("\u5185\u7f6e\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6", "build-in global file");
                }
                return new Pair<>("\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6", "global file");
            }
            return new Pair<>("\u516c\u5171\u79bb\u7ebf\u5305\u6587\u4ef6", "shared pack");
        }
        return new Pair<>("\u9879\u76ee\u5185\u6587\u4ef6", "business pack");
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.inputStream != null) {
            jSONObject.put("inputStream", "Using input stream");
        }
        jSONObject.put(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, this.fileName);
        jSONObject.put("url", this.url);
        jSONObject.put("type", this.type);
        Map<String, String> map = this.header;
        if (map != null) {
            try {
                map.remove(null);
            } catch (Exception e2) {
                Log.e("LocalFileEntity", e2);
            }
            jSONObject.put("header", new JSONObject(this.header));
        }
        LocalFileType localFileType = this.configType;
        if (localFileType != null) {
            jSONObject.put("configType", localFileType.toString());
        }
        return jSONObject;
    }

    public String toString() {
        try {
            return toJson().toString();
        } catch (JSONException e2) {
            Log.e("LocalFileEntity", e2);
            return "";
        }
    }

    @Override // com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        Map<String, String> map;
        return ((this.inputStream == null && TextUtils.isEmpty(this.fileName)) || TextUtils.isEmpty(this.url) || (map = this.header) == null || TextUtils.isEmpty(map.get(HttpHeaders.CONTENT_TYPE))) ? false : true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public LocalFileEntity fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        this.fileName = jSONObject.optString(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, "");
        String optString = jSONObject.optString("url", "");
        this.url = optString;
        if (!TextUtils.isEmpty(optString)) {
            try {
                Uri parse = Uri.parse(this.url);
                String scheme = parse.getScheme();
                String host = parse.getHost();
                if (("https".equalsIgnoreCase(scheme) || "http".equalsIgnoreCase(scheme)) && !TextUtils.isEmpty(host)) {
                    this.url = HybridUrlUtils.getHostPath(parse);
                }
            } catch (Exception e2) {
                Log.e("LocalFileEntity", e2);
            }
        }
        this.type = jSONObject.optString("type", "");
        this.header = null;
        JSONObject optJSONObject = jSONObject.optJSONObject("header");
        if (optJSONObject != null) {
            this.header = new HashMap();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.header.put(a(next, '-'), optJSONObject.getString(next));
            }
        }
        return this;
    }
}
