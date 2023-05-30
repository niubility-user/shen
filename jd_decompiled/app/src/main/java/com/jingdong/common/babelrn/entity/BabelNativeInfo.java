package com.jingdong.common.babelrn.entity;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.WebViewHelper;

/* loaded from: classes5.dex */
public class BabelNativeInfo {
    public BabelRNEntity babelRNEntity;
    public String doge;
    public String isNative;
    public JDJSONObject jdjsonObject;
    public String url;

    private BabelRNEntity getBabelRNEntity(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (BabelRNEntity) JDJSON.parseObject(str, BabelRNEntity.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Bundle getBundleFromUrl() {
        return WebViewHelper.getBundleFromUrl(this.url);
    }

    public boolean isPageRN() {
        String str = this.isNative;
        return (str == null || !"2".equals(str) || this.babelRNEntity == null) ? false : true;
    }

    public void setDoge(String str) {
        this.doge = str;
        this.babelRNEntity = getBabelRNEntity(str);
    }
}
