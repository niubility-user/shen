package com.jingdong.common.jdmiaosha.utils.cache;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes5.dex */
public class JDNetCacheManager {
    public static final String BRAND_BIZKEY = "brand";
    public static final String MIAOSHA_BIZKEY = "seckill";
    public static final String NEWPRODUCT_BIZKEY = "newproduct";
    private static final String TAG = "JDNetCacheManager";
    private static JDNetCacheManager ourInstance = new JDNetCacheManager();

    private JDNetCacheManager() {
    }

    private String getAssetsJson(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(JdSdk.getInstance().getApplication().getAssets().open(str)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            OKLog.e(e2.getMessage(), new Object[0]);
        }
        OKLog.e(TAG, "getAssetsJson \u89e6\u53d1\u56de\u8c03\u903b\u8f91");
        if (TextUtils.isEmpty(sb.toString())) {
            return null;
        }
        return sb.toString();
    }

    public static JDNetCacheManager getInstance() {
        return ourInstance;
    }

    public HttpResponse fetchLocalData(String str) {
        try {
            if (SwitchQueryFetcher.isXTime()) {
                String assetsJson = getAssetsJson(str);
                if (TextUtils.isEmpty(assetsJson)) {
                    return null;
                }
                JDJSONObject parseObject = JDJSON.parseObject(assetsJson);
                HttpResponse httpResponse = new HttpResponse(null);
                httpResponse.setFastJsonObject(parseObject);
                httpResponse.setCache(false);
                OKLog.e(TAG, "fetchLocalData \u8fd4\u56de\u6570\u636e\u975e\u7a7a\uff0c\u6210\u529f\u89e6\u53d1\u56de\u8c03\uff01\uff01\uff01");
                return httpResponse;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
