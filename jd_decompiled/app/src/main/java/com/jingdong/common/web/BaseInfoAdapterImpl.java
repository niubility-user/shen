package com.jingdong.common.web;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.hybrid.adapter.BaseInfoAdapter;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.net.URLDecoder;

/* loaded from: classes6.dex */
public class BaseInfoAdapterImpl extends BaseInfoAdapter {
    private static final String BABEL_ACTIVITY_CLASS_NAME = "com.jd.lib.babel.view.activity.BabelActivity";
    private static final String WEB_ACTIVITY_CLASS_NAME = "com.jingdong.app.mall.WebActivity";

    /* loaded from: classes6.dex */
    interface MKeyNames {
        public static final String URL_ACTION = "urlAction";
        public static final String URL_PARAMS = "urlParamMap";
    }

    @Override // com.jd.libs.hybrid.adapter.BaseInfoAdapter
    public String getArea() {
        return WebHybridUtils.getArea();
    }

    @Override // com.jd.libs.hybrid.adapter.BaseInfoAdapter
    public String getLat() {
        return WebHybridUtils.getLat();
    }

    @Override // com.jd.libs.hybrid.adapter.BaseInfoAdapter
    public String getLng() {
        return WebHybridUtils.getLng();
    }

    @Override // com.jd.libs.hybrid.adapter.BaseInfoAdapter
    public String getUrl(Activity activity) {
        if (BABEL_ACTIVITY_CLASS_NAME.equals(activity.getClass().getName())) {
            return getUrl(activity.getIntent().getExtras(), Boolean.TRUE);
        }
        return "com.jingdong.app.mall.WebActivity".equals(activity.getClass().getName()) ? getUrl(activity.getIntent().getExtras(), Boolean.FALSE) : "";
    }

    private String getUrl(Bundle bundle, Boolean bool) {
        URLParamMap map;
        if (bundle == null) {
            return null;
        }
        try {
            String string = bundle.getString("url");
            if (TextUtils.isEmpty(string) && bool.booleanValue()) {
                string = bundle.getString("webUrl");
            }
            if (TextUtils.isEmpty(string) && (map = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap()) != null) {
                String str = RemoteMessageConst.TO;
                if (!bool.booleanValue() && !TextUtils.isEmpty(bundle.getString("urlAction"))) {
                    str = bundle.getString("urlAction");
                }
                string = URLDecoder.decode(map.get((Object) str), "utf-8");
            }
            return string != null ? string.trim() : string;
        } catch (Exception unused) {
            return null;
        }
    }
}
