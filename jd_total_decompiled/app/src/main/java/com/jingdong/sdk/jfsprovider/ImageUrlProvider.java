package com.jingdong.sdk.jfsprovider;

import android.content.Context;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    */
    private String readJsonFromAsset(Context context, String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str), "UTF-8"));
        } catch (IOException unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                }
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
            try {
                break;
            } catch (IOException unused4) {
            }
        }
        bufferedReader.close();
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return null;
        }
        return sb2;
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
