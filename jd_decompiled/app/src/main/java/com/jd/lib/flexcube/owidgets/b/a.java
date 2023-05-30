package com.jd.lib.flexcube.owidgets.b;

import android.text.TextUtils;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class a {
    public Map<String, Map<String, Object>> a = new HashMap();
    public Map<String, String> b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.flexcube.owidgets.b.a$a */
    /* loaded from: classes15.dex */
    public class C0147a extends TypeReference<Map<String, Object>> {
        C0147a(a aVar) {
        }
    }

    public a(Map<String, String> map) {
        this.b = map;
    }

    public synchronized String a(String str) {
        Map<String, Object> map;
        if (!TextUtils.isEmpty(str) && str.startsWith("$") && str.contains(OrderISVUtil.MONEY_DECIMAL)) {
            int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
            String substring = str.substring(1, indexOf);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            if (b.d(substring)) {
                return (String) b.a(str, new HashMap(this.b));
            }
            if (this.a.containsKey(substring)) {
                map = this.a.get(substring);
            } else {
                Map<String, Object> map2 = (Map) JDJSON.parseObject(this.b.get(substring), new C0147a(this), new Feature[0]);
                if (map2 != null) {
                    this.a.put(substring, map2);
                }
                map = map2;
            }
            if (map != null) {
                return (String) b.b(str.substring(indexOf + 1), map);
            }
            return null;
        }
        return b.c(this.b, str);
    }
}
