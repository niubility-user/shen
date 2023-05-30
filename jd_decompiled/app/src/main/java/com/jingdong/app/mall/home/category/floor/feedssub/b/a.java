package com.jingdong.app.mall.home.category.floor.feedssub.b;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.r.e.b;
import com.jingdong.common.unification.uniconfig.UnNewIconTable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class a extends b {

    /* renamed from: c  reason: collision with root package name */
    private static final Map<String, String> f8699c;
    private String a;
    private String b;

    static {
        HashMap hashMap = new HashMap();
        f8699c = hashMap;
        hashMap.put("\u6ee1\u51cf", "tab_020");
        hashMap.put("\u76f4\u964d", "tab_066");
        hashMap.put("\u8d60", "tab_062");
        hashMap.put("\u5238", "tab_060");
    }

    public a(String str) {
        super(null);
        this.a = "";
        this.b = "";
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return (TextUtils.isEmpty(this.a) && TextUtils.isEmpty(this.b)) ? false : true;
    }

    public a(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.a = "";
        this.b = "";
        String jsonString = getJsonString("listResCode");
        String jsonString2 = getJsonString("listShowName");
        String jsonString3 = getJsonString("squareResCode");
        if ("tab_var_071".equals(jsonString)) {
            String str = f8699c.get(jsonString2);
            if (!TextUtils.isEmpty(str)) {
                this.b = str;
                this.a = "";
                return;
            }
        }
        this.b = jsonString.contains(UnNewIconTable.FIELD_IS_VAR) ? this.b : jsonString;
        String str2 = jsonString3.contains(UnNewIconTable.FIELD_IS_VAR) ? this.b : jsonString3;
        this.b = str2;
        if (!TextUtils.isEmpty(str2)) {
            this.a = "";
            return;
        }
        this.b = jsonString.contains(UnNewIconTable.FIELD_IS_VAR) ? jsonString : this.b;
        jsonString3 = jsonString3.contains(UnNewIconTable.FIELD_IS_VAR) ? jsonString3 : this.b;
        this.b = jsonString3;
        if (!TextUtils.isEmpty(jsonString3) && !TextUtils.isEmpty(jsonString2)) {
            this.a = jsonString2;
        } else {
            this.b = "";
        }
    }
}
