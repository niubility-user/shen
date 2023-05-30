package com.jingdong.sdk.lib.puppetlayout.ylayout.model;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DynamicHelper;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ParentHandler;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class Span {
    public HashMap<String, String> params = new HashMap<>();
    public HashMap<String, String> dynamicParams = new HashMap<>();

    private HashMap<String, String> cloneMap(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        if (hashMap != null && hashMap.size() != 0) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                if (entry != null && entry.getKey() != null) {
                    hashMap2.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return hashMap2;
    }

    public void compute(PuppetContext puppetContext, JDJSONObject jDJSONObject) {
        String valueFromData;
        ParentHandler parentHandler;
        if (this.dynamicParams.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : this.dynamicParams.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                String value = entry.getValue();
                HashMap hashMap = new HashMap();
                for (String str : DynamicHelper.getDynamicList(null, value)) {
                    if (str.startsWith("parent.") && (parentHandler = puppetContext.parentHandler) != null) {
                        try {
                            valueFromData = parentHandler.getRealValue(str);
                        } catch (Exception e2) {
                            if (b.a) {
                                e2.printStackTrace();
                            }
                            valueFromData = null;
                        }
                    } else {
                        valueFromData = DynamicHelper.getValueFromData(puppetContext, str, jDJSONObject);
                    }
                    if (valueFromData != null) {
                        hashMap.put(str, valueFromData);
                    }
                }
                if (hashMap.size() > 0) {
                    this.params.put(entry.getKey(), DynamicHelper.replaceAllValue(value, hashMap));
                }
            }
        }
    }

    public boolean hasDynamic() {
        return this.dynamicParams.size() > 0;
    }

    public void update(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (DynamicHelper.isDynamic(str2)) {
            this.dynamicParams.put(str, str2);
        } else {
            this.params.put(str, str2);
        }
    }

    /* renamed from: clone */
    public Span m26clone() {
        Span span;
        CloneNotSupportedException e2;
        try {
            span = (Span) super.clone();
        } catch (CloneNotSupportedException e3) {
            span = null;
            e2 = e3;
        }
        try {
            span.params = cloneMap(this.params);
        } catch (CloneNotSupportedException e4) {
            e2 = e4;
            if (b.a) {
                e2.printStackTrace();
            }
            return span;
        }
        return span;
    }
}
