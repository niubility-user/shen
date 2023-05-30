package com.jingdong.app.mall.home.r.e;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.common.entity.JumpEntity;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class g extends a {
    public String a;
    public String b;

    /* renamed from: c */
    public ArrayList<f> f10682c;
    public ArrayList<ArrayList<f>> d;

    /* renamed from: e */
    public int f10683e;

    /* renamed from: f */
    public JumpEntity f10684f;

    /* renamed from: g */
    public JDJSONArray f10685g;

    /* renamed from: h */
    public JDJSONArray f10686h;

    /* renamed from: i */
    public JDJSONObject f10687i;

    /* renamed from: j */
    public JDJSONObject f10688j;

    /* renamed from: k */
    public int f10689k;

    /* renamed from: l */
    private FlexCubeModel f10690l;

    /* renamed from: m */
    public boolean f10691m;

    public g() {
        super(null);
    }

    public ArrayList<f> a() {
        return this.f10682c;
    }

    public ArrayList<ArrayList<f>> b() {
        return this.d;
    }

    public f c(int i2) {
        try {
            return this.f10682c.get(i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public FlexCubeModel d() {
        return this.f10690l;
    }

    public String e() {
        if (this.mParentModel == null) {
            return "";
        }
        return this.mParentModel.A + this.a + this.f10689k;
    }

    public String f() {
        if ("09B".equals(this.b)) {
            return this.b;
        }
        return this.a;
    }

    public boolean g(String str) {
        try {
            Iterator<f> it = this.f10682c.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(str, it.next().s())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void h() {
        JDJSONObject jDJSONObject = this.f10687i;
        if (jDJSONObject == null || jDJSONObject.size() <= 0) {
            return;
        }
        try {
            this.f10690l = (FlexCubeModel) JDJSON.parseObject(this.f10687i.toString(), FlexCubeModel.class);
            this.f10683e = b.getJsonInt(this.f10687i, "floorHeight", 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public g(h hVar) {
        this(hVar == null ? null : hVar.i(0));
    }

    public g(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        if (jDJSONObject == null) {
            return;
        }
        com.jingdong.app.mall.home.v.b.a();
        this.f10685g = getJsonArr("data");
        this.f10686h = getJsonArr("dataChange");
        this.f10688j = getJsonObject("floorInfo");
        this.a = getJsonString("tpl");
        this.b = getJsonString("realTpl");
        this.f10683e = getJsonInt("height");
        ArrayList<f> r0 = f.r0(this, this.f10685g);
        this.f10682c = r0;
        this.d = f.q0(this.f10686h, 0, r0);
        getJsonString("sourceValue");
        getJsonInt("subFloorAnimation");
        getJsonInt("slideInterval", 4000);
        this.f10689k = getJsonInt("displayUIStyle");
        if (this instanceof c) {
            return;
        }
        String jsonString = getJsonString("type");
        if (TextUtils.equals(jsonString, "card")) {
            JDJSONObject g2 = s.g(this.a);
            this.f10687i = g2;
            if (g2 == null) {
                this.a = "88001";
                this.b = "88001";
                return;
            }
            this.a = "deploy";
            this.b = "deploy";
        } else if (TextUtils.equals(jsonString, "flexCube")) {
            this.f10687i = s.g(this.a);
            JDJSONArray jDJSONArray = this.f10685g;
            if (jDJSONArray != null && jDJSONArray.size() > 0) {
                this.f10687i.put("materialGroupList", (Object) this.f10685g);
                h();
            }
            if (this.f10683e <= 0) {
                this.a = "88001";
                this.b = "88001";
                return;
            }
            this.a = "flexCube";
            this.b = "flexCube";
        } else {
            this.f10687i = getJsonObject("deployJson");
        }
        try {
            JDJSONObject jsonObject = getJsonObject("jump");
            this.f10684f = jsonObject == null ? null : (JumpEntity) jsonObject.toJavaObject(JumpEntity.class);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
        JumpEntity jumpEntity = this.f10684f;
        if (jumpEntity != null) {
            jumpEntity.getSrv();
        }
    }
}
