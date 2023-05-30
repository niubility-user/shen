package com.jingdong.app.mall.home.p.b.e;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class a extends com.jingdong.app.mall.home.r.e.b {
    private String a;
    private String b;

    /* renamed from: c */
    private int f10535c;
    private int d;

    /* renamed from: e */
    private int f10536e;

    /* renamed from: f */
    private String f10537f;

    /* renamed from: g */
    private BaseModel f10538g;

    /* renamed from: h */
    private int f10539h;

    public a(JDJSONObject jDJSONObject, String str) {
        super(jDJSONObject);
        this.a = str;
    }

    private void i(JDJSONObject jDJSONObject) {
        Md5Encrypt.md5(jDJSONObject == null ? "" : jDJSONObject.toString());
        this.a = getJsonString("type");
        getJsonString("id");
        this.f10537f = getJsonString("pId");
        this.f10536e = getJsonInt("height");
        int jsonInt = getJsonInt("hType", -1);
        if (jsonInt >= 0) {
            this.f10535c = jsonInt;
        }
        int jsonInt2 = getJsonInt("sType", -1);
        if (jsonInt2 >= 0) {
            this.d = jsonInt2;
        }
        this.b = this.a.concat(String.valueOf(this.d)).concat(String.valueOf(this.f10535c));
    }

    public static List<a> j(JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("child");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null) {
            return arrayList;
        }
        int size = optJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new a(jDJSONObject, optJSONArray.getJSONObject(i2)));
        }
        return arrayList;
    }

    public com.jingdong.app.mall.home.p.b.b a() {
        return com.jingdong.app.mall.home.p.a.a(this.a);
    }

    public int b() {
        return this.f10536e;
    }

    public int c() {
        return this.f10535c;
    }

    public int d() {
        return this.f10539h;
    }

    public String e(int i2) {
        return this.b.concat(String.valueOf(i2));
    }

    public BaseModel f() {
        return this.f10538g;
    }

    public a g() {
        return com.jingdong.app.mall.home.p.a.b(this.f10537f);
    }

    public int h() {
        return this.d;
    }

    public void k(int i2) {
        this.f10535c = i2;
    }

    public void l(int i2) {
        this.f10539h = i2;
    }

    public void m(BaseModel baseModel) {
        this.f10538g = baseModel;
    }

    public a(JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2) {
        super(jDJSONObject2);
        this.f10535c = jDJSONObject.optInt("hType", 0);
        this.d = jDJSONObject.optInt("sType", 0);
        i(jDJSONObject2);
    }

    public a(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        i(jDJSONObject);
    }
}
