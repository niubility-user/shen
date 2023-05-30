package com.jingdong.sdk.jfsprovider;

import android.content.Context;
import androidx.collection.ArrayMap;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.TypeToken;

/* loaded from: classes7.dex */
public class ImageUrlProvider {
    private static final String TAG = "ImageUrlProvider";
    private static final ImageUrlProvider ourInstance = new ImageUrlProvider();
    private static ArrayMap<String, ArrayMap<String, String>> imageUrlMaps = new ArrayMap<>();

    private ImageUrlProvider() {
    }

    public static ImageUrlProvider getInstance() {
        return ourInstance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0037, code lost:
        if (r2 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String readJsonFromAsset(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L36
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L36
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L36
            java.io.InputStream r5 = r5.open(r6)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L36
            java.lang.String r6 = "UTF-8"
            r3.<init>(r5, r6)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L36
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2f java.io.IOException -> L36
        L1a:
            java.lang.String r5 = r2.readLine()     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2d
            if (r5 == 0) goto L24
            r0.append(r5)     // Catch: java.lang.Throwable -> L2a java.io.IOException -> L2d
            goto L1a
        L24:
            r2.close()     // Catch: java.io.IOException -> L28
            goto L3a
        L28:
            goto L3a
        L2a:
            r5 = move-exception
            r1 = r2
            goto L30
        L2d:
            goto L37
        L2f:
            r5 = move-exception
        L30:
            if (r1 == 0) goto L35
            r1.close()     // Catch: java.io.IOException -> L35
        L35:
            throw r5
        L36:
            r2 = r1
        L37:
            if (r2 == 0) goto L3a
            goto L24
        L3a:
            java.lang.String r5 = r0.toString()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L45
            return r1
        L45:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jfsprovider.ImageUrlProvider.readJsonFromAsset(android.content.Context, java.lang.String):java.lang.String");
    }

    public String getImageUrl(Context context, String str, String str2) {
        ArrayMap<String, String> arrayMap;
        if (imageUrlMaps.containsKey(str)) {
            arrayMap = imageUrlMaps.get(str);
        } else {
            String readJsonFromAsset = readJsonFromAsset(context, str);
            if (readJsonFromAsset == null || (arrayMap = (ArrayMap) JDJSON.parseObject(readJsonFromAsset, new TypeToken<ArrayMap>() { // from class: com.jingdong.sdk.jfsprovider.ImageUrlProvider.1
            }.getType(), new Feature[0])) == null) {
                return null;
            }
            imageUrlMaps.put(str, arrayMap);
        }
        return arrayMap.get(str2);
    }
}
