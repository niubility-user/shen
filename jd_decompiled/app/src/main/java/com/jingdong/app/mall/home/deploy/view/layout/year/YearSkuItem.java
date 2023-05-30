package com.jingdong.app.mall.home.deploy.view.layout.year;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class YearSkuItem {

    /* renamed from: i */
    public static final int[] f9103i = {-15066598, -15066598};
    private String a;
    private int[] b;

    /* renamed from: c */
    private String f9104c;
    private String d;

    /* renamed from: e */
    private String f9105e;

    /* renamed from: f */
    private String f9106f;

    /* renamed from: g */
    private JumpEntity f9107g;

    /* renamed from: h */
    private String f9108h;

    public YearSkuItem(JDJSONObject jDJSONObject) {
        this.a = jDJSONObject.getString("title");
        this.f9104c = jDJSONObject.getString("subTitle");
        this.f9108h = jDJSONObject.getString("price");
        this.d = jDJSONObject.getString("img");
        this.b = m.n(jDJSONObject.getString(Constant.KEY_TITLE_COLOR), f9103i, true);
        this.f9105e = jDJSONObject.getString("expo");
        this.f9106f = jDJSONObject.getString("expoJson");
        try {
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("jump");
            this.f9107g = jSONObject == null ? null : (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    public static ArrayList<YearSkuItem> i(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null) {
            return null;
        }
        ArrayList<YearSkuItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                if (jDJSONArray.getJSONObject(i2) != null) {
                    arrayList.add(new YearSkuItem(jDJSONArray.getJSONObject(i2)));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public String a() {
        return this.f9105e;
    }

    public String b() {
        return this.f9106f;
    }

    public String c() {
        return this.d;
    }

    public JumpEntity d() {
        return this.f9107g;
    }

    public String e() {
        return this.f9108h;
    }

    public String f() {
        return this.f9104c;
    }

    public String g() {
        return this.a;
    }

    public int[] h() {
        return this.b;
    }
}
